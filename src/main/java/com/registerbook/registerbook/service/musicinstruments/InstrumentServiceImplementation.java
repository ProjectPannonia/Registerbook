package com.registerbook.registerbook.service.musicinstruments;

import com.registerbook.registerbook.model.MusicInstrument;
import com.registerbook.registerbook.repository.MusicInstrumentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InstrumentServiceImplementation implements InstrumentService{

    @Autowired
    MusicInstrumentJpaRepository musicInstrumentJpaRepository;

    @Override
    public List<MusicInstrument> getAllInstruments() {
        return musicInstrumentJpaRepository.findAll();
    }

    public void Save(MusicInstrument musicInstrument) {
        musicInstrumentJpaRepository.save(musicInstrument);
    }

}
