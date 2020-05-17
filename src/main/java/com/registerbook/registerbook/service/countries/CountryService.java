package com.registerbook.registerbook.service.countries;

import com.registerbook.registerbook.model.CountryEntity;

import java.util.List;

public interface CountryService {
    List<CountryEntity> loadCountriesToTheServer();
    void deleteAllCountries();
    void dropTable();
    List<CountryEntity> getListOfCountries();
}
