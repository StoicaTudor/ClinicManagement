package com.core.dto.patient;

import com.sun.istack.NotNull;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class PatientGetDto {

    @NotNull
    private int id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 1, max = 20)
    private String personalNumericalCode;

    @NotNull
    private LocalDateTime birthDate;

    @NotNull
    @Size(min = 3, max = 50)
    private String address;

    public int getId() {
        return id;
    }

    public PatientGetDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PatientGetDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPersonalNumericalCode() {
        return personalNumericalCode;
    }

    public PatientGetDto setPersonalNumericalCode(String personalNumericalCode) {
        this.personalNumericalCode = personalNumericalCode;
        return this;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public PatientGetDto setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PatientGetDto setAddress(String address) {
        this.address = address;
        return this;
    }
}
