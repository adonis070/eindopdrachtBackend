package com.huisarts.demo.service;

import com.huisarts.demo.model.Patient;

import java.util.Collection;
import java.util.Map;

public interface PatientService {

    Collection<Patient> getPatient();
    Patient getPatientById(long id);
    Collection<Patient> getPatient(String naam);
    public abstract long createPatient(Patient patient);
    public abstract void updatePatient(long id, Patient patient);
    void partialUpdatePatient(long id, Map<String, String> fields);
    public abstract void deletePatient(long id);


}
