package com.huisarts.demo.service;

import com.huisarts.demo.exceptions.UserNotFoundException;
import com.huisarts.demo.repository.PatientRepository;
import com.huisarts.demo.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;


    @Override
    public Collection<Patient> getPatient() { return patientRepository.findAll(); }

    @Override
    public Collection<Patient> getPatient(String naam) {
        if (naam.isEmpty()) {
            return patientRepository.findAll();
        }
        else {
            return patientRepository.findAllByNaam(naam);
        }
    }

    @Override
    public Patient getPatientById(long id) {
        if (!patientRepository.existsById(id)) { throw new UserNotFoundException(); }
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public long createPatient(Patient patient) {
        Patient storedPatient = patientRepository.save(patient);
        return storedPatient.getId();
    }

    @Override
    public void updatePatient(long id, Patient patient) {
        if(!patientRepository.existsById(id)) {throw new UserNotFoundException(); }
        Patient storedPatient = patientRepository.findById(id).orElse(null);
        storedPatient.setNaam(patient.getNaam());
        patientRepository.save(patient);
    }

    @Override
    public void partialUpdatePatient(long id, Map<String, String> fields) {
        if (!patientRepository.existsById(id)) { throw new UserNotFoundException(); }
        Patient storedPatient = patientRepository.findById(id).orElse(null);
        for (String field : fields.keySet()) {
            switch (field) {
                case "name":
                    storedPatient.setNaam((String) fields.get(field));
                    break;
            }
        }
        patientRepository.save(storedPatient);

    }

    @Override
    public void deletePatient(long id) {
            if(!patientRepository.existsById(id)) { throw new UserNotFoundException(); }
            patientRepository.deleteById(id);
    }

}
