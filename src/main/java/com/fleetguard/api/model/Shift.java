package com.fleetguard.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date startTime;

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date endTime;

    @NotNull
    private String route;

    private int workedHoursInShift;

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<Assigment> assigments;

    public Shift(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
