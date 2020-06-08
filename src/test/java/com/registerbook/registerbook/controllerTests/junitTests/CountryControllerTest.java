package com.registerbook.registerbook.controllerTests.junitTests;

import com.registerbook.registerbook.controller.CountryController;
import com.registerbook.registerbook.model.entities.Country;
import com.registerbook.registerbook.repository.CountryJpaRepository;
import com.registerbook.registerbook.service.countries.CountryServiceImplementation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CountryControllerTest {

    String[] isoCountries;
    List<Country> nonEmptyResponseCountriesAlreadyOnServer;
    List<Country> emptyResponseCountriesAlreadyOnServer;
    String[] responseArray;

    @InjectMocks
    CountryController countryController;
    @Mock
    CountryServiceImplementation countryServiceImplementation;
    @Mock
    CountryJpaRepository countryJpaRepository;

    @Before
    public void init(){
        emptyResponseCountriesAlreadyOnServer = new ArrayList<>();
        isoCountries = Locale.getISOCountries();
        nonEmptyResponseCountriesAlreadyOnServer = new ArrayList<>();
        for (String country : isoCountries) {
            Locale locale = new Locale("en", country);
            String countryName = locale.getDisplayCountry();
            if (!nonEmptyResponseCountriesAlreadyOnServer.contains(countryName)) {
                nonEmptyResponseCountriesAlreadyOnServer.add(new Country(countryName));
                countryJpaRepository.save(new Country(countryName));
            }
        }

        responseArray = new String[3];
        responseArray[0] = "Hungary";
        responseArray[1] = "Germany";
        responseArray[2] = "England";
    }

    @Test
    public void test_loadCountriesToTheServer() {
        when(countryServiceImplementation.loadCountriesToTheServer()).thenReturn(responseArray);

        ResponseEntity response = countryController.loadCountriesToTheServer();
        Object responseBody = response.getBody();
        HttpStatus responseStatus = response.getStatusCode();

        assertNotEquals(responseBody,null);
        assertNotEquals(responseStatus,null);
        assertEquals(responseBody,responseArray);
        assertEquals(responseStatus,HttpStatus.OK);

        verify(countryServiceImplementation,times(1)).loadCountriesToTheServer();
    }

    @Test
    public void test_getListOfCountries() {

    }
    @Test
    public void test_drop() {

    }

    @After
    public void setToNull(){
        isoCountries = null;
        responseArray = null;
        nonEmptyResponseCountriesAlreadyOnServer = null;
        emptyResponseCountriesAlreadyOnServer = null;
    }
}
