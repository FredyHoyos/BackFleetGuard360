package com.fleetguard.api.mapper;

import com.fleetguard.api.DTO.DriverDTO;
import com.fleetguard.api.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverMapper {
    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);
    DriverDTO dtoToDriverDTO(Driver driver);
}
