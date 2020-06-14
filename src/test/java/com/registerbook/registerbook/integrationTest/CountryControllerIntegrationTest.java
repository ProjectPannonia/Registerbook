package com.registerbook.registerbook.integrationTest;

import com.registerbook.registerbook.model.entities.Country;
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
public class CountryControllerIntegrationTest extends AbstractTest {

    private static MvcResult mvcResult;

    @Before
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void A_getCountriesTest() throws Exception{
        Country[] countryList;
        int status;
        String content;
        String url = "/register/country/getCountries";

        mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

        content = mvcResult.getResponse().getContentAsString();
        countryList = super.mapFromJson(content, Country[].class);
        assertTrue(countryList.length > 0);
    }

}
