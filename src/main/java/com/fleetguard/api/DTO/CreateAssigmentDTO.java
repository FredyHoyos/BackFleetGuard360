package com.fleetguard.api.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CreateAssigmentDTO {
    private Long driverId;
    private Long driver2Id;
    private Long shiftId;
    private Boolean isShiftCompleted;

}
