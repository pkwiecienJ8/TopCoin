package com.crypto.cryptotopfive.repository;

import com.crypto.cryptotopfive.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, String> {
}
