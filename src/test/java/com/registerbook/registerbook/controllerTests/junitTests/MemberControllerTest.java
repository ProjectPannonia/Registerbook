package com.registerbook.registerbook.controllerTests.junitTests;

import com.registerbook.registerbook.controller.MemberController;
import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.service.register.MemberServiceImplementation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MemberControllerTest {

    List<Member> responseFromDb;
    Member testM1;
    Member testM2;
    Member testM3;
    Member testM4;

    @Mock
    MemberServiceImplementation memberServiceImplementation;
    @InjectMocks
    MemberController memberController;

    @Before
    public void init(){

        testM1 = new Member();
        testM1.setName("Create test Bela");
        testM1.setAddress("BudaTest");
        testM1.setBand("TestBand");
        testM1.setEmail("testb@test.com");
        testM1.setYearOfBirth(1985);
        testM1.setCountry("TestCountry");
        testM1.setInstrument("TestGuitar");

        testM2 = new Member();
        testM2.setName("Create test Bela");
        testM2.setAddress("BudaTest");
        testM2.setBand("TestBand");
        testM2.setEmail("testb@test.com");
        testM2.setYearOfBirth(1985);
        testM2.setCountry("TestCountry");
        testM2.setInstrument("TestGuitar");

        testM3 = new Member();
        testM3.setName("Update test Janos");
        testM3.setAddress("BudaTest");
        testM3.setBand("TestBand");
        testM3.setEmail("testb@test.com");
        testM3.setYearOfBirth(1985);
        testM3.setCountry("TestCountry");
        testM3.setInstrument("TestGuitar");

        testM4 = new Member();
        testM4.setName("Delete test Imre");
        testM4.setAddress("BudaTest");
        testM4.setBand("TestBand");
        testM4.setEmail("testb@test.com");
        testM4.setYearOfBirth(1985);
        testM4.setCountry("TestCountry");
        testM4.setInstrument("TestGuitar");

        responseFromDb = new ArrayList<>();
        responseFromDb.add(testM1);
        responseFromDb.add(testM2);
        responseFromDb.add(testM3);
        responseFromDb.add(testM4);

    }
    @Test
    public void test_sendRegisteredMembersToFrontEnd(){
        when(memberServiceImplementation.getAllRegisteredMembers())
                .thenReturn(new ResponseEntity(responseFromDb, HttpStatus.OK))
                .thenReturn(new ResponseEntity(new ArrayList<Member>(),HttpStatus.NO_CONTENT));

        ResponseEntity nonEmptyResponse = memberController.sendRegisteredMembersToFrontEnd();
        HttpStatus expectedOkStatus = nonEmptyResponse.getStatusCode();
        Object expectedNonEmptyBody = nonEmptyResponse.getBody();

        assertNotEquals(expectedOkStatus,null);
        assertNotEquals(expectedNonEmptyBody,null);
        assertTrue(expectedOkStatus instanceof HttpStatus);
        assertTrue(expectedNonEmptyBody instanceof ArrayList);
        assertEquals(expectedOkStatus,HttpStatus.OK);
        assertEquals(expectedNonEmptyBody,responseFromDb);

        ResponseEntity emptyResponse = memberController.sendRegisteredMembersToFrontEnd();
        HttpStatus expectedNoContent = emptyResponse.getStatusCode();
        Object expectedEmptyBody = emptyResponse.getBody();

        assertEquals(expectedNoContent,HttpStatus.NO_CONTENT);
        assertTrue(expectedOkStatus instanceof HttpStatus);
        assertTrue(expectedEmptyBody instanceof ArrayList);
        assertEquals(expectedEmptyBody,new ArrayList<Member>());

        verify(memberServiceImplementation,times(2)).getAllRegisteredMembers();
    }
    @Test
    public void test_writeMembersToFile(){
        // First test with empty database, then non empty database
        HttpStatus expectedFirst = HttpStatus.OK;
        
        HttpStatus expectedSecond = HttpStatus.NO_CONTENT;
        String fileName = "xyz";
        when(memberServiceImplementation.writeMembersToFile(fileName))
                .thenReturn(new ResponseEntity<>("File created. Name: " + fileName + ".txt",HttpStatus.OK))
                .thenReturn(new ResponseEntity<>("Database empty.",HttpStatus.NO_CONTENT));

        ResponseEntity nonEmptyResponse = memberController.writeMembersToFile(fileName);
        Object responseTextFirst = nonEmptyResponse.getBody();
        HttpStatus responseStatusFirst = nonEmptyResponse.getStatusCode();

        assertFalse(nonEmptyResponse == null);
        assertFalse(responseStatusFirst == null);
        assertFalse(responseTextFirst == null);
        assertEquals();
        ResponseEntity emptyResponse = memberController.writeMembersToFile(fileName);
        Object responseTextSecond = emptyResponse.getBody();
        HttpStatus responseStatusSecond = emptyResponse.getStatusCode();


        verify(memberServiceImplementation,timeout(2))
                .writeMembersToFile(fileName);
    }
    @Test
    public void test_getMemberById(){
    }
    @Test
    public void test_getStatistics(){
    }
    @Test
    public void test_createMember(){
    }
    @Test
    public void test_getSpecifiedMembers(){
    }
    @Test
    public void test_loadMembersFromFile(){
    }
    @Test
    public void test_updateMember(){
    }
    @Test
    public void test_deleteMember(){
    }
    @After
    public void setToNull(){
    }
}
