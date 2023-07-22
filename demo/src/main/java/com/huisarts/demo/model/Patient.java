package com.huisarts.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String naam;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    Set<Afspraak> results;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Set<Afspraak> getResults() {
        return results;
    }

    public void setResults(Set<Afspraak> results) {
        this.results = results;
    }
}
