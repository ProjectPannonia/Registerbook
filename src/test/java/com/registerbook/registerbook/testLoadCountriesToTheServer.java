package com.registerbook.registerbook;

import com.registerbook.registerbook.model.CountryEntity;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.MemberServiceImplementation;
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
    MemberJpaRepository memberJpaRepository;
    @InjectMocks
    MemberServiceImplementation memberServiceImplementation;

    @Before
    public void init(){
        load = new ArrayList<>();
        isoCountries = Locale.getISOCountries();
    }

    @Test
    public void testLoadCountries(){
        load = memberServiceImplementation.loadCountriesToTheServer();
        for (CountryEntity c : load){
            System.out.println(c.getCountryName());
        }
    }
}
