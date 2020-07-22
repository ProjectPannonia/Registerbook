package com.registerbook.registerbook.service.band;

import com.registerbook.registerbook.model.testfile.TestUploadForm;
import org.springframework.http.ResponseEntity;

import java.awt.image.BufferedImage;
import java.io.File;

public interface BandService {
    ResponseEntity createBand(TestUploadForm form);
    ResponseEntity getAllBand();

    ResponseEntity getBandById(Long id);

    String dropTable();

    ResponseEntity downloadImage(Long id);
}
