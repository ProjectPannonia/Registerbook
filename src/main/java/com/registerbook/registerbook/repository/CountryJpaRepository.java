package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.model.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryJpaRepository extends JpaRepository<Country,Long> {

    @Override
    <S extends Country> S save(S s);

    @Override
    List<Country> findAll();

    @Query(value = "TRUNCATE TABLE country",nativeQuery = true)
    void clearTable();

    @Query(value = "DROP TABLE country",nativeQuery = true)
    void dropTable();

    @Query(value = "SELECT * FROM country",nativeQuery = true)
    List<Country> getAllCountries();
}
