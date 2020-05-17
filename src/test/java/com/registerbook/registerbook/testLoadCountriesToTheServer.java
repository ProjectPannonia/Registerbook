package com.registerbook.registerbook;

import com.registerbook.registerbook.model.CountryEntity;
import com.registerbook.registerbook.repository.CountryJpaRepository;
import com.registerbook.registerbook.service.countries.CountryServiceImplementation;
import com.registerbook.registerbook.service.register.MemberServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RunWith(MockitoJUnitRunner.class)
public class testLoadCountriesToTheServer {

    List<CountryEntity> load;
    String[] isoCountries;

    @Mock
    CountryJpaRepository countryJpaRepository;
    @InjectMocks
    CountryServiceImplementation countryServiceImplementation;

    @Before
    public void init(){
        load = new ArrayList<>();
        isoCountries = Locale.getISOCountries();
    }

    @Test
    public void testLoadCountries(){
        load = countryServiceImplementation.loadCountriesToTheServer();
        for (CountryEntity c : load){
            System.out.println(c.getCountryName());
        }
    }
}
