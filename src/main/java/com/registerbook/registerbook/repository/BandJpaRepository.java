package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BandJpaRepository extends JpaRepository<Band,Long> {

/*
* @Query(value = "SELECT s FROM Member s WHERE s.name = :sName")
    Member findMemberByName(@Param("sName") String sName);*/
    @Query(value = "SELECT s FROM Band s WHERE s.name = :sName")
    Band getBandByName(@Param("sName")String bandName);
}
