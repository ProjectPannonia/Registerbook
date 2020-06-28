package JunitTests.MusicInstrumentTests.ServiceTests;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class GetAllInstrumentsTest {
    @Mock
    MusicInstrumentJpaRepository repo;
    @InjectMocks
    InstrumentServiceImplementation service;

    List<MusicInstrument> testResponseList;
    MusicInstrument testGuitar;
    MusicInstrument testDrum;
    MusicInstrument testBassGuitar;
    String[] responseArr;
    List<MusicInstrument> testResponseEmptyDb;

    @Before
    public void init(){

        testGuitar = new MusicInstrument();
        testGuitar.setInstrumentName("Guitar");

        testDrum = new MusicInstrument();
        testDrum.setInstrumentName("Drum");

        testBassGuitar = new MusicInstrument();
        testBassGuitar.setInstrumentName("Bass Guitar");

        testResponseList = new ArrayList<>();
        testResponseList.add(testGuitar);
        testResponseList.add(testBassGuitar);
        testResponseList.add(testDrum);

        responseArr = new String[3];
        responseArr[0] = "Bass Guitar";
        responseArr[1] = "Drum";
        responseArr[2] = "Guitar";

        testResponseEmptyDb = new ArrayList<>();
    }

    @Test
    public void test_withExistingData_getAllInstruments(){
        when(repo.findAll()).thenReturn(testResponseList);

        ResponseEntity response = service.getAllInstruments();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseBody = response.getBody();

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseBody instanceof String[]);

        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(((String[]) responseBody)[0],testBassGuitar.getInstrumentName());
        assertEquals(((String[]) responseBody)[1],testDrum.getInstrumentName());
        assertEquals(((String[]) responseBody)[2],testGuitar.getInstrumentName());

        verify(repo,times(1)).findAll();
    }
    /*@Test
    public void test_withEmptyDb_getAllInstruments(){
        when(repo.findAll()).thenReturn(null);
        ResponseEntity response = service.getAllInstruments();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseBody = response.getBody();

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertFalse(responseBody instanceof String[]);

        assertEquals(responseStatus,HttpStatus.NO_CONTENT);
        assertTrue(((ArrayList)responseBody).size() == 0);
    }*/
    @After
    public void setToNull(){
        repo = null;
        service = null;
        testResponseList = null;
        testGuitar = null;
        testDrum = null;
        testBassGuitar = null;
        responseArr = null;
    }
}
