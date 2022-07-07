package com.core.mapper;

import com.core.dto.consultation.ConsultationCreationDto;
import com.core.dto.consultation.ConsultationGetDto;
import com.core.dto.consultation.ConsultationUpdateDto;
import com.infrastructure.entity.Consultation;
import com.infrastructure.repository.PatientRepository;
import com.infrastructure.repository.UserRepository;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    Consultation consultationDtoToConsultation(ConsultationGetDto consultationGetDto);

    ConsultationGetDto consultationToConsultationDto(Consultation consultation);

    Consultation consultationCreationDtoToConsultation(ConsultationCreationDto consultationCreationDto);

    Consultation consultationUpdateDtoToConsultation(ConsultationUpdateDto consultationUpdateDto);

    ConsultationGetDto consultationToConsultationGetDto(Consultation consultation);
}
