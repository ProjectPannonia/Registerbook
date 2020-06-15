package JunitTests.MemberTests.ControllerTest;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SendRegisteredMembersToFrontEndTest{

    @InjectMocks
    MemberController memberController;
    @Mock
    MemberServiceImplementation memberServiceImplementation;

    List<Member> responseFromDbWhenNotEmpty;
    List<Member> responseFromDbWhenEmpty;
    List<Member> responseFromDbWhenOnlyOneMemberRegistered;
    Member testM1;
    Member testM2;
    Member testM3;
    Member testM4;
    HttpStatus expectedStatusWhenDatabaseIsNotEmpty;
    HttpStatus expectedStatusWhenDatabaseIsEmpty;

    @Before
    public void init() {
        expectedStatusWhenDatabaseIsNotEmpty = HttpStatus.OK;
        expectedStatusWhenDatabaseIsEmpty = HttpStatus.NO_CONTENT;

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

        responseFromDbWhenNotEmpty = new ArrayList<>();
        responseFromDbWhenNotEmpty.add(testM1);
        responseFromDbWhenNotEmpty.add(testM2);
        responseFromDbWhenNotEmpty.add(testM3);
        responseFromDbWhenNotEmpty.add(testM4);

        responseFromDbWhenEmpty = new ArrayList<Member>();

        responseFromDbWhenOnlyOneMemberRegistered = new ArrayList<Member>();
        responseFromDbWhenOnlyOneMemberRegistered.add(testM1);
    }
    @Test
    public void test_sendRegisteredMembersToFrontEnd_When_DatabaseIsNotEmpty() {
        when(memberServiceImplementation.getAllRegisteredMembers()).thenReturn(new ResponseEntity(responseFromDbWhenNotEmpty, expectedStatusWhenDatabaseIsNotEmpty));

        ResponseEntity response = memberController.sendRegisteredMembersToFrontEnd();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseAllMembers = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseAllMembers != null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseAllMembers instanceof List);
        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(responseAllMembers,responseFromDbWhenNotEmpty);

        verify(memberServiceImplementation,times(1)).getAllRegisteredMembers();
    }
    @Test
    public void test_sendRegisteredMembersToFrontEnd_When_DatabaseIsEmpty(){
        when(memberServiceImplementation.getAllRegisteredMembers()).thenReturn(new ResponseEntity(responseFromDbWhenEmpty,expectedStatusWhenDatabaseIsEmpty));

        ResponseEntity response = memberController.sendRegisteredMembersToFrontEnd();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseEmptyAllMembers = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseEmptyAllMembers != null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseEmptyAllMembers instanceof List);
        assertEquals(responseStatus,HttpStatus.NO_CONTENT);
        assertEquals(responseEmptyAllMembers,responseFromDbWhenEmpty);

        verify(memberServiceImplementation,times(1)).getAllRegisteredMembers();
    }
    @Test
    public void test_sendRegisteredMembersToFrontEnd_When_OnlyOneRegisteredMemberExist(){
        when(memberServiceImplementation.getAllRegisteredMembers()).thenReturn(new ResponseEntity(responseFromDbWhenOnlyOneMemberRegistered,expectedStatusWhenDatabaseIsNotEmpty));

        ResponseEntity response = memberController.sendRegisteredMembersToFrontEnd();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseAllMembers = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseAllMembers != null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseAllMembers instanceof List);
        assertTrue(((List) responseAllMembers).size() == 1);
        assertTrue(((List) responseAllMembers).get(0) != null);
        assertEquals(((List) responseAllMembers).get(0),testM1);
        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(responseAllMembers,responseFromDbWhenOnlyOneMemberRegistered);

        verify(memberServiceImplementation,times(1)).getAllRegisteredMembers();
    }
    @After
    public void setToNull() {
        memberController = null;
        memberServiceImplementation = null;
        responseFromDbWhenNotEmpty = null;
        responseFromDbWhenEmpty = null;
        responseFromDbWhenOnlyOneMemberRegistered = null;
        testM1 = null;
        testM2 = null;
        testM3 = null;
        testM4 = null;
        expectedStatusWhenDatabaseIsNotEmpty = null;
        expectedStatusWhenDatabaseIsEmpty = null;
    }
}
