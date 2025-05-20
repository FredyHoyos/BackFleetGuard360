package com.fleetguard.api.controller;

import com.fleetguard.api.DTO.DriverDTO;
import com.fleetguard.api.mapper.DriverMapper;
import com.fleetguard.api.model.Driver;
import com.fleetguard.api.repository.DriverRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
    @Autowired
    private DriverRepository repository;
    @Autowired
    private DriverMapper driverMapper;

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

    @PutMapping("/update/{id}")
    public @ResponseBody Driver updateDriver(@PathVariable int id, @Valid @RequestBody DriverDTO newDriver) {
        Driver driver = driverMapper.toModel(newDriver);
        driver.setId(Long.valueOf(id));
        return repository.save(driver);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteDriver(@PathVariable int id) {
        repository.deleteById(id);
    }
}
