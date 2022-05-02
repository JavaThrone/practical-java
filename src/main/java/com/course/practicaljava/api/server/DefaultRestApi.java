package com.course.practicaljava.api.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping(value = "/api")
public class DefaultRestApi {

    private Logger log = LoggerFactory.getLogger(DefaultRestApi.class);

    @GetMapping(value = "/welcome")
    public String welcome() {
        log.info(StringUtils.join("Hello", " this is", " String boot", " REST API!!!"));
        return "Welcome to Spring Boot";
    }

    @GetMapping(value = "/time")
    public String time() {
        return LocalTime.now().toString();
    }

}
