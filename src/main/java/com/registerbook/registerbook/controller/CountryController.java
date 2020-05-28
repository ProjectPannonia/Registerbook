package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.model.entities.Country;
import com.registerbook.registerbook.service.countries.CountryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/register/country")
public class CountryController {

    private CountryServiceImplementation countryServiceImplementation;

    @Autowired
    public void setCountryServiceImplementation(CountryServiceImplementation countryServiceImplementation){
        this.countryServiceImplementation = countryServiceImplementation;
    }

    @GetMapping("/getAllCountries")
    public ResponseEntity<List<Country>> loadCountriesToTheServer() {
        List<Country> result = countryServiceImplementation.loadCountriesToTheServer();
        return new ResponseEntity<List<Country>>(result, HttpStatus.OK);
    }

    @GetMapping("/getCountries")
    public ResponseEntity<String[]> getListOfCountries(){
        String[] result = countryServiceImplementation.getListOfCountries();
        return new ResponseEntity<String[]>(result,HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllCountries")
    public void deleteRegisteredCountries() {
        countryServiceImplementation.deleteAllCountries();
    }

    @DeleteMapping("/drop")
    public void drop(){
        countryServiceImplementation.dropTable();
    }
}
