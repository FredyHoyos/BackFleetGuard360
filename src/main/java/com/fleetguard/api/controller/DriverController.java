package com.fleetguard.api.controller;

import com.fleetguard.api.DTO.DriverDTO;
import com.fleetguard.api.exception.EntityNotFoundException;
import com.fleetguard.api.mapper.DriverMapper;
import com.fleetguard.api.model.Driver;
import com.fleetguard.api.repository.DriverRepository;
import com.fleetguard.api.service.PhotoStorageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
    @Autowired
    private DriverRepository repository;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private PhotoStorageService photoStorageService;

    DriverController() {}
    DriverController(DriverRepository repository, DriverMapper driverMapper) {
        this.repository = repository;
        this.driverMapper = driverMapper;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Driver> getAll() {
        return repository.findAll();
    }

    @PostMapping("/register")
    public @ResponseBody Driver createDriver(@Valid @RequestBody DriverDTO newDriver) {
        Driver driver = driverMapper.toModel(newDriver);
        return repository.save(driver);
    }

    @PutMapping("/{id}")
    public @ResponseBody Driver updateDriver(@PathVariable int id, @Valid @RequestBody DriverDTO newDriver) {
        Driver driver = driverMapper.toModel(newDriver);
        driver.setId(Long.valueOf(id));
        return repository.save(driver);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody void deleteDriver(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping("/photo/{id}")
    public @ResponseBody String uploadPhoto(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try{
            Driver driver = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver not found" + id));
            String relativePath = photoStorageService.savePhoto(file, Long.valueOf(id), driver.getUsername());
            driver.setPhoto(relativePath);
            repository.save(driver);

            return "Successfully uploaded photo.";
        } catch (IOException e) {
            return "Error while uploading photo.";
        }
    }
}
