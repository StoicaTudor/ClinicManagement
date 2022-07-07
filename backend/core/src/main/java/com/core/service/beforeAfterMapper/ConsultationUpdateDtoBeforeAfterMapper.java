package com.core.service.beforeAfterMapper;

import com.core.dto.consultation.ConsultationUpdateDto;
import com.infrastructure.entity.Consultation;
import com.infrastructure.repository.PatientRepository;
import com.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultationUpdateDtoBeforeAfterMapper implements IBeforeAfterMapper<ConsultationUpdateDto, Consultation>{

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public ConsultationUpdateDtoBeforeAfterMapper(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Consultation before(ConsultationUpdateDto consultationUpdateDto) {
        return null;
    }

    @Override
    public Consultation after(ConsultationUpdateDto consultationUpdateDto, Consultation partiallyMappedDestination) throws Exception {

        partiallyMappedDestination.setPatient(patientRepository
                        .findById(consultationUpdateDto.getPatientId())
                        .orElseThrow(() -> new Exception("Patient not found")))
                .setDoctor(userRepository
                        .findById(consultationUpdateDto.getDoctorId())
                        .orElseThrow(() -> new Exception("Patient not found")));

        return partiallyMappedDestination;
    }
}
