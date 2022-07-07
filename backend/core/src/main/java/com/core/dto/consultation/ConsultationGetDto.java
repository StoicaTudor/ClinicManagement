package com.core.dto.consultation;

import com.core.dto.patient.PatientGetDto;
import com.core.dto.user.UserDto;
import com.sun.istack.NotNull;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ConsultationGetDto {

    @NotNull
    private int id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private UserDto doctor;

    @NotNull
    private PatientGetDto patient;

    @Size(min = 10, max = 1000)
    private String details;

    public int getId() {
        return id;
    }

    public ConsultationGetDto setId(int id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ConsultationGetDto setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public UserDto getDoctor() {
        return doctor;
    }

    public ConsultationGetDto setDoctor(UserDto doctor) {
        this.doctor = doctor;
        return this;
    }

    public PatientGetDto getPatient() {
        return patient;
    }

    public ConsultationGetDto setPatient(PatientGetDto patient) {
        this.patient = patient;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public ConsultationGetDto setDetails(String details) {
        this.details = details;
        return this;
    }
}
