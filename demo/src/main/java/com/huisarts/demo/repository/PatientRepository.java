package com.huisarts.demo.repository;

import com.huisarts.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Collection<Patient> findAllByNaam(String naam);

}
