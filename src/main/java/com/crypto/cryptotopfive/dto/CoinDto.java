package com.crypto.cryptotopfive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CoinDto {
    private String symbol;

    private String name;

    @JsonProperty("current_price")
    private double price;

    @JsonProperty("last_updated")
    private Date date;
}
