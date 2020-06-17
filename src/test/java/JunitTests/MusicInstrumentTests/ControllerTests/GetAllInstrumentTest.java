package JunitTests.MusicInstrumentTests.ControllerTests;

import com.registerbook.registerbook.controller.MusicInstrumentController;
import com.registerbook.registerbook.controller.errorHandler.customException.ResourceNotFoundException;
import com.registerbook.registerbook.service.musicinstruments.MusicInstrumentServiceImplementation;
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
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class GetAllInstrumentTest {

    @Mock
    MusicInstrumentServiceImplementation musicInstrumentServiceImplementation;
    @InjectMocks
    MusicInstrumentController musicInstrumentController;

    ResponseEntity responseFromDbIsEmpty;
    ResponseEntity responseFromDbIsNotEmpty;
    String[] allInstruments;

    @Before
    public void init(){
        responseFromDbIsEmpty = new ResponseEntity(null,null,HttpStatus.NO_CONTENT);

        allInstruments = new String[2];
        allInstruments[0] = "Guitar";
        allInstruments[1] = "Drum";
        responseFromDbIsNotEmpty = new ResponseEntity(allInstruments,HttpStatus.OK);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_getAllIstruments_When_DbIsNotEmpty(){
        when(musicInstrumentServiceImplementation.getAllInstruments()).thenReturn(responseFromDbIsEmpty);

        ResponseEntity response = musicInstrumentController.getAllInstruments();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseListOfInstruments = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseListOfInstruments == null);

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseListOfInstruments instanceof String[]);

        assertEquals(responseStatus,HttpStatus.NO_CONTENT);
        assertEquals(responseListOfInstruments,responseFromDbIsEmpty.getBody());

        verify(musicInstrumentServiceImplementation,times(1)).getAllInstruments();
    }

    @Test
    public void test_getAllInstruments_When_DbIsNotEmpty(){
        when(musicInstrumentServiceImplementation.getAllInstruments()).thenReturn(responseFromDbIsNotEmpty);

        ResponseEntity response = musicInstrumentController.getAllInstruments();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseInstrumentList = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseInstrumentList != null);

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseInstrumentList instanceof String[]);

        verify(musicInstrumentServiceImplementation,times(1)).getAllInstruments();
    }
    
    @After
    public void setToNull(){
        musicInstrumentServiceImplementation = null;
        musicInstrumentController = null;
        responseFromDbIsEmpty = null;
    }
}
