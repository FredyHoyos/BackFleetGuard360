package com.fleetguard.api.DTO;

import com.fleetguard.api.model.Administrator;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class AdministratorDTO {
    private String id;
    private String username;

    public AdministratorDTO(){}

    public AdministratorDTO(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public static List<AdministratorDTO> from(List<Administrator> admins) {
        return admins.stream()  // Corrected the reference to the builder
                .map(admin -> AdministratorDTO.builder()
                        .id(String.valueOf(admin.getId()))
                        .username(admin.getUsername())
                        .build())
                .collect(Collectors.toList());
    }
}
