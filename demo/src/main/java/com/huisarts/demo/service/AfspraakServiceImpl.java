package com.huisarts.demo.service;

import com.huisarts.demo.exceptions.RecordNotFoundException;
import com.huisarts.demo.repository.AfspraakRepository;
import com.huisarts.demo.repository.HuisartsRepository;
import com.huisarts.demo.repository.PatientRepository;
import com.huisarts.demo.model.Huisarts;
import com.huisarts.demo.model.Patient;
import com.huisarts.demo.model.Afspraak;
import com.huisarts.demo.model.AfspraakKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AfspraakServiceImpl implements AfspraakService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    HuisartsRepository huisartsRepository;

    @Autowired
    AfspraakRepository afspraakRepository;


    @Override
    public Collection<Afspraak> getAllAfspraken() {
        return afspraakRepository.findAll();
    }

    @Override
    public Collection<Afspraak> getAllAfspraakByPatient(long patientId) {
        return afspraakRepository.findAllByPatientId(patientId);
    }

    @Override
    public Collection<Afspraak> getAllAfspraakByHuisarts(long huisartsId) {
        return afspraakRepository.findAllByHuisartsId(huisartsId);
    }

    @Override
    public Afspraak getAfspraakById(long patientId, long huisartsId) {
        return afspraakRepository.findById(new AfspraakKey(patientId, huisartsId)).orElse((null));
    }

    @Override
    public AfspraakKey createAfspraak(long patientId, long huisartsId, Afspraak result) {
        if (!patientRepository.existsById(patientId)) { throw new RecordNotFoundException(); }
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (!huisartsRepository.existsById(huisartsId)) { throw new RecordNotFoundException(); }
        Huisarts huisarts = huisartsRepository.findById(huisartsId).orElse(null);
        result.setPatient(patient);
        result.setHuisarts(huisarts);
        AfspraakKey id = new AfspraakKey(patientId, huisartsId);
        result.setId(id);
        afspraakRepository.save(result);
        return id;
    }


    @Override
    public void updateAfspraak(long patientId, long huisartsId, Afspraak result) {
        AfspraakKey id = new AfspraakKey(patientId, huisartsId);
        if (!afspraakRepository.existsById(id)) { throw new RecordNotFoundException(); }
        Afspraak existingResult = afspraakRepository.findById(id).orElse(null);
        existingResult.setDate(result.getDate());
        existingResult.setPatient(result.getPatient());
        existingResult.setHuisarts(result.getHuisarts());
        afspraakRepository.save(existingResult);

    }

    @Override
    public void partialUpdateAfspraak(long patientId, long huisartsId, Afspraak result) {

    }

    @Override
    public void deleteAfspraak(long patientId, long huisartsId) {
        AfspraakKey id = new AfspraakKey(patientId, huisartsId);
        if (!afspraakRepository.existsById(id)) { throw new RecordNotFoundException(); }
        afspraakRepository.deleteById(id);

    }
}
