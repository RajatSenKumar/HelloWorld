package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    @Cacheable(value = "helloCache")
    public String getHelloMessage() {
        logger.info("getHelloMessage() called");
        return "Hello World...!!!";
    }
}
