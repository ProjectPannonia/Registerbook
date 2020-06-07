package com.registerbook.registerbook.controllerTests.junitTests;

import com.registerbook.registerbook.controller.CountryController;
import com.registerbook.registerbook.service.countries.CountryServiceImplementation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CountryControllerTest {

    @InjectMocks
    CountryController countryController;
    @Mock
    CountryServiceImplementation countryServiceImplementation;

    @Before
    public void init(){

    }

    @Test
    public void test_loadCountriesToTheServer() {

    }
    @Test
    public void test_getListOfCountries() {

    }
    @Test
    public void test_drop() {

    }

    @After
    public void setToNull(){

    }
}
