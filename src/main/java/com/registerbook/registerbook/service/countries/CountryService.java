package com.registerbook.registerbook.service.countries;

import com.registerbook.registerbook.repository.model.CountryEntity;

import java.util.List;

public interface CountryService {
    List<CountryEntity> loadCountriesToTheServer();
    void deleteAllCountries();
    void dropTable();
    String[] getListOfCountries();
}
