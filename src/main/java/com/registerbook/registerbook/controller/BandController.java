package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.model.Band;
import com.registerbook.registerbook.model.UploadForm;
import com.registerbook.registerbook.model.testfile.TestUploadForm;
import com.registerbook.registerbook.service.band.BandServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    public ResponseEntity createBand(@RequestBody final TestUploadForm form){

        ResponseEntity response = bandServiceImplementation.createBand(form);
        System.out.println("A visszatérő status code" + response.getStatusCode());
        System.out.println("A visszatérő body: " + response.getBody());
        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UploadForm> getBandById(@PathVariable("id") final Long id){
        ResponseEntity result = bandServiceImplementation.getBandById(id);
        return result;
    }
    @GetMapping("/reloadImageFromServer/{id}")
    public ResponseEntity<UploadForm> reloadImageFromServer(@PathVariable("id") final Long id){
        ResponseEntity response = bandServiceImplementation.getImageToHdd(id);
        return response;
    }
    @DeleteMapping("/delete")
    public String dropTable(){
        return bandServiceImplementation.dropTable();
    }
}
