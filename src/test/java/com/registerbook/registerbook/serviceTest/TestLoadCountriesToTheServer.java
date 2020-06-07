package com.registerbook.registerbook.serviceTest;

import com.registerbook.registerbook.repository.CountryJpaRepository;
import com.registerbook.registerbook.service.countries.CountryServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Locale;

@RunWith(MockitoJUnitRunner.class)
public class TestLoadCountriesToTheServer {

    String[] load;
    String[] isoCountries;

    @Mock
    CountryJpaRepository countryJpaRepository;
    @InjectMocks
    CountryServiceImplementation countryServiceImplementation;

    @Before
    public void init(){
        //load = String[];
        isoCountries = Locale.getISOCountries();
    }

    @Test
    public void testLoadCountries(){
        load = countryServiceImplementation.loadCountriesToTheServer();
        for (String c : load){
            //System.out.println(c.getCountryName());
        }
    }
}
