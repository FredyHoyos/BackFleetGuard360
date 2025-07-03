package com.fleetguard.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "assigment")
public class Assigment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_driver")
    private Driver driver;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_driver_2")
    private Driver driver2;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_shift")
    private Shift shift;

    private Boolean isShiftCompleted;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver2() {
        return driver2;
    }

    public void setDriver2(Driver driver2) {
        this.driver2 = driver2;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }


    public Boolean getShiftCompleted() {
        return isShiftCompleted;
    }

    public void setShiftCompleted(Boolean shiftCompleted) {
        isShiftCompleted = shiftCompleted;
    }
}
