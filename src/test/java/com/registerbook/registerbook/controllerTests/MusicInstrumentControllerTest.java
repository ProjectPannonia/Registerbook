package com.registerbook.registerbook.controllerTests;

import com.registerbook.registerbook.controller.MusicInstrumentController;
import com.registerbook.registerbook.model.entities.MusicInstrument;
import com.registerbook.registerbook.service.musicinstruments.MusicInstrumentServiceImplementation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MusicInstrumentControllerTest {

    List<MusicInstrument> testEmptyResponse;
    List<MusicInstrument> testNonEmptyResponse;
    MusicInstrument testInstrument1;
    MusicInstrument testInstrument2;
    MusicInstrument testInstrument3;
    MusicInstrument testInstrument4;

    @InjectMocks
    MusicInstrumentController musicInstrumentController;
    @Mock
    MusicInstrumentServiceImplementation musicInstrumentServiceImplementation;
    @Mock
    MusicInstrument musicInstrument;

    @Before
    public void init(){
        testEmptyResponse = new ArrayList<>();
        testNonEmptyResponse = new ArrayList<>();

        testInstrument1.setId(new Long(0));
        testInstrument1.setInstrumentName("Guitar");
        testInstrument2.setId(new Long(1));
        testInstrument2.setInstrumentName("Drum");
        testInstrument3.setId(new Long(2));
        testInstrument3.setInstrumentName("Bass guitar");
        testInstrument4.setId(new Long(3));
        testInstrument4.setInstrumentName("Voice");
        
        testNonEmptyResponse.add(testInstrument1);
        testNonEmptyResponse.add(testInstrument2);
        testNonEmptyResponse.add(testInstrument3);
        testNonEmptyResponse.add(testInstrument4);
    }
    @Test
    public void testGetAllMembers(){

    }

    @After
    public void killObjects(){
        testEmptyResponse = null;
        testNonEmptyResponse = null;
    }
}
