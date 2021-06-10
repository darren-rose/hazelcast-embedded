package com.example.hazelcast.embedded;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/crypto-currencies")
public class CryptoCurrencyController {

    private final CacheClient cacheClient;

    public CryptoCurrencyController(CacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    @PostMapping(path = "/", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public CryptoCurrency put(@RequestBody CryptoCurrency cryptoCurrency) {
        return cacheClient.put(cryptoCurrency.getTicker(), cryptoCurrency);
    }

    @GetMapping(value = "/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CryptoCurrency get(@PathVariable String ticker) {
        return cacheClient.get(ticker);
    }
}