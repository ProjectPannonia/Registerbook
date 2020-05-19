package com.registerbook.registerbook.controllerTest;

import com.registerbook.registerbook.repository.model.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberControllerTest extends AbstractTest {

    MvcResult mvcResult;
    private int numberOfMembers;
    private int maxIndex;

    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void getMemberListTest() throws Exception {
        String url = "/register/member/";
        mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Member[] memberList = super.mapFromJson(content, Member[].class);
        numberOfMembers = memberList.length;
        maxIndex = numberOfMembers - 1;

        assertTrue(memberList.length > 0);
    }

    @Test
    public void createNewMemberTest() throws Exception {
        System.out.println("Az adatbázisban: " + numberOfMembers + " db.");
        System.out.println("Jelenlegi max index: " + maxIndex + ".");
        Member testMember = new Member();
        testMember.setId(new Long(numberOfMembers));
        System.out.println("Bemeneti member id: " + testMember.getId());
        testMember.setName("Bela");
        testMember.setBand("Amon Amarth");
        testMember.setAddress("Bszh");
        testMember.setEmail("ld@ld.hu");
        testMember.setYearOfBirth(1989);
        testMember.setCountry("Hungary");
        testMember.setInstrument("Guitar");
        String url = "/register/member/";

        String inputJson = super.mapToJson(testMember);
        mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(inputJson,content);
    }
    /*TO-DO*/
    /*
    * DELETE
    * UPDATE
    * GETBYID*/

}
