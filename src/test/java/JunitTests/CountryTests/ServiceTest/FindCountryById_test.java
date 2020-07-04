package JunitTests.CountryTests.ServiceTest;

import com.registerbook.registerbook.controller.errorHandler.apiError.ResourceNotFoundException;
import com.registerbook.registerbook.model.entities.Country;
import com.registerbook.registerbook.repository.CountryJpaRepository;
import com.registerbook.registerbook.service.country.CountryServiceImplementation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FindCountryById_test {
    @Mock
    CountryJpaRepository repo;
    @InjectMocks
    CountryServiceImplementation service;

    Country responseCountry;

    @Before
    public void init(){
        responseCountry = new Country();
        responseCountry.setCountryName("Hungary");
    }
    @Test(expected = ResourceNotFoundException.class)
    public void test_expectingResourceNotFound_findCountryById(){
        when(repo.findCountryById(anyLong())).thenReturn(null);

        ResponseEntity response = service.findCountryById(new Long(1));
        HttpStatus responseStatus = response.getStatusCode();
        Object responseBody = response.getBody();

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseBody == null);

        verify(repo,times(1)).findCountryById(anyLong());
    }
    @Test
    public void test_withExistingId_findCountryById(){
        when(repo.findCountryById(new Long(1))).thenReturn(responseCountry);

        ResponseEntity response = service.findCountryById(new Long(1));
        HttpStatus responseStatus = response.getStatusCode();
        Object responseBody = response.getBody();

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseBody instanceof Country);

        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(responseBody,responseCountry);

        verify(repo,times(1)).findCountryById(new Long(1));
    }
    @After
    public void setToNull(){
        repo = null;
        service = null;
        responseCountry = null;
    }
}
