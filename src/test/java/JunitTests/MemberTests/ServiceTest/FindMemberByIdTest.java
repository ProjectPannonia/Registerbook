package JunitTests.MemberTests.ServiceTest;

import com.registerbook.registerbook.controller.errorHandler.apiError.ResourceNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FindMemberByIdTest {
    @Mock
    MemberJpaRepository repo;
    @InjectMocks
    MemberServiceImplementation service;

    Member responseMember;
    @Before
    public void init(){
        responseMember = new Member();
        responseMember.setName("Test Bela");
        responseMember.setAddress("Budapest");
        responseMember.setBand("Test band");
        responseMember.setCountry("Hungary");
        responseMember.setEmail("test@test.hu");
        responseMember.setInstrument("Guitar");
        responseMember.setYearOfBirth(1989);

    }
    @Test(expected = ResourceNotFoundException.class)
    public void test_expectingResourceNotFound_FindMemberById(){
        when(repo.findMemberById(anyLong())).thenReturn(null);

        ResponseEntity response = service.findMemberById(new Long(1));
        HttpStatus responseStatus = response.getStatusCode();
        Object responseBody = response.getBody();

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);

        verify(repo,times(1)).findMemberById(new Long(1));
    }
    @Test
    public void test_withExistingId_FindMemberById(){
        when(repo.findMemberById(new Long(1))).thenReturn(responseMember);

        ResponseEntity response = service.findMemberById(new Long(1));
        HttpStatus responseStatus = response.getStatusCode();
        Object responseBody = response.getBody();

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseBody instanceof Member);

        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(responseBody,responseMember);

        verify(repo,times(1)).findMemberById(new Long(1));
    }
    @After
    public void setToNull(){
        repo = null;
        service = null;
    }
}
