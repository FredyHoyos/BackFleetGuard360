package com.fleetguard.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @NotNull
    private String route;

    private int workedHoursInShift;

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assigment> assigments = new ArrayList<>();

    public Shift(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getWorkedHoursInShift() {
        return workedHoursInShift;
    }

    public void setWorkedHoursInShift(int workedHoursInShift) {
        this.workedHoursInShift = workedHoursInShift;
    }

    public List<Assigment> getAssigments() {
        return assigments;
    }

    public void setAssigments(List<Assigment> assigments) {
        this.assigments = assigments;
    }

}
