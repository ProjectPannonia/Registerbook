package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.controller.errorHandler.apiError.ResourceNotFoundException;
import com.registerbook.registerbook.model.Country;
import com.registerbook.registerbook.service.country.CountryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register/country")
public class CountryController {

    private CountryServiceImplementation countryServiceImplementation;

    @Autowired
    public void setCountryServiceImplementation(CountryServiceImplementation countryServiceImplementation){
        this.countryServiceImplementation = countryServiceImplementation;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") final Long id) throws ResourceNotFoundException{

        return countryServiceImplementation.findCountryById(id);
    }
    @GetMapping("/getAllCountries")
    public ResponseEntity<String[]> loadCountriesToTheServer() {
        String[] result = countryServiceImplementation.loadCountriesToTheServer();

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
