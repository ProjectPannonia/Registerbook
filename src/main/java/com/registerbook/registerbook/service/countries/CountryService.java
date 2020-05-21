package com.registerbook.registerbook.service.countries;

import com.registerbook.registerbook.model.entities.Country;

import java.util.List;

public interface CountryService {
    List<Country> loadCountriesToTheServer();
    void deleteAllCountries();
    void dropTable();
    String[] getListOfCountries();
}
