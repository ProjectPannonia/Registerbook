package JunitTests.MemberTests.ServiceTest;

import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.member.MemberServiceImplementation;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class GetAllRegisteredMembersTest {
    @Mock
    MemberJpaRepository memberJpaRepository;
    @InjectMocks
    MemberServiceImplementation memberServiceImplementation;

    List<Member> responseEmptyDb;
    List<Member> responseNonEmptyDbResponse;
    Member testM1;
    Member testM2;

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
        testM2.setName("Create test Miklos");
        testM2.setAddress("BakonyTest");
        testM2.setBand("TestBand");
        testM2.setEmail("testb@test.com");
        testM2.setYearOfBirth(1985);
        testM2.setCountry("TestCountry");
        testM2.setInstrument("TestGuitar");

        responseNonEmptyDbResponse = new ArrayList<>();
        responseNonEmptyDbResponse.add(testM1);
        responseNonEmptyDbResponse.add(testM2);

        responseEmptyDb = new ArrayList<>();
    }
    @Test
    public void test_getAllRegisteredMembers_With_NonEmptyDatabase(){
        when(memberJpaRepository.findAll()).thenReturn(responseNonEmptyDbResponse);

        ResponseEntity response = memberServiceImplementation.getAllRegisteredMembers();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseMembersList = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseMembersList != null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseMembersList instanceof List);
        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(responseMembersList,responseNonEmptyDbResponse);
        assertTrue(((List) responseMembersList).size() > 0);
        assertTrue(((List) responseMembersList).size() == 2);

        verify(memberJpaRepository,times(1)).findAll();
    }
    @Test
    public void test_getAllRegisteredMembers_With_EmptyDatabase(){
        when(memberJpaRepository.findAll()).thenReturn(responseEmptyDb);

        ResponseEntity response = memberServiceImplementation.getAllRegisteredMembers();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseMembersList = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseMembersList != null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseMembersList instanceof List);
        assertEquals(responseStatus,HttpStatus.NO_CONTENT);
        assertEquals(responseMembersList,responseEmptyDb);
        assertTrue(((List) responseMembersList).size() == 0);

        verify(memberJpaRepository,times(1)).findAll();
    }
    @After
    public void setToNull(){
        memberJpaRepository = null;
        memberServiceImplementation = null;
        responseEmptyDb = null;
        responseNonEmptyDbResponse = null;
        testM1 = null;
        testM2 = null;
    }
}
