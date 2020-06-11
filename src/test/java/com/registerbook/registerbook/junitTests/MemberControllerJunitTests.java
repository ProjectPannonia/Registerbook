package com.registerbook.registerbook.junitTests;

import com.registerbook.registerbook.controllerTests.integrationTests.AbstractTest;
import com.registerbook.registerbook.model.entities.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

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
    public void CreateUpdateDeleteTest() throws Exception {
        String urlToCreate = "/register/member/";

        String inputJson = super.mapToJson(testMemberToCreate);

        MvcResult mvcCreateResult = mvc.perform(MockMvcRequestBuilders
                .post(urlToCreate).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        Member responseFromDatabaseMember = super.mapFromJson(mvcCreateResult.getResponse()
                .getContentAsString(),Member.class);

        int responseStatus = mvcCreateResult
                .getResponse()
                .getStatus();

        assertTrue(responseFromDatabaseMember instanceof Member);
        assertEquals(201, responseStatus);

        // UPDATE test
        Long lastRegisteredId = responseFromDatabaseMember.getId();
        String urlToUpdate = "/register/member/" + lastRegisteredId;
        String inputToJsonUpdate = super.mapToJson(testMemberToUpdate);

        MvcResult mvcUpdateResult = mvc.perform(MockMvcRequestBuilders.put(urlToUpdate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputToJsonUpdate))
                .andReturn();

        Member memberAfterUpdate = super.mapFromJson(mvcUpdateResult
                .getResponse()
                .getContentAsString(),Member.class);
        assertTrue(memberAfterUpdate != null);
        assertTrue(memberAfterUpdate instanceof Member);
        assertEquals(testMemberToUpdate.getName(),memberAfterUpdate.getName());

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
