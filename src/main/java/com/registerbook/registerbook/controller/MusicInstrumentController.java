package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.controller.errorHandler.apiError.ResourceNotFoundException;
import com.registerbook.registerbook.model.MusicInstrument;
import com.registerbook.registerbook.service.instrument.InstrumentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/register/instrument")
public class MusicInstrumentController {

    private InstrumentServiceImplementation instrumentServiceImplementation;

    @Autowired
    public void setInstrumentServiceImplementation(InstrumentServiceImplementation instrumentServiceImplementation){
        this.instrumentServiceImplementation = instrumentServiceImplementation;
    }
    @GetMapping("/{id}")
    public ResponseEntity<MusicInstrument> getInstrumentById(@PathVariable("id") final Long id) throws ResourceNotFoundException{
        return instrumentServiceImplementation.findInstrumentById(id);
    }
    @GetMapping("/getInstruments")
    public ResponseEntity<String[]> getAllInstruments(){
        ResponseEntity allInstruments =  instrumentServiceImplementation.getAllInstruments();

        return allInstruments;
    }
    @GetMapping("/dropInstruments")
    public ResponseEntity<String> dropInstrumentsTable(){
        String response = instrumentServiceImplementation.clearTable();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(value = "/createNewInstrument", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewInstrumentIfNotExist(@Valid @RequestBody final MusicInstrument musicInstrument) {
        return instrumentServiceImplementation.saveInstrumentIfNotExist(musicInstrument);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MusicInstrument> deleteMember(@PathVariable("id") final String name) {
        instrumentServiceImplementation.deleteInstrument(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
