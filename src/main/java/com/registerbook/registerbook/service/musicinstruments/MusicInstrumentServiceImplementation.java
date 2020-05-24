package com.registerbook.registerbook.service.musicinstruments;

import com.registerbook.registerbook.model.entities.MusicInstrument;
import com.registerbook.registerbook.repository.MusicInstrumentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicInstrumentServiceImplementation implements MusicInstrumentService {

    @Autowired
    MusicInstrumentJpaRepository musicInstrumentJpaRepository;

    @Override
    public List<MusicInstrument> getAllInstruments() {
        return musicInstrumentJpaRepository.findAll();
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

    public void Save(MusicInstrument musicInstrument) {
        musicInstrumentJpaRepository.save(musicInstrument);
    }

}
