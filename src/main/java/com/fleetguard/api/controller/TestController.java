package com.fleetguard.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String testEndpoint() {
        return " La API de Fleetguard está funcionando correctamente y con despliegue continuo :).";
    }
}