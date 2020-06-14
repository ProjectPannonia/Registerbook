package com.registerbook.registerbook.junitTest.memberControllerTest;

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

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreateMemberTest {
    @Mock
    MemberServiceImplementation memberServiceImplementation;
    @InjectMocks
    MemberController memberController;

    Member testM1;

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

    }
    @Test
    public void test_createMember_WhenMemberNotExist(){
        HttpStatus expectedStatus = HttpStatus.CREATED;

        when(memberServiceImplementation.saveNewMemberIfNotExist(testM1)).thenReturn(new ResponseEntity<>(testM1,expectedStatus));

        ResponseEntity response = memberController.createMember(testM1);
        HttpStatus responseStatus = response.getStatusCode();
        Object responseMember = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseMember != null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseMember instanceof Member);
        assertEquals(responseStatus,expectedStatus);
        assertEquals(responseMember,testM1);

        assertTrue(responseMember instanceof Member);
        assertEquals(responseStatus,HttpStatus.CREATED);

        verify(memberServiceImplementation,times(1)).saveNewMemberIfNotExist(testM1);
    }
    @Test
    public void test_createMember_WhenAlreadyExist(){
        HttpStatus expectedStatus = HttpStatus.CONFLICT;

        when(memberServiceImplementation.saveNewMemberIfNotExist(testM1)).thenReturn(new ResponseEntity(testM1,expectedStatus));

        ResponseEntity response = memberController.createMember(testM1);
        HttpStatus responseStatus = response.getStatusCode();
        Object responseMember = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseMember != null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseMember instanceof Member);
        assertEquals(responseStatus,expectedStatus);
        assertEquals(responseMember,testM1);

        verify(memberServiceImplementation,times(1)).saveNewMemberIfNotExist(testM1);
    }
    @After
    public void setToNull(){
        memberServiceImplementation = null;
        memberController = null;
        testM1 = null;
    }
}
