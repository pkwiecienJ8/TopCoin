package com.crypto.cryptotopfive.controller;

import com.crypto.cryptotopfive.service.CoinService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CoinController {

    private final CoinService coinService;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping(value = "/coin/{name}")
    public ResponseEntity<Object> getCoinData(@PathVariable String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(coinService.findCoinData(name));
    }

    @GetMapping(value = "/coin/{name}/history")
    public ResponseEntity<Object> getCoinHistoricalData(@PathVariable String name,
                                                        @RequestParam(value = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(coinService.findCoinHistoryData(name, date));
    }
}
