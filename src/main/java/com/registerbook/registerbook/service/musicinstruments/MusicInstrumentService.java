package com.registerbook.registerbook.service.musicinstruments;

import com.registerbook.registerbook.model.entities.MusicInstrument;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MusicInstrumentService {

    List<MusicInstrument> getAllInstruments();

    List<MusicInstrument> isThisInstrumentAlreadyInDatabase(String instrumentName);
}
