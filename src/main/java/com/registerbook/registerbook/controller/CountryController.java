package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.repository.model.CountryEntity;
import com.registerbook.registerbook.service.countries.CountryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/register/country")
public class CountryController {

    private CountryServiceImplementation countryServiceImplementation;

    @Autowired
    public void setCountryServiceImplementation(CountryServiceImplementation countryServiceImplementation){
        this.countryServiceImplementation = countryServiceImplementation;
    }

    @GetMapping("/adminGuiRest")
    public ResponseEntity<List<CountryEntity>> loadCountriesToTheServer() {
        List<CountryEntity> result = countryServiceImplementation.loadCountriesToTheServer();
        return new ResponseEntity<List<CountryEntity>>(result, HttpStatus.OK);
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
