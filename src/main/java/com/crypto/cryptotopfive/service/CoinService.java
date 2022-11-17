package com.crypto.cryptotopfive.service;

import com.crypto.cryptotopfive.dto.CoinDto;
import com.crypto.cryptotopfive.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CoinService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoinService.class);
    private final WebClient webClient;
    private static final DateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy");

    public CoinService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.coingecko.com/api/v3/").build();
    }

    public String findCoinData(String name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("coins/{name}")
                        .build(name))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, this::handleErrors)
                .bodyToMono(String.class)
                .block();
    }

    public String findCoinHistoryData(String name, Date date) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("coins/{name}/history")
                        .queryParam("date", "{date}")
                        .build(name, FORMATTER.format(date)))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public CoinDto[] findTopFiveCoins() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("coins/markets/")
                        .queryParam("vs_currency", "{currency}")
                        .queryParam("order", "{order}")
                        .queryParam("per_page", "{per_page}")
                        .queryParam("page", "{page}")
                        .build("usd", "market_cap_desc", "5", "1"))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, this::handleErrors)
                .bodyToMono(CoinDto[].class)
                .block();
    }

    private Mono<? extends Throwable> handleErrors(ClientResponse response) {
        return response.bodyToMono(String.class).flatMap(body -> {
            LOGGER.error("Error when fetching data from coingecko");
            return Mono.error(new ApiException(body));
        });
    }
}

