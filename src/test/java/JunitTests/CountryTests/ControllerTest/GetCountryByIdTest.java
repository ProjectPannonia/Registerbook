package JunitTests.CountryTests.ControllerTest;

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
public class GetCountryByIdTest {

    @Mock
    CountryServiceImplementation countryServiceImplementation;
    @InjectMocks
    CountryController countryController;

    @Before
    public void init(){

    }
    @Test
    public void test_getCountryById(){
        //when(countryServiceImplementation.findCountryById(anyLong())).thenReturn();
    }
    @After
    public void setToNull(){
        countryServiceImplementation = null;
        countryController = null;
    }
}
