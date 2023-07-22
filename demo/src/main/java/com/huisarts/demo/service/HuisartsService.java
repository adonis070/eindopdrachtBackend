package com.huisarts.demo.service;

import com.huisarts.demo.model.Huisarts;

import java.util.Collection;
import java.util.Map;

public interface HuisartsService {

    Collection<Huisarts> getAllHuisartsen();
    Huisarts getHuisartsById(long id);
    Collection<Huisarts> getHuisarts(String naam);
    public abstract long createHuisarts(Huisarts huisarts);
    public abstract void updateHuisarts(long id, Huisarts huisarts);
    void partialUpdateHuisarts(long id, Map<String, String> fields);
    public abstract void deleteHuisarts(long id);


}
