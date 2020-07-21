package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.model.Band;
import com.registerbook.registerbook.model.UploadForm;
import com.registerbook.registerbook.model.testfile.TestUploadForm;
import com.registerbook.registerbook.service.band.BandServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
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
    public void createBand(@RequestBody final TestUploadForm form){
        System.out.println("A kapott name: " + form.getName());
        System.out.println("A kapott path: " + form.getPath());
        File file = new File(form.getPath());
        System.out.println(file != null);
        
        /*File file = new File(imagePath);
        String nameOfFile = file.getName();
        System.out.println("A feltöltött fáj neve: " + nameOfFile);
        bandServiceImplementation.createBand(name,imagePath);
        */

    }
    @GetMapping("/{id}")
    public ResponseEntity<UploadForm> getBandById(@PathVariable("id") final Long id){
        ResponseEntity result = bandServiceImplementation.getBandById(id);
        return result;
    }
    @DeleteMapping("/delete")
    public String dropTable(){
        return bandServiceImplementation.dropTable();
    }
}
