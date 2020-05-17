package com.registerbook.registerbook.service.musicinstruments;

import com.registerbook.registerbook.model.MusicInstrument;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstrumentService {

    List<MusicInstrument> getAllInstruments();

}
