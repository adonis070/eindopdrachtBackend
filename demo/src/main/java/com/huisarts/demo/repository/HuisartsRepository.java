package com.huisarts.demo.repository;

import com.huisarts.demo.model.Huisarts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface HuisartsRepository extends JpaRepository<Huisarts, Long> {
        Collection<Huisarts> findAllByNaam(String naam);
}

