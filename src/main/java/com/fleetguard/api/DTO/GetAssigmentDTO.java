package com.fleetguard.api.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Setter
@Getter
public class GetAssigmentDTO {
    private Long assigmentId;
    private Long driverId;
    private String driverName;
    private String driverEmail;
    private Long driver2Id;
    private String driver2Name;
    private String driver2Email;
    private Long shiftId;
    private LocalDate shiftDate;
    private LocalTime shiftStartTime;
    private LocalTime shiftEndTime;
    private String shiftRoute;
    private int shiftWorkedHours;

}
