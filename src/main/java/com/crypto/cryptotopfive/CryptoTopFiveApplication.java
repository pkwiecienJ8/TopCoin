package com.crypto.cryptotopfive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoTopFiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoTopFiveApplication.class, args);
	}

}
