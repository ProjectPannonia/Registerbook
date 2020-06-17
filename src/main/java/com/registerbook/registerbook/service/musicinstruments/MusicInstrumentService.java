package com.registerbook.registerbook.service.musicinstruments;

import com.registerbook.registerbook.model.entities.MusicInstrument;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MusicInstrumentService {

    ResponseEntity getAllInstruments();

    List<MusicInstrument> isThisInstrumentAlreadyInDatabase(String instrumentName);

    MusicInstrument findByName(String name);

    void deleteInstrument(String name);

    String clearTable();

    ResponseEntity<String> saveInstrumentIfNotExist(MusicInstrument musicInstrument);

    ResponseEntity findInstrumentById(Long id);
}
