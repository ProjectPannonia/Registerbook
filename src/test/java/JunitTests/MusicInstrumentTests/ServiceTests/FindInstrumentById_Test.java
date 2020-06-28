package JunitTests.MusicInstrumentTests.ServiceTests;

import com.registerbook.registerbook.controller.errorHandler.apiError.ResourceNotFoundException;
import com.registerbook.registerbook.model.entities.MusicInstrument;
import com.registerbook.registerbook.repository.MusicInstrumentJpaRepository;
import com.registerbook.registerbook.service.musicinstruments.InstrumentServiceImplementation;
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
public class FindInstrumentById_Test {
    @Mock
    MusicInstrumentJpaRepository repo;
    @InjectMocks
    InstrumentServiceImplementation service;

    HttpStatus expectedStatus;
    MusicInstrument expectedInstrument;
    ResponseEntity expectedResponseEntity;

    @Before
    public void init(){
        expectedStatus = HttpStatus.OK;
        expectedInstrument = new MusicInstrument();
        expectedInstrument.setInstrumentName("Guitar");
        expectedResponseEntity = new ResponseEntity(expectedInstrument,expectedStatus);
    }
    @Test
    public void test_withAnExistingId_findInstrumentById(){
        when(repo.findInstrumentById(new Long(1))).thenReturn(expectedInstrument);
        ResponseEntity response = service.findInstrumentById(new Long(1));
        HttpStatus responseStatus = response.getStatusCode();
        Object responseInstrument = response.getBody();

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseInstrument instanceof MusicInstrument);
        assertEquals(responseStatus,expectedStatus);
        assertEquals(responseInstrument,expectedInstrument);

        verify(repo,times(1)).findInstrumentById(new Long(1));
    }
    @Test(expected = ResourceNotFoundException.class)
    public void test_throw_ResourceNotFound_findInstrumentById(){
        when(repo.findInstrumentById(anyLong())).thenReturn(null);
        ResponseEntity response = service.findInstrumentById(new Long(1));
        HttpStatus responseStatus = response.getStatusCode();
        Object responseInstrument = response.getBody();
        verify(repo,times(1)).findInstrumentById(anyLong());
    }
    @After
    public void setToNull(){
        repo = null;
        service = null;
        expectedStatus = null;
        expectedResponseEntity = null;
        expectedInstrument = null;
    }
}
