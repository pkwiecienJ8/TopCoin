package com.crypto.cryptotopfive.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coin_price")
@Entity
public class CoinPrice {

    @Id
    @GeneratedValue
    private int id;

    private double price;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "coin_symbol")
    private Coin coin;
}
