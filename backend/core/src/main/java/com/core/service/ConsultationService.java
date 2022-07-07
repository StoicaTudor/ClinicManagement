package com.core.service;

import com.core.dto.consultation.ConsultationCreationDto;
import com.core.dto.consultation.ConsultationGetDto;
import com.core.dto.consultation.ConsultationUpdateDto;
import com.core.mapper.ConsultationMapper;
import com.core.service.beforeAfterMapper.IBeforeAfterMapper;
import com.infrastructure.entity.Consultation;
import com.infrastructure.repository.ConsultationRepository;
import com.infrastructure.repository.PatientRepository;
import com.infrastructure.repository.UserRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan({"mapper"})
@ComponentScan({"repository"})
@ComponentScan({"service.before_after_mapper"})
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    private final ConsultationMapper consultationMapper;
    private final IBeforeAfterMapper<ConsultationCreationDto, Consultation> consultationCreationBeforeAfterMapper;
    private final IBeforeAfterMapper<ConsultationUpdateDto, Consultation> consultationUpdateBeforeAfterMapper;

    public ConsultationService(
            ConsultationRepository consultationRepository,
            UserRepository userRepository,
            PatientRepository patientRepository,
            ConsultationMapper consultationMapper,
            IBeforeAfterMapper<ConsultationCreationDto, Consultation> consultationCreationBeforeAfterMapper,
            IBeforeAfterMapper<ConsultationUpdateDto, Consultation> consultationUpdateBeforeAfterMapper) {

        this.consultationRepository = consultationRepository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.consultationMapper = consultationMapper;
        this.consultationCreationBeforeAfterMapper = consultationCreationBeforeAfterMapper;
        this.consultationUpdateBeforeAfterMapper = consultationUpdateBeforeAfterMapper;
    }

    public ConsultationGetDto create(ConsultationCreationDto consultationCreationDto) throws Exception {
        Consultation consultation =
                consultationRepository.save(
                        consultationCreationBeforeAfterMapper.after(
                                consultationCreationDto,
                                consultationMapper.consultationCreationDtoToConsultation(consultationCreationDto)));

        return consultationMapper.consultationToConsultationGetDto(consultation);
    }

    public ConsultationGetDto update(ConsultationUpdateDto consultationUpdateDto) throws Exception {
        Consultation consultation = consultationUpdateBeforeAfterMapper
                .after(
                        consultationUpdateDto,
                        consultationMapper.consultationUpdateDtoToConsultation(consultationUpdateDto));

        consultationRepository.save(consultation);

        return consultationMapper.consultationToConsultationGetDto(consultation);
    }

    public void delete(int consultationId) {
        consultationRepository.deleteById(consultationId);
    }

    public ConsultationGetDto getById(int id) throws Exception {
        return consultationMapper.consultationToConsultationDto(
                consultationRepository
                        .findById(id)
                        .orElseThrow(() -> new Exception("Consultation not found with id: " + id)));
    }

    public List<ConsultationGetDto> getByPatientId(int patientId) {
        return consultationRepository
                .findConsultationsByPatient_Id(patientId)
                .stream()
                .map(consultationMapper::consultationToConsultationDto)
                .collect(Collectors.toList());
    }
}
