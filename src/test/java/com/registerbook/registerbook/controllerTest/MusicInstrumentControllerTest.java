package com.registerbook.registerbook.controllerTest;

import com.registerbook.registerbook.model.entities.MusicInstrument;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MusicInstrumentControllerTest extends AbstractTest {

    MvcResult mvcResult;

    @Before
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void A_getAllInstrumentsTest() throws Exception {
        int status;
        MusicInstrument[] allMusicInstrument;
        String content;
        String url = "/register/musicinstrument/getInstruments";

        mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(200,status);

        content = mvcResult.getResponse()
                .getContentAsString();
        allMusicInstrument = super.mapFromJson(content,MusicInstrument[].class);
        assertTrue(allMusicInstrument.length > 0);
    }

}
