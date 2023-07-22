package com.huisarts.demo.service;

import com.huisarts.demo.exceptions.UserNotFoundException;
import com.huisarts.demo.repository.AfspraakRepository;
import com.huisarts.demo.repository.HuisartsRepository;
import com.huisarts.demo.model.Huisarts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class HuisartsServiceImpl implements HuisartsService {

    @Autowired
    HuisartsRepository huisartsRepository;

    @Autowired
    AfspraakRepository afspraakRepository;


    @Override
    public Collection<Huisarts> getAllHuisartsen() { return huisartsRepository.findAll(); }

    @Override
    public Collection<Huisarts> getHuisarts(String naam) {
        if (naam.isEmpty()) {
            return huisartsRepository.findAll();
        }
        else {
            return huisartsRepository.findAllByNaam(naam);
        }
    }

    @Override
    public Huisarts getHuisartsById(long id) {
        if (!huisartsRepository.existsById(id)) { throw new UserNotFoundException(); }
        return huisartsRepository.findById(id).orElse(null);
    }

    @Override
    public long createHuisarts(Huisarts huisarts) {
        Huisarts storedHuisarts = huisartsRepository.save(huisarts);
        return storedHuisarts.getId();
    }

    @Override
    public void updateHuisarts(long id, Huisarts Huisarts) {
        if(!huisartsRepository.existsById(id)) {throw new UserNotFoundException(); }
        Huisarts storedHuisarts = huisartsRepository.findById(id).orElse(null);
        storedHuisarts.setNaam(Huisarts.getNaam());
        huisartsRepository.save(Huisarts);
    }

    @Override
    public void partialUpdateHuisarts(long id, Map<String, String> fields) {
        if (!huisartsRepository.existsById(id)) { throw new UserNotFoundException(); }
        Huisarts storedHuisarts = huisartsRepository.findById(id).orElse(null);
        for (String field : fields.keySet()) {
            switch (field) {
                case "name":
                    storedHuisarts.setNaam((String) fields.get(field));
                    break;
            }
        }
        huisartsRepository.save(storedHuisarts);

    }

    @Override
    public void deleteHuisarts(long id) {
        if(!huisartsRepository.existsById(id)) { throw new UserNotFoundException(); }
        huisartsRepository.deleteById(id);
    }




}
