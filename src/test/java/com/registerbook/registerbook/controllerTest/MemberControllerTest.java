package com.registerbook.registerbook.controllerTest;

import com.registerbook.registerbook.repository.model.Member;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

public class MemberControllerTest extends AbstractTest {

    public static Member lastMember;

    @Before
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void getMemberListTest() throws Exception {
        String url = "/register/member/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Member[] memberList = super.mapFromJson(content, Member[].class);
        assertTrue(memberList.length > 0);
    }
    @Test
    public void getMemberByIdTest()throws Exception{
        String url = "/register/member/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

        String returnedJson = mvcResult.getResponse().getContentAsString();
        System.out.println(returnedJson);
        Member testMember = super.mapFromJson(returnedJson,Member.class);
        assertNotEquals(null,testMember.getName());
        assertNotEquals(null,testMember.getBand());
        assertNotEquals(null,testMember.getCountry());
        assertNotEquals(null,testMember.getEmail());
        assertNotEquals(null,testMember.getAddress());
        assertNotEquals(0,testMember.getYearOfBirth());
        assertNotEquals(null,testMember.getInstrument());
    }

    @Test
    public void createNewMemberTest() throws Exception {
        Member testMember = new Member();
        testMember.setId(new Long(900));
        testMember.setName("Jucus");
        testMember.setBand("Amon Amarth");
        testMember.setAddress("Bszh");
        testMember.setEmail("ld@ld.hu");
        testMember.setYearOfBirth(1989);
        testMember.setCountry("Hungary");
        testMember.setInstrument("Guitar");

        String url = "/register/member/";
        String inputJson = super.mapToJson(testMember);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();
        lastMember = super.mapFromJson(mvcResult.getResponse().getContentAsString(),Member.class);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }
    /*TO-DO*/
    /*
    * DELETE
    * UPDATE
    * GETBYID*/

    @Test
    public void updateMemberTest(){

    }



    @Test
    public void deleteMemberTest() throws Exception {
        System.out.println(lastMember);
        String url = "/register/member/" + lastMember.getId();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(url))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204,status);
    }

}
