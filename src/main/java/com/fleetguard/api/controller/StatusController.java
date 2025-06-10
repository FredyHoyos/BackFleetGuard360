package com.fleetguard.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:3000", "https://front-fleet-guard360.vercel.app"})

@RestController
public class StatusController {
    @GetMapping("/")
    ResponseEntity<String> status() {
        return ResponseEntity.status(HttpStatus.OK).body("FleetGuard App Status is OK");
    }
}
