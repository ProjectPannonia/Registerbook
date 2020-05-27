package com.registerbook.registerbook.serviceTest;

import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.register.MemberService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestMemberService {

    @Mock
    MemberJpaRepository memberJpaRepository;

    @InjectMocks
    MemberServiceImplementation memberService;

    protected ArrayList<Member> blankDbResponse;
    protected ArrayList<Member> nonBlankDbResponse;
    protected Member testMember1;
    protected Member testMember2;
    protected Member testMember3;

    @Before
    public void init(){
        blankDbResponse = new ArrayList<>();
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

        nonBlankDbResponse = new ArrayList<>();
        nonBlankDbResponse.add(testMember1);
        nonBlankDbResponse.add(testMember2);
        nonBlankDbResponse.add(testMember3);

    }

    @Test
    public void testGetAllMembersWithEmptyDatabase(){
        when(memberJpaRepository.findAll()).thenReturn(blankDbResponse);

        HttpStatus expectedResponseStatus = HttpStatus.NO_CONTENT;
        List<Member> expectedListOfMembers = new ArrayList<>();

        ResponseEntity allRegisteredMembers = memberService.getAllRegisteredMembers();

        assertEquals(expectedResponseStatus,allRegisteredMembers.getStatusCode());
        assertEquals(expectedListOfMembers,allRegisteredMembers.getBody());
        assertNotEquals(null,allRegisteredMembers.getBody());

        verify(memberJpaRepository,times(1)).findAll();
    }
    @Test
    public void testGetAllMembersWithNotEmptyDatabase(){
        when(memberJpaRepository.findAll()).thenReturn(nonBlankDbResponse);

        HttpStatus expectedResponseStatus = HttpStatus.OK;
        List<Member> notExpectedList = new ArrayList<>();

        ResponseEntity responseFromDb = memberService.getAllRegisteredMembers();

        assertEquals(expectedResponseStatus,responseFromDb.getStatusCode());
        assertNotEquals(HttpStatus.NO_CONTENT,responseFromDb.getStatusCode());
        assertNotEquals(notExpectedList,responseFromDb.getBody());

        verify(memberJpaRepository,times(1)).findAll();
    }
    @Test
    public void testWriteMembersToFileWithEmptyDb(){
        when(memberJpaRepository.findAll()).thenReturn(blankDbResponse);

        String expectedResponseText = "Database empty.";
        HttpStatus expectedStatus = HttpStatus.NO_CONTENT;

        ResponseEntity responseFromDb = memberService
                .writeMembersToFile("TestFilename");
        String resultResponseText = responseFromDb.getBody().toString();
        HttpStatus resultStatus = responseFromDb.getStatusCode();

        assertEquals(expectedResponseText,resultResponseText);
        assertEquals(expectedStatus,resultStatus);
        verify(memberJpaRepository,times(1)).findAll();
    }
    @Test
    public void testWriteMembersToFileWithNotEmptyDb(){
        when(memberJpaRepository.findAll()).thenReturn(nonBlankDbResponse);

        String testFileName = "TestFile";
        String expectedResponseText = "File created. Name: " + testFileName + ".txt";

        HttpStatus expectedStatus = HttpStatus.OK;
        ResponseEntity resultResponseFromDb = memberService
                .writeMembersToFile(testFileName);

        HttpStatus resultStatus = resultResponseFromDb.getStatusCode();
        String resultResponseText = resultResponseFromDb.getBody().toString();

        assertEquals(expectedResponseText,resultResponseText);
        assertEquals(expectedStatus,resultStatus);
        verify(memberJpaRepository,times(1)).findAll();
    }
    @Test
    public void testWithAnExistingMember_SaveNewMemberIfNotExist(){
        when(memberJpaRepository.findMemberByName("Ádám")).thenReturn(testMember1);

        HttpStatus expectedStatus = HttpStatus.CONFLICT;
        Member expectedMember = testMember1;

        ResponseEntity resultResponse = memberService.saveNewMemberIfNotExist(testMember1);
        HttpStatus resultStatus = resultResponse.getStatusCode();
        Member resultMember = (Member) resultResponse.getBody();

        assertEquals(expectedStatus,resultStatus);
        assertEquals(expectedMember,resultMember);

        verify(memberJpaRepository,times(1)).findMemberByName(anyString());
    }
    @Test
    public void testWithNonRegisteredMember_SaveNewMemberIfNotExist(){
        when(memberJpaRepository.findMemberByName(anyString())).thenReturn(null).thenReturn(testMember1);

        HttpStatus expectedStatus = HttpStatus.CREATED;
        Member expectedMember = testMember1;

        ResponseEntity resultResponse = memberService.saveNewMemberIfNotExist(testMember1);
        Member responseMemberFromDb = (Member) resultResponse.getBody();
        HttpStatus responseStatusFromDb = resultResponse.getStatusCode();

        assertEquals(testMember1.getName(),responseMemberFromDb.getName());
        assertEquals(testMember1.getCountry(),responseMemberFromDb.getCountry());
        assertEquals(testMember1.getBand(),responseMemberFromDb.getBand());
        assertEquals(testMember1.getAddress(),responseMemberFromDb.getAddress());
        assertEquals(testMember1.getEmail(),responseMemberFromDb.getEmail());
        assertEquals(testMember1.getInstrument(),responseMemberFromDb.getInstrument());
        assertEquals(testMember1.getYearOfBirth(),responseMemberFromDb.getYearOfBirth());

        assertEquals(expectedMember,responseMemberFromDb);
        assertEquals(expectedStatus,responseStatusFromDb);

        verify(memberJpaRepository,times(1)).save(testMember1);
        verify(memberJpaRepository,times(2)).findMemberByName(anyString());
    }
    @Test
    public void testWithNonExistMember_findMemberByIdIfExist(){
        when(memberJpaRepository.findMemberById(anyLong())).thenReturn(null);

        HttpStatus expectedResultStatus = HttpStatus.NOT_FOUND;

        ResponseEntity resultResponse = memberService.findMemberByIdIfExist(0L);
        HttpStatus responseStatus = resultResponse.getStatusCode();
        Member responseMember = (Member) resultResponse.getBody();

        assertEquals(expectedResultStatus,responseStatus);
        assertEquals(null,responseMember);

        verify(memberJpaRepository,times(1)).findMemberById(0L);
    }

}
