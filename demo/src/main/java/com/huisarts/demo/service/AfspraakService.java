package com.huisarts.demo.service;

import com.huisarts.demo.model.Afspraak;
import com.huisarts.demo.model.AfspraakKey;

import java.util.Collection;

public interface AfspraakService {

    Collection<Afspraak> getAllAfspraken();
    Collection<Afspraak> getAllAfspraakByPatient(long patientId);
    Collection<Afspraak> getAllAfspraakByHuisarts(long huisartsId);
    Afspraak getAfspraakById(long patientId, long huisartsId);

    AfspraakKey createAfspraak(long patientId, long huisartsId, Afspraak result);
    void updateAfspraak(long patientId, long huisartsId, Afspraak result);
    void partialUpdateAfspraak(long patientId, long huisartsId, Afspraak result);
    void deleteAfspraak(long patientId, long huisartsId);

}
