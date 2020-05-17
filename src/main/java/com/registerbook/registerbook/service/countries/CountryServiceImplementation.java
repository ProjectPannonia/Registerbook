package com.registerbook.registerbook.service.countries;

import com.registerbook.registerbook.model.CountryEntity;
import com.registerbook.registerbook.repository.CountryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class CountryServiceImplementation implements CountryService {

    @Autowired
    CountryJpaRepository countryJpaRepository;

    @Override
    public List<CountryEntity> loadCountriesToTheServer() {
        List<CountryEntity> countriesAlreadyOnServer = countryJpaRepository.findAll();
        if(countriesAlreadyOnServer.isEmpty()) {
            String[] isoCountries = Locale.getISOCountries();
            for (String country : isoCountries) {
                Locale locale = new Locale("en", country);
                String countryName = locale.getDisplayCountry();
                if (!countriesAlreadyOnServer.contains(countryName)) {
                    countriesAlreadyOnServer.add(new CountryEntity(countryName));
                    countryJpaRepository.save(new CountryEntity(countryName));
                }
            }
        }
        return countryJpaRepository.findAll();
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
        List<CountryEntity> allCountries = countryJpaRepository.getAllCountries();
        int lengthOfArray = allCountries.size();
        String[] countries = new String[lengthOfArray];
        for (int i = 0; i < countries.length;i++){
            countries[i] = allCountries.get(i).getCountryName();
        }
        Arrays.sort(countries);
        return countries;
    }
}
