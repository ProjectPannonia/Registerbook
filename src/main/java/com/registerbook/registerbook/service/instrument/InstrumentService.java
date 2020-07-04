package com.registerbook.registerbook.service.instrument;

import com.registerbook.registerbook.model.MusicInstrument;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InstrumentService {

    ResponseEntity getAllInstruments();

    List<MusicInstrument> isThisInstrumentAlreadyInDatabase(String instrumentName);

    MusicInstrument findByName(String name);

    void deleteInstrument(String name);

    String clearTable();

    ResponseEntity<String> saveInstrumentIfNotExist(MusicInstrument musicInstrument);

    ResponseEntity findInstrumentById(Long id);
}
