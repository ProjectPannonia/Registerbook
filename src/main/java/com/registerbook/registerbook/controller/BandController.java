package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.model.Band;
import com.registerbook.registerbook.model.BandNameAndLogo;
import com.registerbook.registerbook.service.band.BandServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("register/band")
public class BandController {

    private BandServiceImplementation bandServiceImplementation;

    @Autowired
    public void setBandServiceImplementation(BandServiceImplementation bandServiceImplementation){
        this.bandServiceImplementation = bandServiceImplementation;
    }

    @GetMapping("/")
    public ResponseEntity<List<Band>> getAllBands(){
        return bandServiceImplementation.getAllBand();
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBand(@RequestBody final String name, final String imagePath){
        bandServiceImplementation.createBand(name,imagePath);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BandNameAndLogo> getBandById(@PathVariable("id") final Long id){
        ResponseEntity result = bandServiceImplementation.getBandById(id);
        return result;
    }
    @DeleteMapping("/delete")
    public String dropTable(){
        return bandServiceImplementation.dropTable();
    }
}
