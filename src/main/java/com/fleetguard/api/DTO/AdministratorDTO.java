package com.fleetguard.api.DTO;

import com.fleetguard.api.model.Administrator;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdministratorDTO {
    private String id;
    private String username;

    public AdministratorDTO(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public static AdministratorDTO from(Administrator administrator) {
        return AdministratorDTO.builder()  // Corrected the reference to the builder
                .id(String.valueOf(administrator.getId()))
                .username(administrator.getUsername())
                .build();
    }

}
