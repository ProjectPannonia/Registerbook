package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.controller.errorHandler.customException.ResourceNotFoundException;
import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.model.entities.MusicInstrument;
import com.registerbook.registerbook.service.musicinstruments.MusicInstrumentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/register/musicinstrument")
public class MusicInstrumentController {

    private MusicInstrumentServiceImplementation instrumentServiceImplementation;

    @Autowired
    public void setInstrumentServiceImplementation(MusicInstrumentServiceImplementation instrumentServiceImplementation){
        this.instrumentServiceImplementation = instrumentServiceImplementation;
    }
    @GetMapping("/getInstrumentById/{id}")
    public ResponseEntity<MusicInstrument> getInstrumentById(@PathVariable("id") final Long id){
        ResponseEntity result = instrumentServiceImplementation.findInstrumentById(id);
        if (result.getStatusCode() == HttpStatus.NOT_FOUND){
            throw new ResourceNotFoundException("Music instrument with this id: " + id + " not found!");
        }
        return result;
    }
    @GetMapping("/getInstruments")
    public ResponseEntity<String[]> getAllInstruments(){
        ResponseEntity allInstruments =  instrumentServiceImplementation.getAllInstruments();

        if(allInstruments.getBody() == null){
            throw new ResourceNotFoundException("Music instruments table is empty!");
        }

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
