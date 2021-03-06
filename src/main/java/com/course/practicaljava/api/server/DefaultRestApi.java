package com.course.practicaljava.api.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping(value = "/api")
public class DefaultRestApi {

    private final Logger log = LoggerFactory.getLogger(DefaultRestApi.class);

    @GetMapping(value = "/welcome")
    public String welcome() {
        log.info(StringUtils.join("Hello", " this is", " String boot", " REST API!!!"));
        return "Welcome to Spring Boot";
    }

    @GetMapping(value = "/time")
    public String time() {
        return LocalTime.now().toString();
    }

    @GetMapping(value = "/header-one")
    public String headerByAnnotation(
        @RequestHeader(name = "User-Agent") String headerUserAgent,
        @RequestHeader(name = "Practical-java") String headerPracticalJava
    ) {
        return "User-agent: " + headerUserAgent + " Practical-java: " + headerPracticalJava;
    }

    @GetMapping(value = "/header-two")
    public String headerByRequest(@RequestHeader HttpHeaders headers) {
        return "User-agent: " + headers.getValuesAsList("User-Agent")
            + " Practical-java: " + headers.getValuesAsList("Practical-java");
    }

}
