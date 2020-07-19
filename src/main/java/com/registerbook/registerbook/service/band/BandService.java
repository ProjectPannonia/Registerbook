package com.registerbook.registerbook.service.band;

import org.springframework.http.ResponseEntity;

import java.awt.image.BufferedImage;

public interface BandService {
    void createBand(String bandName, String imagePath);
    ResponseEntity getAllBand();

    ResponseEntity getBandById(Long id);

    String dropTable();
}
