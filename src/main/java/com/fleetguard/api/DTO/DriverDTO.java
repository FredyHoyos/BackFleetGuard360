package com.fleetguard.api.DTO;

import com.fleetguard.api.model.Driver;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class DriverDTO {
    private Long id;
    private String name;
    private String username;
    private Integer documentType;
    private String documentNumber;
    private String rol;
    private String address;
    private Date birthDate;
    private String phoneNumber;
    private String email;
    private String password;

    public DriverDTO(){}

    public DriverDTO(Long id, String name, String username,
                     Integer documentType,  String documentNumber,
                     String rol, String address, Date birthDate, String phoneNumber,
                     String email, String password){
        this.id = id;
        this.name = name;
        this.username = username;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.rol = rol;
        this.address = address;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public static List<DriverDTO> fromList(List<Driver> drivers){
        return drivers.stream()
                .map(driver -> DriverDTO.builder()
                        .id(driver.getId())
                        .name(driver.getName())
                        .username(driver.getUsername())
                        .documentType(driver.getDocumentType().getId())
                        .documentNumber(driver.getDocumentNumber())
                        .rol(driver.getRol())
                        .address(driver.getAddress())
                        .birthDate(driver.getBirthDate())
                        .phoneNumber(driver.getPhoneNumber())
                        .email(driver.getEmail())
                        .password(driver.getPassword())
                        .build())
                .collect(Collectors.toList());
    }
}
