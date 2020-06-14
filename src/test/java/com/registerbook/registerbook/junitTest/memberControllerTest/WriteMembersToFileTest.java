package com.registerbook.registerbook.junitTest.memberControllerTest;

import com.registerbook.registerbook.controller.MemberController;
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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class WriteMembersToFileTest {
    @Mock
    MemberServiceImplementation memberServiceImplementation;
    @InjectMocks
    MemberController memberController;

    @Before
    public void init(){

    }
    @Test
    public void test_writeMembersToFile_When_FileCreated(){
        String testFileName = "xyz";
        String expectedResponseText = "File created. Name: " + testFileName + ".txt";
        HttpStatus expectedStatus = HttpStatus.OK;

        when(memberServiceImplementation.writeMembersToFile(testFileName)).thenReturn(new ResponseEntity<>(expectedResponseText,expectedStatus));

        ResponseEntity nonEmptyResponse = memberController.writeMembersToFile(testFileName);
        Object responseText = nonEmptyResponse.getBody();
        HttpStatus responseStatus = nonEmptyResponse.getStatusCode();

        assertTrue(nonEmptyResponse != null);
        assertTrue(responseStatus != null);
        assertTrue(responseText != null);
        assertTrue(responseText instanceof String);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(nonEmptyResponse instanceof ResponseEntity);
        assertEquals(responseText,expectedResponseText);
        assertEquals(responseStatus,expectedStatus);

        verify(memberServiceImplementation,times(1)).writeMembersToFile(testFileName);
    }
    @After
    public void setToNull(){
        memberController = null;
        memberServiceImplementation = null;

    }
}
