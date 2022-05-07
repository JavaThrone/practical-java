package com.course.practicaljava.api.server;

import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DefaultRestApiTest {

    @Autowired
    private WebTestClient client;

    @Test
    void welcome() {
        client.get().uri("/api/welcome")
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(String.class)
            .value(IsEqualIgnoringCase.equalToIgnoringCase("Welcome to Spring Boot"));
    }

    @Test
    void time() {
        client
            .get()
            .uri("/api/time/")
            .exchange()
            .expectBody(String.class)
            .value(this::isCorrectTime);
    }

    private void isCorrectTime(String v) {
        var responseLocalTime = LocalTime.parse(v);
        var now = LocalTime.now();
        var nowMinus30Seconds = now.minusSeconds(30);

        assertTrue(responseLocalTime.isAfter(nowMinus30Seconds) && responseLocalTime.isBefore(now));
    }

    @Test
    void headerByAnnotation() {
    }

    @Test
    void headerByRequest() {
    }
}
