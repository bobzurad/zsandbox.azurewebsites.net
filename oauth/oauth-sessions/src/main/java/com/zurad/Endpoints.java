package com.zurad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Endpoints {

    @GetMapping("/")
    String serverInfo(@Value("${server.port}") int port) {
        return "Hello, your server port is: " + port;
    }

    @GetMapping("/test")
    String test() {
        return "test";
    }
}

