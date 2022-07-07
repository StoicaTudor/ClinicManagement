package com.core.mapper;

import com.core.dto.patient.PatientCreationDto;
import com.core.dto.patient.PatientGetDto;
import com.core.dto.patient.PatientUpdateDto;
import com.infrastructure.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientDtoToPatient(PatientGetDto patientGetDto);

    PatientGetDto patientToPatientDto(Patient patient);

    Patient patientCreationDtoToPatient(PatientCreationDto patientCreationDto);

    Patient patientUpdateDtoToPatient(PatientUpdateDto patientUpdateDto);

    PatientGetDto patientToPatientGetDto(Patient patient);
}
