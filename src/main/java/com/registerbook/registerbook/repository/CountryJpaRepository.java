package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryJpaRepository extends JpaRepository<CountryEntity,Long> {

    <S extends List<CountryEntity>> S saveAndFlush(S s);

    @Override
    <S extends CountryEntity> S save(S s);

    @Override
    List<CountryEntity> findAll();

    @Override
    void deleteAll();
}
