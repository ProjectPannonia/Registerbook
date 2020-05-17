package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryJpaRepository extends JpaRepository<CountryEntity,Long> {

    @Override
    <S extends CountryEntity> S save(S s);

    @Override
    List<CountryEntity> findAll();

    @Query(value = "TRUNCATE TABLE country",nativeQuery = true)
    void clearTable();

    @Query(value = "DROP TABLE country",nativeQuery = true)
    void dropTable();

    @Query(value = "SELECT * FROM country",nativeQuery = true)
    List<CountryEntity> getAllCountries();
}
