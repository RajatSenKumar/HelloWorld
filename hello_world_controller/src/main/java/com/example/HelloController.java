package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        logger.info("sayHello() called");
        try {
            String message = helloService.getHelloMessage();
            logger.info("Returning Message: {}", message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            logger.error("Error occurred while fetching the hello message: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing your request. Please try again later.");
        }
    }

    @GetMapping("/")
    public ResponseEntity<String> redirectToHello() {
        logger.info("Default endpoint accessed, redirecting to /hello");
        return sayHello(); // Reuse the logic of sayHello()
    }
}
