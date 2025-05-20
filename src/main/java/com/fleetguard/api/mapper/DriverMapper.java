package com.fleetguard.api.mapper;

import com.fleetguard.api.DTO.DriverDTO;
import com.fleetguard.api.model.Driver;
import com.fleetguard.api.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fleetguard.api.exception.EntityNotFoundException;

@Component
public class DriverMapper {
    @Autowired
    private final DocumentTypeRepository documentTypeRepository;

    DriverMapper(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DriverDTO toDTO(Driver driver) {
        DriverDTO dto = new DriverDTO();
        dto.setName(driver.getName());
        dto.setUsername(driver.getUsername());
        dto.setDocumentType(driver.getDocumentType().getId());
        dto.setDocumentNumber(String.valueOf(driver.getDocumentNumber()));
        dto.setRol(driver.getRol());
        dto.setAddress(String.valueOf(driver.getAddress()));
        dto.setBirthDate(driver.getBirthDate());
        dto.setPhoneNumber(driver.getPhoneNumber());
        dto.setEmail(driver.getEmail());
        dto.setPassword(driver.getPassword());
        return dto;
    }

    public Driver toModel(DriverDTO dto) {
        Driver driver = new Driver();
        driver.setName(dto.getName());
        driver.setUsername(dto.getUsername());
        driver.setDocumentType(documentTypeRepository.findById(dto.getDocumentType()).orElseThrow(() -> new EntityNotFoundException("Document type not found" + driver.getDocumentType().getId())));
        driver.setDocumentNumber(dto.getDocumentNumber());
        driver.setRol(dto.getRol());
        driver.setAddress(dto.getAddress());
        driver.setBirthDate(dto.getBirthDate());
        driver.setPhoneNumber(dto.getPhoneNumber());
        driver.setEmail(dto.getEmail());
        driver.setPassword(dto.getPassword());
        return driver;
    }
}
