package com.registerbook.registerbook.controllerTest;

import com.registerbook.registerbook.repository.model.Member;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberControllerTest extends AbstractTest{

    @Before
    public void setup(){
        super.setup();
    }

    @Test
    public void getMemberListTest() throws Exception{
        String url = "/register/member/";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();
        Member[] memberList = super.mapFromJson(content,Member[].class);
        assertTrue(memberList.length>0);
    }
}
