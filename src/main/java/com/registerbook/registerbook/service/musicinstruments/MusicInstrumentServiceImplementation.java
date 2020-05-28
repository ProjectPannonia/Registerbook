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
    public String[] getAllInstruments() {
        List<MusicInstrument> instrumentList = musicInstrumentJpaRepository.findAll();
        String[] instrumentArray = new String[instrumentList.size()];
        for (int i = 0; i < instrumentArray.length; i++)
            instrumentArray[i] = instrumentList.get(i).getInstrumentName();
        return instrumentArray;
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

    public void Save(MusicInstrument musicInstrument) {
        musicInstrumentJpaRepository.save(musicInstrument);
    }

}
