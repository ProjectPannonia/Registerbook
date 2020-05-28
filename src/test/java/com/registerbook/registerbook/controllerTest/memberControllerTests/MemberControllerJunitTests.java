package com.registerbook.registerbook.controllerTest.memberControllerTests;

import com.registerbook.registerbook.controllerTest.AbstractTest;
import com.registerbook.registerbook.model.entities.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MemberControllerJunitTests extends AbstractTest {

    private static Member testMemberToGet;
    private static Member testMemberToCreate;
    private static Member testMemberToUpdate;
    private static Member testMemberToDelete;

    @Before
    public void setup()throws Exception{

        super.setup();

        testMemberToGet = new Member();
        testMemberToGet.setName("Create test Bela");
        testMemberToGet.setAddress("BudaTest");
        testMemberToGet.setBand("TestBand");
        testMemberToGet.setEmail("testb@test.com");
        testMemberToGet.setYearOfBirth(1985);
        testMemberToGet.setCountry("TestCountry");
        testMemberToGet.setInstrument("TestGuitar");

        testMemberToCreate = new Member();
        testMemberToCreate.setName("Create test Bela");
        testMemberToCreate.setAddress("BudaTest");
        testMemberToCreate.setBand("TestBand");
        testMemberToCreate.setEmail("testb@test.com");
        testMemberToCreate.setYearOfBirth(1985);
        testMemberToCreate.setCountry("TestCountry");
        testMemberToCreate.setInstrument("TestGuitar");

        testMemberToUpdate = new Member();
        testMemberToUpdate.setName("Update test Janos");
        testMemberToUpdate.setAddress("BudaTest");
        testMemberToUpdate.setBand("TestBand");
        testMemberToUpdate.setEmail("testb@test.com");
        testMemberToUpdate.setYearOfBirth(1985);
        testMemberToUpdate.setCountry("TestCountry");
        testMemberToUpdate.setInstrument("TestGuitar");

        testMemberToDelete = new Member();
        testMemberToDelete.setName("Delete test Imre");
        testMemberToDelete.setAddress("BudaTest");
        testMemberToDelete.setBand("TestBand");
        testMemberToDelete.setEmail("testb@test.com");
        testMemberToDelete.setYearOfBirth(1985);
        testMemberToDelete.setCountry("TestCountry");
        testMemberToUpdate.setInstrument("TestGuitar");
    }

    @Test
    public void testGetAllMembers() throws Exception {
        String url = "/register/member/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int responseStatus = mvcResult.getResponse().getStatus();

        assertEquals(200, responseStatus);

        String responseContent = mvcResult
                .getResponse()
                .getContentAsString();

        Member[] memberList = super.mapFromJson(responseContent, Member[].class);
        assertTrue(memberList.length > 0);

        for (Member m : memberList){
            assertNotEquals(null,m);
        }
    }
    @Test
    public void testCreateMember() throws Exception {
        String url = "/register/member/";

        String inputJson = super.mapToJson(testMemberToCreate);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        Member responseFromDatabaseMember = super.mapFromJson(mvcResult.getResponse()
                .getContentAsString(),Member.class);

        int status = mvcResult.getResponse().getStatus();

        assertEquals(201, status);
    }
    @Test
    public void testGetMemberById(){

    }
    @Test
    public void testDeleteMemberById(){

    }
    @After
    public void destroy(){
        testMemberToCreate = null;
        testMemberToUpdate = null;
        testMemberToDelete = null;
    }
}
