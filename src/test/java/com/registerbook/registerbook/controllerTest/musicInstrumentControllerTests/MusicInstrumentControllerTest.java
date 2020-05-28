package com.registerbook.registerbook.controllerTest.musicInstrumentControllerTests;

import com.registerbook.registerbook.controllerTest.AbstractTest;
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
    public void A_createInstrumentTest() throws Exception {
        int status;
        String content;
        String url = "/register/musicinstrument/createNewInstruemnt";
        MusicInstrument testInstrument = new MusicInstrument();
        testInstrument.setInstrumentName("Guitar");
        content = super.mapToJson(testInstrument);

        mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andReturn();
        status = mvcResult.getResponse().getStatus();
        assertEquals(201,status);
    }
    @Test
    public void B_getAllInstrumentsTest() throws Exception {
        MusicInstrument[] allMusicInstrument;
        int status;
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
        for (MusicInstrument m : allMusicInstrument){
            System.out.println(m.getInstrumentName());
        }
    }
    @Test
    public void C_deleteInstrumentByNameTest() throws Exception {
        int status;
        String content;
        String url = "/register/musicinstrument/Guitar";
        mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();
        status = mvcResult.getResponse().getStatus();
        assertEquals(204,status);
    }
}