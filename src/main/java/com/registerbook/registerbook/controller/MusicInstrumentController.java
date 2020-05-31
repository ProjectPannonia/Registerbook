package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.model.entities.MusicInstrument;
import com.registerbook.registerbook.service.musicinstruments.MusicInstrumentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/register/musicinstrument")
public class MusicInstrumentController {

    private MusicInstrumentServiceImplementation instrumentServiceImplementation;

    @Autowired
    public void setInstrumentServiceImplementation(MusicInstrumentServiceImplementation instrumentServiceImplementation){
        this.instrumentServiceImplementation = instrumentServiceImplementation;
    }

    @GetMapping("/getInstruments")
    public ResponseEntity<String[]> getAllIstruments(){
        String[] allInstruments =  instrumentServiceImplementation.getAllInstruments();
        return new ResponseEntity<>(allInstruments, HttpStatus.OK);
    }
    @GetMapping("/dropInstruments")
    public ResponseEntity<String> dropInstrumentsTable(){
        String response = instrumentServiceImplementation.clearTable();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(value = "/createNewInstrument", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewInstrumentIfNotExist(@Valid @RequestBody final MusicInstrument musicInstrument) {
        //String response = instrumentServiceImplementation.saveInstrumentIfNotExist(musicInstrument);
        return instrumentServiceImplementation.saveInstrumentIfNotExist(musicInstrument);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MusicInstrument> deleteMember(@PathVariable("id") final String name) {
        instrumentServiceImplementation.deleteInstrument(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
