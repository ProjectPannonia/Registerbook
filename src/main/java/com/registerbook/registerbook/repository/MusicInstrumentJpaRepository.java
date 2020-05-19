package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.repository.model.MusicInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicInstrumentJpaRepository extends JpaRepository<MusicInstrument,Long> {

    List<MusicInstrument> findAll();

    @Override
    <S extends MusicInstrument> S save(S s);

    @Query(value = "SELECT s FROM MusicInstrument s WHERE s.instrumentName = :name")
    List<MusicInstrument> getInstrumentByName(@Param("name") String name);
}
