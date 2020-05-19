package com.registerbook.registerbook.controllerTest;

import com.registerbook.registerbook.repository.model.Member;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
    @Test
    public void createNewMemberTest() throws Exception{
        String url = "/register/member/";
        Member testMember = new Member();
        testMember.setId(new Long(1));
        testMember.setName("Adam");
        testMember.setBand("Amon Amarth");
        testMember.setAddress("Bszh");
        testMember.setEmail("ld@ld.hu");
        testMember.setYearOfBirth(1989);
        testMember.setCountry("Hungary");
        testMember.setInstrument("Guitar");

        String inputJson = super.mapToJson(testMember);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(409,status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"Product is created successfully");
    }
}
