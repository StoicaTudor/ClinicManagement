package com.infrastructure.repository;

import com.infrastructure.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    List<Consultation> findConsultationsByPatient_Id(Integer id);

    void deleteById(Integer id);
}
