package com.core.service;

import com.core.dto.patient.PatientCreationDto;
import com.core.dto.patient.PatientGetDto;
import com.core.dto.patient.PatientUpdateDto;
import com.core.mapper.PatientMapper;
import com.infrastructure.entity.Patient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.infrastructure.repository.PatientRepository;

@Service
@ComponentScan({"mapper"})
@ComponentScan({"repository"})
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public PatientGetDto getPatientById(int id) {
        return patientMapper.patientToPatientDto(patientRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found by id " + id)));
    }

    public PatientGetDto getPatientByName(String name) {
        return patientMapper.patientToPatientGetDto(patientRepository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException("Patient not found by name " + name)));
    }

    public PatientGetDto createPatient(PatientCreationDto patientCreationDto) {
        Patient patient = patientMapper.patientCreationDtoToPatient(patientCreationDto);
        patientRepository.save(patient);
        return patientMapper.patientToPatientGetDto(patient);
    }

    public PatientGetDto updatePatient(PatientUpdateDto patientUpdateDto) {
        Patient patient = patientMapper.patientUpdateDtoToPatient(patientUpdateDto);
        patientRepository.save(patient);
        return patientMapper.patientToPatientGetDto(patient);
    }
}
