package com.registerbook.registerbook.service.musicinstruments;

import com.registerbook.registerbook.model.entities.MusicInstrument;

import java.util.List;

public interface MusicInstrumentService {

    String[] getAllInstruments();

    List<MusicInstrument> isThisInstrumentAlreadyInDatabase(String instrumentName);

    MusicInstrument findByName(String name);

    void deleteInstrument(String name);

    String clearTable();

    String saveInstrumentIfNotExist(MusicInstrument musicInstrument);
}
