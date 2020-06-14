package serviceTest.junitTest;

import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
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
public class MemberServiceTest {

    @Mock
    MemberJpaRepository memberJpaRepository;
    @InjectMocks
    MemberServiceImplementation memberServiceImplementation;

    List<Member> testFindAllResponse;
    Member testM1;
    Member testM2;
    Member testM3;
    Member testM4;

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

        testFindAllResponse = new ArrayList<>();
        testFindAllResponse.add(testM1);
        testFindAllResponse.add(testM2);
        testFindAllResponse.add(testM3);
        testFindAllResponse.add(testM4);
    }
    @Test
    public void test_getAllRegisteredMembers(){
        when(memberJpaRepository.findAll()).thenReturn(testFindAllResponse);
        HttpStatus expectedResponseStatus = HttpStatus.OK;
        HttpStatus unExpectedResponseStatus = HttpStatus.NO_CONTENT;

        ResponseEntity response = memberServiceImplementation.getAllRegisteredMembers();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseList = response.getBody();

        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseList instanceof List);
        assertEquals(responseList,testFindAllResponse);
        assertEquals(responseStatus,HttpStatus.OK);

        verify(memberJpaRepository,times(1)).findAll();
    }
    @After
    public void setToNull(){

    }
}
