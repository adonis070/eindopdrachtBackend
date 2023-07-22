package com.huisarts.demo.controller;

import com.huisarts.demo.model.Huisarts;

import com.huisarts.demo.model.Afspraak;
import com.huisarts.demo.model.AfspraakKey;
import com.huisarts.demo.service.HuisartsService;
import com.huisarts.demo.service.AfspraakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/kappers")
public class HuisartsenController {

    @Autowired
    HuisartsService huisartsService;

    @Autowired
    AfspraakService afspraakService;



    //CRUD(Create Read Update Delete) Methodes****************************************************


    //Get all kappers ---------------------------------------------------------------------------

    @GetMapping(value = "")
    public ResponseEntity<Object> zoekHuisarts(@RequestParam(name = "naam", defaultValue = "") String naam) {
        return ResponseEntity.ok().body(huisartsService.getHuisarts(naam));
    }

    //Get Huisarts ---------------------------------------------------------------------------

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getHuisarts(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(huisartsService.getHuisartsById(id));
    }

    //Create kapper ---------------------------------------------------------------------------

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createHuisarts(@RequestBody Huisarts huisarts) {
        long newId = huisartsService.createHuisarts(huisarts);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    //Update kapper ---------------------------------------------------------------------------

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateHuisarts(@PathVariable("id") long id, @RequestBody Huisarts huisarts) {
        huisartsService.updateHuisarts(id, huisarts);
        return ResponseEntity.noContent().build();
    }

    //Partial update kapper ---------------------------------------------------------------------------

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> partialUpdateHuisarts(@PathVariable("id") long id, @RequestBody Map<String , String> fields) {
        huisartsService.partialUpdateHuisarts(id, fields);
        return ResponseEntity.noContent().build();
    }

    //Delete kapper ---------------------------------------------------------------------------

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteHuisarts(@PathVariable("id") long id) {
        huisartsService.deleteHuisarts(id);
        return ResponseEntity.noContent().build();
    }

    //Reserveration methoden***********************************************************************


    //Reservering ophalen------------------------------------------------------------------------

    @GetMapping(value = "/{huisarts_id}/patienten/{patient_id}")
    public ResponseEntity<Object> getAfspraak(@PathVariable("huisarts_id") long huisartsId,
                                              @PathVariable("patient_id") long patientId) {
        return ResponseEntity.ok().body(afspraakService.getAfspraakById(huisartsId, patientId));
    }

    //Reservering maken------------------------------------------------------------------------

    @PostMapping(value = "/{huisarts_id}/patienten/{patient_id}")
    public ResponseEntity<Object> makeAfspraak(@PathVariable("huisarts_id") long huisartsId,
                                               @PathVariable("patient_id") long patientId,
                                               @RequestBody Afspraak result) {
        AfspraakKey newId = afspraakService.createAfspraak(huisartsId, patientId, result);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(location);
    }

    //Reservering Verwijderen------------------------------------------------------------------------

    @DeleteMapping(value = "/{huisarts_id}/patienten/{patient_id}")
    public ResponseEntity<Object> deleteAfspraak(@PathVariable("huisarts_id") long huisartsId,
                                                 @PathVariable("patient_id") long patientId,
                                                 @RequestBody Afspraak result) {
        afspraakService.deleteAfspraak(huisartsId, patientId);
        return ResponseEntity.noContent().build();
    }

}
