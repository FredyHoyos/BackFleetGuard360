package com.fleetguard.api.service;

import com.fleetguard.api.DTO.CreateAssigmentDTO;
import com.fleetguard.api.DTO.GetAssigmentDTO;
import com.fleetguard.api.model.Assigment;
import com.fleetguard.api.model.Driver;
import com.fleetguard.api.model.Shift;
import com.fleetguard.api.repository.AssigmentRepository;
import com.fleetguard.api.repository.DriverRepository;
import com.fleetguard.api.repository.ShiftRepository;
import com.fleetguard.api.mapper.AssigmentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssigmentService {
    private AssigmentRepository assigmentRepository;
    private ShiftRepository shiftRepository;
    private AssigmentMapper assigmentMapper;
    private DriverRepository driverRepository;


    AssigmentService(AssigmentRepository assigmentRepository, ShiftRepository shiftRepository, AssigmentMapper assigmentMapper, DriverRepository driverRepository) {
        this.assigmentRepository = assigmentRepository;
        this.shiftRepository = shiftRepository;
        this.assigmentMapper = assigmentMapper;
        this.driverRepository = driverRepository;
    }

    public List<GetAssigmentDTO> getAllAssigments()  {
        Iterable<Assigment> assigments = assigmentRepository.findAll();
        List<GetAssigmentDTO> dtos = new ArrayList<>();
        if (assigments != null) {
            assigments.forEach(item -> {
            dtos.add(assigmentMapper.toGetDTO(item));});
        }

        return dtos;
    }

    public GetAssigmentDTO createAssigment(CreateAssigmentDTO dto) {
        if (shiftRepository.findById(dto.getShiftId()).isPresent()) {
            Assigment assigment = assigmentMapper.toModel(dto);
            assigmentRepository.save(assigment);
            return assigmentMapper.toGetDTO(assigment);
        }
        else {
            throw new RuntimeException("Shift not found");
        }
    }

    public GetAssigmentDTO updateAssigment(Long id, CreateAssigmentDTO dto) {
        Assigment existingAssigment = assigmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Assigment not found"));
        existingAssigment.setDriver(
                driverRepository.findById(dto.getDriverId().intValue())
                        .orElseThrow(() -> new RuntimeException("Driver not found for id: " + dto.getDriverId()))
        );

        existingAssigment.setDriver2(
                driverRepository.findById(dto.getDriver2Id().intValue())
                        .orElseThrow(() -> new RuntimeException("Driver not found for id: " + dto.getDriver2Id()))
        );

        existingAssigment.setShift(
                shiftRepository.findById(dto.getShiftId())
                        .orElseThrow(() -> new RuntimeException("Shift not found for id: " + dto.getShiftId()))
        );

        existingAssigment.setShiftCompleted(dto.getIsShiftCompleted());
        Assigment updated = assigmentRepository.save(existingAssigment);
        return assigmentMapper.toGetDTO(updated);
    }

    public String deleteAssigment(long id) {
        if(assigmentRepository.findById(id).isPresent()) {
            assigmentRepository.deleteById(id);
            return "Assigment deleted";
        }else {
            throw new RuntimeException("Assigment not found for id:" + id);
        }
    }

}
