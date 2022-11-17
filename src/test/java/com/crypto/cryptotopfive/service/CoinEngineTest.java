package com.crypto.cryptotopfive.service;

import com.crypto.cryptotopfive.dto.CoinDto;
import com.crypto.cryptotopfive.mapper.CoinMapper;
import com.crypto.cryptotopfive.repository.CoinRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CoinEngineTest {
    @Mock
    CoinRepository coinRepository;

    @Mock
    CoinService coinService;

    @Mock
    CoinMapper coinMapper;

    @InjectMocks
    CoinEngine coinEngine;

    @Test
    public void shouldNotSaveDataIfCoingeckoDidntReturnData() {
        when(coinService.findTopFiveCoins()).thenReturn(null);
        coinEngine.computeTopFiveCoins();
        verify(coinRepository, times(0)).saveAll(anyCollection());
    }

    @Test
    public void shouldSaveAllCoins() {
        when(coinService.findTopFiveCoins()).thenReturn(createCoinDtoArray());
        when(coinMapper.mapCoinDtoToCoin(any())).thenCallRealMethod();
        coinEngine.computeTopFiveCoins();
        verify(coinRepository, times(1)).saveAll(anyCollection());
    }

    private CoinDto[] createCoinDtoArray() {
        CoinDto[] coinDtos = new CoinDto[5];
        coinDtos[0] = CoinDto.builder()
                .date(new Date())
                .price(9.99)
                .symbol("btc")
                .name("bitcoin")
                .build();

        coinDtos[1] = CoinDto.builder()
                .date(new Date())
                .price(1.01)
                .symbol("usdt")
                .name("tether")
                .build();


        coinDtos[2] = CoinDto.builder()
                .date(new Date())
                .price(5.99)
                .symbol("eth")
                .name("ether")
                .build();

        coinDtos[3] = CoinDto.builder()
                .date(new Date())
                .price(1.11)
                .symbol("bnb")
                .name("binance")
                .build();

        coinDtos[4] = CoinDto.builder()
                .date(new Date())
                .price(19.99)
                .symbol("sol")
                .name("solana")
                .build();

        return coinDtos;
    }

}