package com.registerbook.registerbook.service.band;

import com.registerbook.registerbook.model.Band;
import com.registerbook.registerbook.model.UploadForm;
import com.registerbook.registerbook.repository.BandJpaRepository;
import com.registerbook.registerbook.service.band.resizeImage.ImageResizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class BandServiceImplementation implements BandService{

    @Autowired
    BandJpaRepository repository;

    @Override
    public void createBand(String bandName, String imagePath) {
        Band band = repository.getBandByName(bandName);
        int scaledWidth = 1024;
        int scaledHeight = 768;
        String fixedPath = "D:/ae.jpg";
        if(band == null){
            try {
                BufferedImage modifiedImage = ImageResizer.resize(fixedPath, scaledWidth, scaledHeight);
                /*
                * ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write( originalImage, "jpg", baos );
	            baos.flush();
	            byte[] imageInByte = baos.toByteArray();
	            baos.close();*/
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(modifiedImage,"jpg",baos);
                baos.flush();
                byte[] modifiedImageBytes = baos.toByteArray();
                Band bandToSave = new Band();
                bandToSave.setName(bandName);
                bandToSave.setImage(modifiedImageBytes);
                repository.save(bandToSave);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
}
