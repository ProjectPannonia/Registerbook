package com.registerbook.registerbook.service.country;

import com.registerbook.registerbook.model.entities.Country;
import org.springframework.http.ResponseEntity;

public interface CountryService {
    String[] loadCountriesToTheServer();
    void deleteAllCountries();
    void dropTable();
    String[] getListOfCountries();
    ResponseEntity<Country> findCountryById(Long id);
}
