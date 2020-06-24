package com.registerbook.registerbook.service.musicinstruments;

import com.registerbook.registerbook.model.entities.MusicInstrument;
import com.registerbook.registerbook.repository.MusicInstrumentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicInstrumentServiceImplementation implements MusicInstrumentService {

    @Autowired
    MusicInstrumentJpaRepository musicInstrumentJpaRepository;

    @Override
    public ResponseEntity getAllInstruments() {
        List<MusicInstrument> instrumentList = musicInstrumentJpaRepository.findAll();

        if (instrumentList.size() == 0){
            return new ResponseEntity(null,HttpStatus.NO_CONTENT);
        }

        String[] instrumentArray = new String[instrumentList.size()];
        for (int i = 0; i < instrumentArray.length; i++)
            instrumentArray[i] = instrumentList.get(i).getInstrumentName();

        return new ResponseEntity(instrumentArray,HttpStatus.OK);
    }

    @Override
    public List<MusicInstrument> isThisInstrumentAlreadyInDatabase(String instrumentName) {
        return musicInstrumentJpaRepository.getInstrumentsListByName(instrumentName);
    }

    @Override
    public MusicInstrument findByName(String name) {
        return musicInstrumentJpaRepository.getInstrumentByName(name);
    }

    @Override
    public void deleteInstrument(String name) {
        musicInstrumentJpaRepository.deleteByName(name);
    }

    @Override
    public String clearTable() {
        musicInstrumentJpaRepository.dropTable();
        String response;
        int numberOfMembers = musicInstrumentJpaRepository.findAll().size();

        if (numberOfMembers == 0){
            response = "Database cleared";
        }else {
            response = "Problem with clearing table!";
        }

        return response;
    }

    @Override
    public ResponseEntity<String> saveInstrumentIfNotExist(MusicInstrument musicInstrument) {
        MusicInstrument isInstrumentExist = musicInstrumentJpaRepository.getInstrumentByInsensitiveCaseName(musicInstrument.getInstrumentName().toLowerCase());
        String response = "This instrument: " + musicInstrument.getInstrumentName() + " already exist!";

        if (isInstrumentExist == null) {
            musicInstrumentJpaRepository.save(musicInstrument);
            response = "Music instrument saved: " + musicInstrument.getInstrumentName();
        }

        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity findInstrumentById(Long id) {
        MusicInstrument searchedInstrument = musicInstrumentJpaRepository.findInstrumentById(id);
        HttpStatus responseStatus = (searchedInstrument == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity(searchedInstrument,responseStatus);
    }


    public void Save(MusicInstrument musicInstrument) {
        musicInstrumentJpaRepository.save(musicInstrument);
    }

}
