package com.fleetguard.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/")
    ResponseEntity<String> status() {
        return ResponseEntity.status(HttpStatus.OK).body("FleetGuard App Status is OK");
    }
}
