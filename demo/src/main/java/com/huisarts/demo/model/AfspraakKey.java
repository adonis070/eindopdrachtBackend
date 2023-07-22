package com.huisarts.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
//Serializable betekend dit een string maakt en dat string wordt gebruikt als aparte key
public class AfspraakKey implements Serializable {

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "huisarts_id")
    private Long huisartsId;

    //Constructors--------------------------------------------------------------------

    public AfspraakKey() {}
    public AfspraakKey(long patientId, long huisartsId) {
        this.patientId = patientId;
        this.huisartsId = huisartsId;
    }

    //Getters en Setters---------------------------------------------------------------

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getHuisartsId() { return huisartsId; }
    public void setHuisartsId(Long huisartsId) { this.huisartsId = huisartsId; }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AfspraakKey that = (AfspraakKey) o;
        return patientId.equals(that.patientId) &&
                huisartsId.equals(that.huisartsId);
    }

    // hashcode
    @Override
    public int hashCode() {
        return Objects.hash(patientId, huisartsId);
    }

}

