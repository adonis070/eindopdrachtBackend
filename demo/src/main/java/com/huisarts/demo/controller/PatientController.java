package com.huisarts.demo.controller;

import com.huisarts.demo.model.Patient;
import com.huisarts.demo.model.Afspraak;
import com.huisarts.demo.model.AfspraakKey;
import com.huisarts.demo.service.PatientService;
import com.huisarts.demo.service.AfspraakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/patienten")
public class PatientController {



    @Autowired
    PatientService patientService;

    @Autowired
    AfspraakService afspraakService;

    //CRUD(Create Read Update Delete) Methodes****************************************************

    //Get klanten ---------------------------------------------------------------------------

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllHuisartsen(@RequestParam(name = "naam", defaultValue = "") String naam) {
        return ResponseEntity.ok().body(patientService.getPatient(naam));
    }

    //Get klant---------------------------------------------------------------------------

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getPatient(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(patientService.getPatientById(id));
    }

    //Create klant ---------------------------------------------------------------------------

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createPatient(@RequestBody Patient patient) {
        long newId = patientService.createPatient(patient);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    //Update klant---------------------------------------------------------------------------

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updatePatient(@PathVariable("id") long id, @RequestBody Patient patient) {
        patientService.updatePatient(id, patient);
        return ResponseEntity.noContent().build();
    }

    //Partial update klant ---------------------------------------------------------------------------

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> partialUpdatePatient(@PathVariable("id") long id, @RequestBody Map<String , String> fields) {
        patientService.partialUpdatePatient(id, fields);
        return ResponseEntity.noContent().build();
    }

    //Delete klant ---------------------------------------------------------------------------

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deletePatient(@PathVariable("id") long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    //Reserveration methoden***********************************************************************


    //Reservering ophalen------------------------------------------------------------------------

    @GetMapping(value = "/{patient_id}/huisartsen/{huisarts_id}")
    public ResponseEntity<Object> getAfspraak(@PathVariable("patient_id") long patienId,
                                              @PathVariable("huisarts_id") long huisartsId) {
        return ResponseEntity.ok().body(afspraakService.getAfspraakById(patienId, huisartsId));
    }

    //Reservering maken------------------------------------------------------------------------

    @PostMapping(value = "/{patient_id}/huisartsen/{huisarts_id}")
    public ResponseEntity<Object> makeAfspraak(@PathVariable("patient_id") long patientId,
                                               @PathVariable("huisarts_id") long huisartsId,
                                               @RequestBody Afspraak result) {
        AfspraakKey newId = afspraakService.createAfspraak(patientId, huisartsId, result);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(location);
    }

    //Reservering Verwijderen------------------------------------------------------------------------

    @DeleteMapping(value = "/{patient_id}/huisartsen/{huisarts_id}")
    public ResponseEntity<Object> deleteAfspraak(@PathVariable("patient_id") long patientId,
                                                 @PathVariable("huisarts_id") long huisartsId,
                                                 @RequestBody Afspraak result) {
        afspraakService.deleteAfspraak(patientId, huisartsId);
        return ResponseEntity.noContent().build();
    }


}
