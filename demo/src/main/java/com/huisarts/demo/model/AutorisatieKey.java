package com.huisarts.demo.model;

import java.io.Serializable;
import java.util.Objects;


//Serializable betekend dit een string maakt en dat string wordt gebruikt als aparte key
public class AutorisatieKey implements Serializable {
    private String username;
    private String autorisatie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorisatieKey that = (AutorisatieKey) o;
        return username.equals(that.username) &&
                autorisatie.equals(that.autorisatie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, autorisatie);
    }
}
