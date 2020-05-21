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
    public ResponseEntity<List<MusicInstrument>> getAllIstruments(){
        List<MusicInstrument> allInstruments =  instrumentServiceImplementation.getAllInstruments();
        return new ResponseEntity<List<MusicInstrument>>(allInstruments, HttpStatus.OK);
    }

    @PostMapping(value = "/createNewInstruemnt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MusicInstrument> createMember(@Valid @RequestBody final MusicInstrument musicInstrument) {
        //logger.info("Creating music instrument: {}", musicInstrument);
        if (instrumentServiceImplementation.isThisInstrumentAlreadyInDatabase(musicInstrument.getInstrumentName()) != null) {
            //return new ResponseEntity<MusicInstrument>(new CustomErrorType("Unable to create new music instrument. A music instrument with name: " + musicInstrument.getInstrumentName() + " already exist."),HttpStatus.CONFLICT);
        }
        instrumentServiceImplementation.Save(musicInstrument);

        return new ResponseEntity<>(musicInstrument, HttpStatus.CREATED);
    }
}
