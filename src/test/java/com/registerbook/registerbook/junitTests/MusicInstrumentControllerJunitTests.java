package com.registerbook.registerbook.junitTests;

import com.registerbook.registerbook.model.entities.MusicInstrument;
import com.registerbook.registerbook.repository.MusicInstrumentJpaRepository;
import com.registerbook.registerbook.service.musicinstruments.MusicInstrumentServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MusicInstrumentControllerJunitTests {

    @Mock
    MusicInstrumentJpaRepository musicInstrumentJpaRepository;

    @InjectMocks
    MusicInstrumentServiceImplementation musicInstrumentService;

    List<MusicInstrument> emptyResponse;
    List<MusicInstrument> nonEmptyResponse;
    MusicInstrument instrument1;
    MusicInstrument instrument2;
    MusicInstrument instrument3;
    MusicInstrument instrument4;

    @Before
    public void init(){
        emptyResponse = new ArrayList<>();

        instrument1 = new MusicInstrument();
        instrument2 = new MusicInstrument();
        instrument3 = new MusicInstrument();
        instrument4 = new MusicInstrument();

        nonEmptyResponse = new ArrayList<>();
        nonEmptyResponse.add(instrument1);
        nonEmptyResponse.add(instrument2);
        nonEmptyResponse.add(instrument3);
        nonEmptyResponse.add(instrument4);
    }

    @Test
    public void clearTable(){
        when((musicInstrumentJpaRepository.findAll())).thenReturn(emptyResponse).thenReturn(nonEmptyResponse);

        String expectedFirstResponse = "Database cleared";
        String responseWithEmptyDb = musicInstrumentService.clearTable();

        assertEquals(expectedFirstResponse,responseWithEmptyDb);

        String expectedSecondResponse = "Problem with clearing table!";
        String responseWithNonEmpty = musicInstrumentService.clearTable();

        assertEquals(expectedSecondResponse,responseWithNonEmpty);

        verify(musicInstrumentJpaRepository,times(2)).findAll();
    }
}
