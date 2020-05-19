package com.registerbook.registerbook.service.musicinstruments;

import com.registerbook.registerbook.repository.model.MusicInstrument;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MusicInstrumentService {

    List<MusicInstrument> getAllInstruments();

    List<MusicInstrument> isThisInstrumentAlreadyInDatabase(String instrumentName);
}
