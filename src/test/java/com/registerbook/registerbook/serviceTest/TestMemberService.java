package com.registerbook.registerbook.serviceTest;

import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.register.MemberServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestMemberService {

    @Mock
    MemberJpaRepository mjp;

    @InjectMocks
    MemberServiceImplementation msi;

    protected ArrayList<Member> emptyResponse;
    protected ArrayList<Member> normalResponse;
    protected Member testMember1;
    protected Member testMember2;
    protected Member testMember3;

    @Before
    public void init(){
        emptyResponse = new ArrayList<>();
        testMember1 = new Member();
        testMember1.setId(new Long(1));
        testMember1.setName("Ádám");
        testMember1.setInstrument("Guitar");
        testMember1.setCountry("Hungary");
        testMember1.setBand("Amon Amarth");
        testMember1.setEmail("ld@gm.hu");
        testMember1.setYearOfBirth(1989);
        testMember1.setAddress("BSZH");

        testMember2 = new Member();
        testMember2.setId(new Long(2));
        testMember2.setName("Józsi");
        testMember2.setInstrument("Drum");
        testMember2.setCountry("Hungary");
        testMember2.setBand("Amon Amarth");
        testMember2.setEmail("ld@gm.hu");
        testMember2.setYearOfBirth(1989);
        testMember2.setAddress("BSZH");

        testMember3 = new Member();
        testMember3.setId(new Long(3));
        testMember3.setName("Béla");
        testMember3.setInstrument("Bass Guitar");
        testMember3.setCountry("Sweden");
        testMember3.setBand("Testband");
        testMember3.setEmail("ld@test.hu");
        testMember3.setYearOfBirth(1900);
        testMember3.setAddress("Budatest");

        normalResponse = new ArrayList<>();
        normalResponse.add(testMember1);
        normalResponse.add(testMember2);
        normalResponse.add(testMember3);

    }

    @Test
    public void testGetAllMembersWithEmptyDatabase(){
        HttpStatus expectedResponseStatus = HttpStatus.NO_CONTENT;
        List<Member> expectedListOfMembers = new ArrayList<>();
        when(mjp.findAll()).thenReturn(emptyResponse);

        ResponseEntity allRegisteredMembers = msi.getAllRegisteredMembers();

        assertEquals(expectedResponseStatus,allRegisteredMembers.getStatusCode());
        assertEquals(expectedListOfMembers,allRegisteredMembers.getBody());
        assertNotEquals(null,allRegisteredMembers.getBody());
    }
    @Test
    public void testGetAllMembersWithNotEmptyDatabase(){
        when(mjp.findAll()).thenReturn(normalResponse);
        HttpStatus expectedResponseStatus = HttpStatus.OK;
        List<Member> notExpectedList = new ArrayList<>();
        ResponseEntity responseFromDb = msi.getAllRegisteredMembers();
        assertEquals(expectedResponseStatus,responseFromDb.getStatusCode());
        assertNotEquals(HttpStatus.NO_CONTENT,responseFromDb.getStatusCode());
    }
}
