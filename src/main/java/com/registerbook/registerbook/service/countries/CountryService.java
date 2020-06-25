package com.registerbook.registerbook.service.countries;

import com.registerbook.registerbook.model.entities.Country;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CountryService {
    String[] loadCountriesToTheServer();
    void deleteAllCountries();
    void dropTable();
    String[] getListOfCountries();
    ResponseEntity<Country> findCountryById(Long id);
}
