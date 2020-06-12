package com.registerbook.registerbook.controllerTests.junitTests;

import com.registerbook.registerbook.controller.MemberController;
import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.service.register.MemberServiceImplementation;
import com.registerbook.registerbook.service.register.statistics.StatisticData;
import com.registerbook.registerbook.service.register.statistics.UpdatedStatistic.CountryAndQuantity;
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
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MemberControllerTest {

    List<Member> responseFromDb;
    Member testM1;
    Member testM2;
    Member testM3;
    Member testM4;
    StatisticData testStatisticData;
    List<CountryAndQuantity> testCountryAndQuantity;

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


        testCountryAndQuantity = new ArrayList<>();
        testCountryAndQuantity.add(new CountryAndQuantity("Hungary",3));
        testCountryAndQuantity.add(new CountryAndQuantity("USA",5));
        testCountryAndQuantity.add(new CountryAndQuantity("USA",5));

        testStatisticData = new StatisticData(300,20,testCountryAndQuantity,10);
    }
    //Resource not found exception => runtime exception,Error details(404)
    // getbyid => dobjon exception
    // restcontroller, exception => error detailsel
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
        String testFileName = "xyz";
        String expectedResponseTextFirst = "File created. Name: " + testFileName + ".txt";
        String expectedResponseTextSecond = "Database empty.";
        HttpStatus expectedFirstStatus = HttpStatus.OK;
        HttpStatus expectedSecondStatus = HttpStatus.NO_CONTENT;

        when(memberServiceImplementation.writeMembersToFile(testFileName))
                .thenReturn(new ResponseEntity<>(expectedResponseTextFirst,expectedFirstStatus))
                .thenReturn(new ResponseEntity<>(expectedResponseTextSecond,expectedSecondStatus));

        ResponseEntity nonEmptyResponse = memberController.writeMembersToFile(testFileName);
        Object responseTextFirst = nonEmptyResponse.getBody();
        HttpStatus responseStatusFirst = nonEmptyResponse.getStatusCode();

        assertFalse(nonEmptyResponse == null);
        assertFalse(responseStatusFirst == null);
        assertFalse(responseTextFirst == null);
        assertTrue(responseTextFirst instanceof String);
        assertEquals(responseTextFirst,expectedResponseTextFirst);
        assertEquals(responseStatusFirst,expectedFirstStatus);


        ResponseEntity emptyResponse = memberController.writeMembersToFile(testFileName);
        Object responseTextSecond = emptyResponse.getBody();
        HttpStatus responseStatusSecond = emptyResponse.getStatusCode();

        assertFalse(emptyResponse == null);
        assertFalse(responseStatusSecond == null);
        assertFalse(responseTextSecond == null);
        assertTrue(responseStatusSecond instanceof HttpStatus);
        assertTrue(responseTextSecond instanceof String);
        assertEquals(responseStatusSecond,expectedSecondStatus);
        assertEquals(responseTextSecond,expectedResponseTextSecond);

        verify(memberServiceImplementation,times(2))
                .writeMembersToFile(testFileName);
    }

    @Test
    public void test_getMemberById(){
        HttpStatus expectedStatusFirst = HttpStatus.OK;
        HttpStatus expectedStatusSecond = HttpStatus.NO_CONTENT;

        when(memberServiceImplementation.findMemberByIdIfExist(anyLong()))
                .thenReturn(new ResponseEntity<>(testM1,expectedStatusFirst))
                .thenReturn(new ResponseEntity<>(null,expectedStatusSecond));

        ResponseEntity responseFirst = memberController.getMemberById(anyLong());
        HttpStatus responseStatusFirst = responseFirst.getStatusCode();
        Object responseMemberFirst = responseFirst.getBody();

        assertFalse(responseFirst == null);
        assertFalse(responseStatusFirst == null);
        assertFalse(responseMemberFirst == null);
        assertTrue(responseFirst instanceof ResponseEntity);
        assertTrue(responseStatusFirst instanceof HttpStatus);
        assertTrue(responseMemberFirst instanceof Member);
        assertEquals(responseStatusFirst,expectedStatusFirst);
        assertEquals(responseMemberFirst,testM1);

        ResponseEntity responseSecond = memberController.getMemberById(anyLong());
        HttpStatus responseStatusSecond = responseSecond.getStatusCode();
        Object responseMemberSecond = responseSecond.getBody();

        assertFalse(responseSecond == null);
        assertFalse(responseStatusSecond == null);
        assertTrue(responseMemberSecond == null);
        assertTrue(responseSecond instanceof ResponseEntity);
        assertTrue(responseStatusSecond instanceof HttpStatus);
        assertFalse(responseMemberSecond instanceof Member);
        assertEquals(responseStatusSecond,expectedStatusSecond);
        assertEquals(responseMemberSecond,null);

        verify(memberServiceImplementation,times(2))
                .findMemberByIdIfExist(anyLong());
    }

    @Test
    public void test_getStatistics(){
        when(memberServiceImplementation.getStatistics())
                .thenReturn(testStatisticData);

        ResponseEntity response = memberController.getStatistics();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseStatisticData = response.getBody();

        assertFalse(response == null);
        assertFalse(responseStatus == null);
        assertFalse(responseStatisticData == null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatisticData instanceof StatisticData);
        assertTrue(responseStatus instanceof HttpStatus);
        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(responseStatisticData,testStatisticData);

        verify(memberServiceImplementation,times(1))
                .getStatistics();
    }

    @Test
    public void test_createMember(){
        HttpStatus expectedWhenCreated = HttpStatus.CREATED;
        HttpStatus expectedWhenAlreadyExist = HttpStatus.CONFLICT;

        when(memberServiceImplementation.saveNewMemberIfNotExist(testM1))
                .thenReturn(new ResponseEntity<>(testM1,expectedWhenCreated))
                .thenReturn(new ResponseEntity<>(testM1,expectedWhenAlreadyExist));

        ResponseEntity responseFirst = memberController.createMember(testM1);
        HttpStatus responseStatusFirst = responseFirst.getStatusCode();
        Object responseMemberFirst = responseFirst.getBody();

        assertFalse(responseFirst == null);
        assertTrue(responseFirst instanceof ResponseEntity);

        assertFalse(responseStatusFirst == null);
        assertTrue(responseStatusFirst instanceof HttpStatus);

        assertFalse(responseMemberFirst == null);
        assertEquals(responseMemberFirst,testM1);

        assertTrue(responseMemberFirst instanceof Member);
        assertEquals(responseStatusFirst,HttpStatus.CREATED);

/*
        ResponseEntity responseSecond = memberController.createMember(testM1);
        HttpStatus responseStatusSecond = responseFirst.getStatusCode();
        Object responseMemberSecond = responseFirst.getBody();

        assertFalse(responseSecond == null);
        assertTrue(responseFirst instanceof ResponseEntity);

        assertFalse(responseStatusSecond == null);
        assertTrue(responseStatusSecond instanceof HttpStatus);

        assertFalse(responseMemberSecond == null);
        assertEquals(responseMemberSecond,testM1);
        assertEquals(responseStatusSecond,HttpStatus.CONFLICT);
*/
        verify(memberServiceImplementation,times(1))
                .saveNewMemberIfNotExist(testM1);
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
