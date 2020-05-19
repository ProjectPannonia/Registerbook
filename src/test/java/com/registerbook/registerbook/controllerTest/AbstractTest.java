package com.registerbook.registerbook.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registerbook.registerbook.RegisterbookApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RegisterbookApplication.class)
@WebAppConfiguration
public class AbstractTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected void killObjects(){
        mvc = null;
        webApplicationContext = null;
    }

    protected String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }
    protected <T>T mapFromJson(String json, Class<T> clazz) throws JsonParseException,JsonMappingException, IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,clazz);
    }
}
