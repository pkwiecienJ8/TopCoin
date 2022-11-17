package com.crypto.cryptotopfive.service;

import com.crypto.cryptotopfive.dto.CoinDto;
import com.crypto.cryptotopfive.entity.Coin;
import com.crypto.cryptotopfive.mapper.CoinMapper;
import com.crypto.cryptotopfive.repository.CoinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinEngine {

    private final static Logger LOGGER = LoggerFactory.getLogger(CoinEngine.class);

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;
    private final CoinService coinService;

    public CoinEngine(CoinRepository coinRepository,
                      CoinMapper coinMapper,
                      CoinService coinService) {
        this.coinRepository = coinRepository;
        this.coinMapper = coinMapper;
        this.coinService = coinService;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void computeTopFiveCoins() {
        CoinDto[] coinDtos = coinService.findTopFiveCoins();

        if (coinDtos == null) {
            LOGGER.warn("Schedule job failed. No data");
            return;
        }

        List<Coin> coinList = Arrays.stream(coinDtos)
                .map(coinMapper::mapCoinDtoToCoin)
                .collect(Collectors.toList());

        coinRepository.saveAll(coinList);
    }
}
