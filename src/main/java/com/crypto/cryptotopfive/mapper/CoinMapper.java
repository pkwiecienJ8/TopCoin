package com.crypto.cryptotopfive.mapper;

import com.crypto.cryptotopfive.dto.CoinDto;
import com.crypto.cryptotopfive.entity.Coin;
import com.crypto.cryptotopfive.entity.CoinPrice;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CoinMapper {

    public Coin mapCoinDtoToCoin(CoinDto coinDto) {
        Coin coin = Coin.builder()
                .symbol(coinDto.getSymbol())
                .name(coinDto.getName())
                .build();

        coin.setPrices(Collections.singletonList(mapCoinDtoToCoinPrice(coinDto, coin)));

        return coin;
    }

    private CoinPrice mapCoinDtoToCoinPrice(CoinDto coinDto, Coin coin) {
        return CoinPrice.builder()
                .price(coinDto.getPrice())
                .date(coinDto.getDate())
                .coin(coin)
                .build();
    }
}
