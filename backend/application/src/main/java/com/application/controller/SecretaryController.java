package com.application.controller;

import com.core.dto.consultation.ConsultationCreationDto;
import com.core.dto.consultation.ConsultationGetDto;
import com.core.dto.consultation.ConsultationUpdateDto;
import com.core.dto.patient.PatientCreationDto;
import com.core.dto.patient.PatientGetDto;
import com.core.dto.patient.PatientUpdateDto;
import com.core.service.ConsultationService;
import com.core.service.PatientService;
import com.core.service.doctorNotification.IUserNotifier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMappings.SECRETARY)
@RequiredArgsConstructor
@CrossOrigin
public class SecretaryController {

    private final ConsultationService consultationService;
    private final PatientService patientService;
    private final IUserNotifier doctorNotificationService;

    @PostMapping(UrlMappings.CREATE_CONSULTATION)
    public ResponseEntity<?> createConsultation(@RequestBody ConsultationCreationDto consultationCreationDto) {
        try {
            return ResponseEntity.ok(consultationService.create(consultationCreationDto));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping(UrlMappings.GET_CONSULTATIONS_BY_PATIENT_ID)
    public List<ConsultationGetDto> getConsultationsByPatientId(@RequestParam("patientId") int patientId) {
        return consultationService
                .getByPatientId(patientId);
    }

    @PostMapping(UrlMappings.UPDATE_CONSULTATION)
    public ResponseEntity<?> updateConsultation(@RequestBody ConsultationUpdateDto consultationUpdateDto) {
        try {
            ConsultationGetDto consultationGetDto = consultationService.update(consultationUpdateDto);
            return ResponseEntity.ok(consultationGetDto);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping(UrlMappings.DELETE_CONSULTATION)
    public void deleteConsultation(@RequestParam("consultationId") int consultationId) {
        consultationService.delete(consultationId);
    }

    @PostMapping(UrlMappings.CREATE_PATIENT)
    public PatientGetDto createPatient(@RequestBody PatientCreationDto patientCreationDtoDto) {
        return patientService.createPatient(patientCreationDtoDto);
    }

    @PostMapping(UrlMappings.UPDATE_PATIENT)
    public PatientGetDto updatePatient(@RequestBody PatientUpdateDto patientUpdateDto) {
        return patientService.updatePatient(patientUpdateDto);
    }
}
