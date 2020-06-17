package JunitTests.MusicInstrumentTests.ControllerTests;

import com.registerbook.registerbook.controller.MusicInstrumentController;
import com.registerbook.registerbook.model.entities.MusicInstrument;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MusicInstrumentControllerTest {

    String[] testEmptyResponse;
    String[] testNonEmptyResponse;
    List<MusicInstrument> testInstrumentResponse;
    MusicInstrument testInstrumentToSave;

    @InjectMocks
    MusicInstrumentController musicInstrumentController;
    @Mock
    MusicInstrumentServiceImplementation musicInstrumentServiceImplementation;

    @Before
    public void init(){
        testEmptyResponse = new String[0];

        testNonEmptyResponse = new String[4];
        testNonEmptyResponse[0] = "Gutar";
        testNonEmptyResponse[1] = "Bass Guitar";
        testNonEmptyResponse[2] = "Drum";
        testNonEmptyResponse[3] = "Voice";

        testInstrumentResponse = new ArrayList<>();
        testInstrumentResponse.add(new MusicInstrument());
        testInstrumentResponse.add(new MusicInstrument());
        testInstrumentResponse.add(new MusicInstrument());
        testInstrumentResponse.add(new MusicInstrument());

        testInstrumentToSave = new MusicInstrument();
        testInstrumentToSave.setId(new Long(0));
        testInstrumentToSave.setInstrumentName("Guitar");
    }
    /*
    @Test
    public void testGetAllMembersNonEmptyResponse(){
        when(musicInstrumentServiceImplementation.getAllInstruments()).thenReturn(testNonEmptyResponse);
        ResponseEntity response = musicInstrumentController.getAllInstruments();
        Object responseBody = response.getBody();
        HttpStatus responseStatus = response.getStatusCode();

        assertNotEquals(responseBody,null);
        assertEquals(responseBody,testNonEmptyResponse);
        assertEquals(responseStatus, HttpStatus.OK);
        verify(musicInstrumentServiceImplementation,times(1)).getAllInstruments();
    }
    @Test
    public void testGetAllMembersEmptyResponse(){
        when(musicInstrumentServiceImplementation.getAllInstruments()).thenReturn(testEmptyResponse);
        ResponseEntity response = musicInstrumentController.getAllInstruments();
        Object responseBody = response.getBody();
        HttpStatus responseStatus = response.getStatusCode();

        assertNotEquals(responseBody,testNonEmptyResponse);
        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(responseBody,testEmptyResponse);

        verify(musicInstrumentServiceImplementation,times(1)).getAllInstruments();
        //verify(musicInstrumentJpaRepository,times(1)).findAll();
    }
    @Test
    public void testDropInstrumentsTableCleared(){
        when(musicInstrumentServiceImplementation.clearTable()).thenReturn("Database cleared!");

        String expectedResponseText = "Database cleared!";
        HttpStatus expectedStatus = HttpStatus.OK;

        ResponseEntity response = musicInstrumentController.dropInstrumentsTable();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseBody = response.getBody();

        assertNotEquals(responseBody,null);
        assertNotEquals(responseBody,"Problem with clearing table!");
        assertEquals(responseStatus,expectedStatus);
        assertEquals(responseBody,expectedResponseText);

        verify(musicInstrumentServiceImplementation,times(1)).clearTable();
    }
    @Test
    public void testDropInstrumentsTableProblemWithDrop() {
        when(musicInstrumentServiceImplementation.clearTable()).thenReturn("Problem with clearing table!");

        String expectedResponseText = "Problem with clearing table!";
        HttpStatus expectedStatus = HttpStatus.OK;

        ResponseEntity response = musicInstrumentController.dropInstrumentsTable();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseBody = response.getBody();

        assertNotEquals(responseBody,null);
        assertNotEquals(responseBody,"Database cleared!");
        assertEquals(responseStatus,expectedStatus);
        assertEquals(responseBody,expectedResponseText);

        verify(musicInstrumentServiceImplementation,times(1)).clearTable();
    }
    @Test
    public void testWithNonExistingInstrument_CreateNewInstrumentIfNotExist(){
        String expectedBody = "Music instrument saved: Guitar";
        HttpStatus expectedStatus = HttpStatus.CREATED;
        ResponseEntity expectedResponse = new ResponseEntity(expectedBody,expectedStatus);
        when(musicInstrumentServiceImplementation.saveInstrumentIfNotExist(any(MusicInstrument.class)))
                .thenReturn(expectedResponse);

        ResponseEntity response = musicInstrumentController.createNewInstrumentIfNotExist(testInstrumentToSave);
        Object responseBody = response.getBody();
        HttpStatus responseStatus = response.getStatusCode();
        Object notExpectedBody = "This instrument: " + testInstrumentToSave.getInstrumentName() + " already exist!";

        assertNotEquals(response,null);
        assertNotEquals(response.getBody(),notExpectedBody);

        assertEquals(expectedBody,responseBody);
        assertEquals(responseStatus,responseStatus);

        verify(musicInstrumentServiceImplementation,times(1)).saveInstrumentIfNotExist(any(MusicInstrument.class));
    }
    @Test
    public void testWithExistingInstrument_CreateNewInstrumentIfNotExist(){
        String expectedBody = "This instrument: " + testInstrumentToSave.getInstrumentName() + " already exist!";
        HttpStatus expectedStatus = HttpStatus.CREATED;
        ResponseEntity expectedResponse = new ResponseEntity(expectedBody,expectedStatus);
        when(musicInstrumentServiceImplementation.saveInstrumentIfNotExist(any(MusicInstrument.class)))
                .thenReturn(expectedResponse);

        Object notExpectedBody = "Music instrument saved: " + testInstrumentToSave.getInstrumentName();
        ResponseEntity response = musicInstrumentController.createNewInstrumentIfNotExist(testInstrumentToSave);
        Object responseBody = response.getBody();
        HttpStatus responseStatus = response.getStatusCode();

        assertNotEquals(response,null);
        assertNotEquals(response.getBody(),notExpectedBody);

        assertEquals(expectedBody,responseBody);
        assertEquals(responseStatus,responseStatus);

        verify(musicInstrumentServiceImplementation,times(1)).saveInstrumentIfNotExist(any(MusicInstrument.class));
    }

    @After
    public void setToNull(){
        testEmptyResponse = null;
        testNonEmptyResponse = null;
        testInstrumentResponse = null;
        testInstrumentResponse = null;
    }
    */

}
