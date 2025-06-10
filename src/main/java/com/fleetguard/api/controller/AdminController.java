package com.fleetguard.api.controller;

import com.fleetguard.api.DTO.AdministratorDTO;
import com.fleetguard.api.model.Administrator;
import com.fleetguard.api.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = {"http://localhost:3000", "https://front-fleet-guard360.vercel.app"})

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdministratorRepository administratorRepository;

    @GetMapping("/all")
    public ResponseEntity<List<AdministratorDTO>> getAll() {
        Iterable<Administrator> administrators = administratorRepository.findAll();
        List<Administrator> adminList = StreamSupport.stream(
                administrators.spliterator(), false)
                .collect(Collectors.toList());
        return ResponseEntity.ok(AdministratorDTO.from(adminList));
    }

}
