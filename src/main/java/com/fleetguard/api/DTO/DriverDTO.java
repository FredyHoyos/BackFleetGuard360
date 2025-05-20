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

    public DriverDTO(String name, String username,
                     Integer documentType,  String documentNumber,
                     String rol, String address, Date birthDate, String phoneNumber,
                     String email, String password){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<DriverDTO> fromList(List<Driver> drivers){
        return drivers.stream()
                .map(driver -> DriverDTO.builder()
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
