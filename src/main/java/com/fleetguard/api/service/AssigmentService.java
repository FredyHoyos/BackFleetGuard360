package com.fleetguard.api.service;

import com.fleetguard.api.model.Assigment;
import com.fleetguard.api.repository.AssigmentRepository;
import com.fleetguard.api.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AssigmentService {
    private AssigmentRepository assigmentRepository;
    private ShiftRepository shiftRepository;

    AssigmentService(AssigmentRepository assigmentRepository, ShiftRepository shiftRepository) {
        this.assigmentRepository = assigmentRepository;
        this.shiftRepository = shiftRepository;
    }

    public Iterable<Assigment> getAllAssigments()  {
        return Optional.of(assigmentRepository.findAll())
                .orElseThrow(() -> new RuntimeException("No assigments found"));
    }

    public Assigment createAssigment(Assigment assigment) {
        if (shiftRepository.findById(assigment.getShift().getId()).isPresent()) {
            Assigment newAssigment = new Assigment();
            newAssigment.setDriver(assigment.getDriver());
            newAssigment.setDriver2(assigment.getDriver2());
            newAssigment.setShift(assigment.getShift());
            newAssigment.setShiftCompleted(false);
            return assigmentRepository.save(newAssigment);
        }
        else {
            throw new RuntimeException("Shift not found");
        }
    }

    public Assigment updateAssigment(long id, Assigment assigment) {
        if (shiftRepository.findById(assigment.getShift().getId()).isPresent()){
            return Optional.of(new Assigment())
                    .map(item -> {
                       item.setDriver(assigment.getDriver());
                       item.setDriver2(assigment.getDriver2());
                       item.setShift(assigment.getShift());
                       item.setShiftCompleted(assigment.getShiftCompleted());
                       return assigmentRepository.save(item);
                    })
                    .orElseThrow(() -> new RuntimeException("Error updating assigment"));
        } else {
            throw new RuntimeException("Shift not found");
        }
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
