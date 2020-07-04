package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.model.MusicInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MusicInstrumentJpaRepository extends JpaRepository<MusicInstrument,Long> {

    List<MusicInstrument> findAll();

    @Override
    <S extends MusicInstrument> S save(S s);

    @Query(value = "SELECT s FROM MusicInstrument s WHERE s.instrumentName = :name")
    MusicInstrument getInstrumentByName(@Param("name") String name);

    @Query(value = "SELECT s FROM MusicInstrument s WHERE LOWER(s.instrumentName) = :name")
    MusicInstrument getInstrumentByInsensitiveCaseName(@Param("name") String name);

    @Query(value = "SELECT s FROM MusicInstrument s WHERE s.instrumentName = :name")
    List<MusicInstrument> getInstrumentsListByName(@Param("name") String name);

    @Transactional
    @Query(value = "DELETE FROM MusicInstrument s WHERE s.instrumentName = :name")
    @Modifying
    void deleteByName(@Param("name") String name);

    @Transactional
    @Query(value = "DELETE FROM MusicInstrument m")
    @Modifying
    void dropTable();

    @Query(value = "SELECT s FROM MusicInstrument s WHERE s.id = :id")
    MusicInstrument findInstrumentById(@Param("id") Long id);
}
