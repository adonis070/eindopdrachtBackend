package com.huisarts.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "afspraak")
public class Afspraak {

    @EmbeddedId
    private AfspraakKey id;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @ManyToOne
    @MapsId("huisartsId")
    @JoinColumn(name = "huisarts_id")
    private Huisarts huisarts;

    @Column
    private LocalDate date;

    public Afspraak() {
        date = LocalDate.now();
    }
    public AfspraakKey getId() { return id; }
    public void setId(AfspraakKey id) {
        this.id = id;
    }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Huisarts getHuisarts() { return huisarts; }
    public void setHuisarts(Huisarts huisarts) { this.huisarts = huisarts; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

}
