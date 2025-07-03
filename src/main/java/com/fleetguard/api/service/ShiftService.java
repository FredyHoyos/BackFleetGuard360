package com.fleetguard.api.service;

import com.fleetguard.api.model.Shift;
import com.fleetguard.api.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {
    private ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public Iterable<Shift> getAllShifts() {
        return Optional.of(shiftRepository.findAll())
                .orElseThrow(() ->  new RuntimeException("No shift found"));
    }

    public Shift getShiftById(long id) {
        return shiftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No shift found for id:" + id));
    }

    public Shift createShift(Shift shift) {
        return Optional.of(new Shift())
                .map( item -> {
                    item.setDate(shift.getDate());
                    item.setStartTime(shift.getStartTime());
                    item.setEndTime(shift.getEndTime());
                    item.setRoute(shift.getRoute());
                    int workedHours = calculateWorkedHours(shift.getStartTime(), shift.getEndTime());
                    item.setWorkedHoursInShift(workedHours);
                    return shiftRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Error while creating shift"));
    }

    public Shift updateShift(long id, Shift shift) {
        if (shiftRepository.findById(id).isPresent()) {
            return Optional.of(new Shift())
                    .map(item -> {
                        item.setDate(shift.getDate());
                        item.setStartTime(shift.getStartTime());
                        item.setEndTime(shift.getEndTime());
                        item.setRoute(shift.getRoute());
                        int workedHours = calculateWorkedHours(shift.getStartTime(), shift.getEndTime());
                        item.setWorkedHoursInShift(workedHours);
                        return shiftRepository.save(item);
                    })
                    .orElseThrow(() -> new RuntimeException("Error while updating shift"));
        }
        else {
            throw new RuntimeException("Shift not found for id:" + id);
        }

    }

    public String deleteShift(long id) {
        if (shiftRepository.findById(id).isPresent()) {
            shiftRepository.deleteById(id);
            return "Shift deleted for id:" + id;
        } else {
            throw new RuntimeException("Shift not found for id:" + id);
        }
    }

    private int calculateWorkedHours(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        long diffInMillies = endDate.getTime() - startDate.getTime();
        return (int) diffInMillies / (60 * 60 * 1000);
    }
}
