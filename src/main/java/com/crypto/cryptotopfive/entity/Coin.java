package com.crypto.cryptotopfive.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coins")
@Entity
public class Coin {

    @Id
    private String symbol;

    private String name;

    @OneToMany(mappedBy = "coin", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<CoinPrice> prices;

}
