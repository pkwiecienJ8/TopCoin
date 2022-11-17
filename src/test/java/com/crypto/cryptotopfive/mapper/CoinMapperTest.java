package com.crypto.cryptotopfive.mapper;

import com.crypto.cryptotopfive.dto.CoinDto;
import com.crypto.cryptotopfive.entity.Coin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


class CoinMapperTest {

    private final CoinMapper coinMapper = new CoinMapper();

    @Test
    public void mapCoinDtoToCoinWillThrowNPEIfObjectIsNullTest() {
        Assertions.assertThrows(NullPointerException.class, () -> coinMapper.mapCoinDtoToCoin(null));
    }

    @Test
    public void mapCoinDtoToCoinTest() {
        CoinDto coinDto = CoinDto.builder()
                .name("bitcoin")
                .symbol("btc")
                .price(1.11)
                .date(new Date())
                .build();

        Coin result = coinMapper.mapCoinDtoToCoin(coinDto);

        assertThat(result).isNotNull()
                .extracting("symbol", "name")
                .contains("btc", "bitcoin");

        assertThat(result.getPrices())
                .extracting("price")
                .contains(1.11);
    }
}