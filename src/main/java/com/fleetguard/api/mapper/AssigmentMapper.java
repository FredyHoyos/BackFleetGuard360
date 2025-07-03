package com.fleetguard.api.mapper;

import com.fleetguard.api.DTO.CreateAssigmentDTO;
import com.fleetguard.api.DTO.GetAssigmentDTO;
import com.fleetguard.api.model.Assigment;
import com.fleetguard.api.repository.DriverRepository;
import com.fleetguard.api.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssigmentMapper {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    public AssigmentMapper(DriverRepository driverRepository, ShiftRepository shiftRepository) {
        this.driverRepository = driverRepository;
        this.shiftRepository = shiftRepository;
    }

    public CreateAssigmentDTO toCreateDTO(Assigment assigment) {
        CreateAssigmentDTO dto = new CreateAssigmentDTO();
        dto.setDriverId(assigment.getDriver().getId());
        dto.setDriver2Id(assigment.getDriver2().getId());
        dto.setShiftId(assigment.getShift().getId());
        dto.setIsShiftCompleted(assigment.getShiftCompleted());
        return dto;
    }

    public Assigment toModel(CreateAssigmentDTO dto) {
        Assigment assigment = new Assigment();
        assigment.setDriver(driverRepository.findById(dto.getDriverId().intValue()).orElseThrow(() -> new RuntimeException("Driver not found for id: " + dto.getDriverId())));
        assigment.setDriver2(driverRepository.findById(dto.getDriver2Id().intValue()).orElseThrow(() -> new RuntimeException("Driver not found for id: " + dto.getDriver2Id())));
        assigment.setShift(shiftRepository.findById(dto.getShiftId()).orElseThrow(() -> new RuntimeException("Shift not found for id: " + dto.getShiftId())));
        assigment.setShiftCompleted(dto.getIsShiftCompleted());
        return assigment;
    }

    public GetAssigmentDTO toGetDTO(Assigment assigment) {
            GetAssigmentDTO dto = new GetAssigmentDTO();
            dto.setAssigmentId(assigment.getId());
            dto.setDriverId(assigment.getDriver().getId());
            dto.setDriverName(assigment.getDriver().getName());
            dto.setDriverEmail(assigment.getDriver().getEmail());
            dto.setDriver2Id(assigment.getDriver2().getId());
            dto.setDriver2Name(assigment.getDriver2().getName());
            dto.setDriver2Email(assigment.getDriver2().getEmail());
            dto.setShiftId(assigment.getShift().getId());
            dto.setShiftDate(assigment.getShift().getDate());
            dto.setShiftStartTime(assigment.getShift().getStartTime());
            dto.setShiftEndTime(assigment.getShift().getEndTime());
            dto.setShiftRoute(assigment.getShift().getRoute());
            dto.setShiftWorkedHours(assigment.getShift().getWorkedHoursInShift());
            return dto;
    }

}
