package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.controller.errorHandler.customException.ResourceNotFoundException;
import com.registerbook.registerbook.service.countries.CountryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register/country")
public class CountryController {

    private CountryServiceImplementation countryServiceImplementation;

    @Autowired
    public void setCountryServiceImplementation(CountryServiceImplementation countryServiceImplementation){
        this.countryServiceImplementation = countryServiceImplementation;
    }

    @GetMapping("/getAllCountries")
    public ResponseEntity<String[]> loadCountriesToTheServer() {
        String[] result = countryServiceImplementation.loadCountriesToTheServer();
        if(result == null || result.length == 0){
            throw new ResourceNotFoundException("Countries table is empty!");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getCountries")
    public ResponseEntity<String[]> getListOfCountries(){
        String[] result = countryServiceImplementation.getListOfCountries();
        return new ResponseEntity<>(result,HttpStatus.OK);
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
