package com.huisarts.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(AutorisatieKey.class)
@Table(name = "autorisaties")
public class Autorisatie implements Serializable {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String autorisatie;

    public Autorisatie() {}
    public Autorisatie(String username, String autorisatie) {
        this.username = username;
        this.autorisatie = autorisatie;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAutorisatie() {
        return autorisatie;
    }
    public void setAutorisatie(String autorisatie) {
        this.autorisatie = autorisatie;
    }

}
