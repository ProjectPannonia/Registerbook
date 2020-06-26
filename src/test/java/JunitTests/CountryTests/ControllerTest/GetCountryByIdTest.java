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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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
        when(countryServiceImplementation.findCountryById(anyLong())).thenReturn(doThrow(new RuntimeException(anyString())));
    }
    @After
    public void setToNull(){
        countryServiceImplementation = null;
        countryController = null;
    }
}
