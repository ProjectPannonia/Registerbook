package com.registerbook.registerbook.service.band;

import com.registerbook.registerbook.model.Band;
import com.registerbook.registerbook.model.UploadForm;
import com.registerbook.registerbook.model.testfile.TestUploadForm;
import com.registerbook.registerbook.repository.BandJpaRepository;
import com.registerbook.registerbook.service.band.resizeImage.ImageResizer;
import com.sun.xml.bind.v2.util.ByteArrayOutputStreamEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@Service
public class BandServiceImplementation implements BandService{

    @Autowired
    BandJpaRepository repository;

    @Override
    public ResponseEntity createBand(TestUploadForm form) {
        int scaledWidth = 1024;
        int scaledHeight = 768;
        boolean isItSaved = false;

        String bandName = form.getName();
        Band band = repository.getBandByName(bandName);

        if(band == null) {
            try {
                BufferedImage fileToImage = ImageResizer.resize(form.getPath(), scaledWidth, scaledHeight);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(fileToImage,"jpg",baos);
                baos.flush();
                byte[] imageBytes = baos.toByteArray();
                Band bandToSave = new Band();
                bandToSave.setName(form.getName());
                bandToSave.setImage(imageBytes);

                repository.save(bandToSave);
                isItSaved = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String response = isItSaved ? "The new band registered." : "The name is already exist!";
        HttpStatus responseStatus = isItSaved ? HttpStatus.OK : HttpStatus.CONFLICT;

        return new ResponseEntity(response,responseStatus);
    }

    @Override
    public ResponseEntity getAllBand() {
        List<Band> response = repository.findAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getBandById(Long id) {
        Band searchedBand = repository.getOne(id);
        UploadForm bandResponse = null;

        if(searchedBand != null){
            ByteArrayInputStream bais = new ByteArrayInputStream(searchedBand.getImage());
            BufferedImage bandLogo;

            try{
                bandLogo = ImageIO.read(bais);
                bandResponse = new UploadForm(searchedBand.getName(),bandLogo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //BufferedImage bandLogo = searchedBand.getImage();
        }
        return new ResponseEntity<UploadForm>(bandResponse,HttpStatus.OK);
    }

    @Override
    public String dropTable() {
        repository.deleteAll();
        return "Table cleared";
    }

    @Override
    public ResponseEntity getImageToHdd(Long id) {
        Band searchedBand = repository.getOne(id);
        String createHerePath = "D://TestFiles//";
        String createdFileName = searchedBand.getName() + ".txt";

        String bandName = searchedBand.getName();
        byte[] imageBytes = searchedBand.getImage();
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage reloadedImage = null;

        try {
            reloadedImage = ImageIO.read(bais);
            File outputFile = new File("C:/testfiles/" + searchedBand.getName());
            /*if(!outputFile.exists()){
                outputFile.createNewFile();
            }*/
            System.out.println(outputFile.exists());
            ImageIO.write(reloadedImage,"jpg",outputFile);
            Writer writer = new BufferedWriter(new FileWriter(outputFile));

            writer.close();
            bais.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        File outputfile = new File("image.jpg");
        ImageIO.write(bufferedImage, "jpg", outputfile);
        */

        return new ResponseEntity(reloadedImage,HttpStatus.OK);
    }
}
