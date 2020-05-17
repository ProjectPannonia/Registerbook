package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.model.MusicInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicInstrumentJpaRepository extends JpaRepository<MusicInstrument,Long> {

    @Query(value = "SELECT * FROM MUSICINSTRUMENT")
    List<MusicInstrument> findAll();

    @Override
    <S extends MusicInstrument> S save(S s);
}
