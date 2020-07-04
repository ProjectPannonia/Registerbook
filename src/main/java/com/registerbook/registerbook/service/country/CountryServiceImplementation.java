package com.registerbook.registerbook.service.country;

import com.registerbook.registerbook.controller.errorHandler.apiError.ResourceNotFoundException;
import com.registerbook.registerbook.model.Country;
import com.registerbook.registerbook.repository.CountryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class CountryServiceImplementation implements CountryService {

    @Autowired
    CountryJpaRepository countryJpaRepository;

    @Override
    public ResponseEntity<Country> findCountryById(Long id) {
        Country country = countryJpaRepository.findCountryById(id);
        if(country == null)
            throw new ResourceNotFoundException(Country.class,"id",id.toString());

        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @Override
    public String[] loadCountriesToTheServer() {
        List<Country> countriesAlreadyOnServer = countryJpaRepository.findAll();
        String[] countriesOnServerArray;

        if(countriesAlreadyOnServer.isEmpty()) {
            String[] isoCountries = Locale.getISOCountries();
            for (String country : isoCountries) {
                Locale locale = new Locale("en", country);
                String countryName = locale.getDisplayCountry();
                if (!countriesAlreadyOnServer.contains(countryName)) {
                    countriesAlreadyOnServer.add(new Country(countryName));
                    countryJpaRepository.save(new Country(countryName));
                }
            }
        }
        countriesOnServerArray = new String[countriesAlreadyOnServer.size()];
        for (int i = 0; i < countriesOnServerArray.length;i++)
            countriesOnServerArray[i] = countriesAlreadyOnServer.get(i).getCountryName();

        Arrays.sort(countriesOnServerArray);

        return countriesOnServerArray;
    }

    @Override
    public void deleteAllCountries() {
        countryJpaRepository.clearTable();
    }

    @Override
    public void dropTable() {
        countryJpaRepository.dropTable();
    }

    @Override
    public String[] getListOfCountries() {
        List<Country> allCountries = countryJpaRepository.getAllCountries();
        int lengthOfArray = allCountries.size();
        String[] countries = new String[lengthOfArray];
        for (int i = 0; i < countries.length;i++){
            countries[i] = allCountries.get(i).getCountryName();
        }
        Arrays.sort(countries);
        return countries;
    }

}
