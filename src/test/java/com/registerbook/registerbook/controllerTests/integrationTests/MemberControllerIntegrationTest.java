package com.registerbook.registerbook.controllerTests.integrationTests;

import com.registerbook.registerbook.model.entities.Member;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberControllerIntegrationTest extends AbstractTest {

    private static Member lastMember;
    private static MvcResult mvcResult;

    @Before
    public void setup() throws Exception {
        super.setup();
    }
//Teszt eset szerinti elnevezés
    @Test
    public void A_testGetAllMembers() throws Exception {
        Member[] memberList;
        String content;
        int status;
        String url = "/register/member/";

        mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(200, status);

        content = mvcResult
                .getResponse()
                .getContentAsString();

        memberList = super.mapFromJson(content, Member[].class);
        assertTrue(memberList.length > 0);
    }

    @Test
    public void B_testGetMemberById()throws Exception{
        String url = "/register/member/3";
        int status;
        String returnedJson;
        Member testMember;

        mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        status = mvcResult
                .getResponse()
                .getStatus();

        returnedJson = mvcResult
                .getResponse()
                .getContentAsString();

        testMember = super.mapFromJson(returnedJson,Member.class);

        assertNotEquals(null,testMember.getName());
        assertNotEquals(null,testMember.getBand());
        assertNotEquals(null,testMember.getCountry());
        assertNotEquals(null,testMember.getEmail());
        assertNotEquals(null,testMember.getAddress());
        assertNotEquals(0,testMember.getYearOfBirth());
        assertNotEquals(null,testMember.getInstrument());
        assertEquals(200,status);
    }

    @Test
    public void C_testCreateNewMember() throws Exception {
        String url = "/register/member/";
        int status;
        Member testMember = new Member();

        testMember.setId(52L);
        testMember.setName("XYZ");
        testMember.setBand("TestBand");
        testMember.setAddress("Bszh");
        testMember.setEmail("ld@ld.hu");
        testMember.setYearOfBirth(1989);
        testMember.setCountry("Hungary");
        testMember.setInstrument("Guitar");

        String inputJson = super.mapToJson(testMember);
        System.out.println("InputJson: " + inputJson);
        mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        String mResult = mvcResult.getResponse().getContentAsString();
        System.out.println("Ez jött vissza: " + mResult);
        lastMember = super.mapFromJson(mvcResult.getResponse()
                .getContentAsString(),Member.class);

        System.out.println("Ezzel az id-vel lett elmentve"+lastMember.getId());
        status = mvcResult
                .getResponse()
                .getStatus();

        assertEquals(201, status);
    }

    @Test
    public void D_testUpdateExistingMember() throws Exception{
        String url = "/register/member/" + lastMember.getId() ;
        Member testMember = new Member();

        testMember.setId(new Long(999));
        testMember.setName("ModifiedTestBand");
        testMember.setBand("Amon Amarth");
        testMember.setAddress("Bszh");
        testMember.setEmail("ld@ld.hu");
        testMember.setYearOfBirth(1989);
        testMember.setCountry("Hungary");
        testMember.setInstrument("Guitar");

        String inputJson = super.mapToJson(testMember);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .put(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andReturn();

        lastMember = super.mapFromJson(mvcResult
                .getResponse()
                .getContentAsString(),Member.class);

        int status = mvcResult
                .getResponse()
                .getStatus();

        assertEquals(200,status);
    }

    @Test
    public void E_testDeleteMember() throws Exception {
        String url = "/register/member/" + lastMember.getId();
        int status;

        mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(url))
                .andReturn();

        status = mvcResult
                .getResponse()
                .getStatus();

        assertEquals(204,status);
    }
}
