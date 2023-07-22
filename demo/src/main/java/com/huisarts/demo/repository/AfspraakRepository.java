package com.huisarts.demo.repository;

import com.huisarts.demo.model.Afspraak;
import com.huisarts.demo.model.AfspraakKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AfspraakRepository extends JpaRepository <Afspraak, AfspraakKey> {
    Collection<Afspraak> findAllByPatientId(long patientId);
    Collection<Afspraak> findAllByHuisartsId(long huisartsId);
}
