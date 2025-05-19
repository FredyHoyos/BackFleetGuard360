package com.fleetguard.api.repository;

import org.springframework.data.repository.CrudRepository;
import com.fleetguard.api.model.Driver;

import java.util.Optional;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
    Optional<Driver> findByUsername(String username);
}
