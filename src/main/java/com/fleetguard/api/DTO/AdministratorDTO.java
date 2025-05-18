package com.fleetguard.api.DTO;

import com.fleetguard.api.model.Administrator;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public class AdministratorDTO {
    private String id;
    private String username;

    public static AdministratorDTO from(Administrator administrator) {
        return AdministratorDTO.builder()  // Corrected the reference to the builder
                .id(String.valueOf(administrator.getId()))
                .username(administrator.getUsername())
                .build();
    }

    public static List<AdministratorDTO> from(List<Administrator> administrators) {
        return administrators.stream()
                .map(admin -> new AdministratorDTO(String.valueOf(admin.getId()), admin.getUsername()))
                .collect(Collectors.toList());
    }
}
