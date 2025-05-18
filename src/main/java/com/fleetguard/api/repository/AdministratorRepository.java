package com.fleetguard.api.repository;

import com.fleetguard.api.model.Administrator;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdministratorRepository extends CrudRepository<Administrator, Integer> {
    Optional<Administrator> findByUsername(String username);
    boolean existsByUsername(String username);
    void deleteByUsername(String username);
}
