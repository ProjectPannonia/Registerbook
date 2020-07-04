package JunitTests.MemberTests.ControllerTest;

import com.registerbook.registerbook.controller.MemberController;
import com.registerbook.registerbook.controller.errorHandler.apiError.ResourceNotFoundException;
import com.registerbook.registerbook.model.Member;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class GetMemberByIdTest {
    @Mock
    MemberServiceImplementation memberServiceImplementation;
    @InjectMocks
    MemberController memberController;

    HttpStatus expectedStatusWhenIdNotExist;
    HttpStatus expectedStatusWhenIdExist;
    Member testMember;

    @Before
    public void init(){
        expectedStatusWhenIdNotExist = HttpStatus.NO_CONTENT;
        expectedStatusWhenIdExist = HttpStatus.OK;
        testMember = new Member();
        testMember.setName("Create test Bela");
        testMember.setAddress("BudaTest");
        testMember.setBand("TestBand");
        testMember.setEmail("testb@test.com");
        testMember.setYearOfBirth(1985);
        testMember.setCountry("TestCountry");
        testMember.setInstrument("TestGuitar");
    }
    @Test(expected = ResourceNotFoundException.class)
    public void test_getMemberById_With_NonExistId(){
        when(memberServiceImplementation.findMemberById(anyLong())).thenReturn(new ResponseEntity<>(null,expectedStatusWhenIdNotExist));

        ResponseEntity resultResponse= memberController.getMemberById(anyLong());
        HttpStatus resultStatus = resultResponse.getStatusCode();
        Object resultMember = resultResponse.getBody();

        assertTrue(resultResponse == null);
        assertTrue(resultMember == null);
        assertEquals(resultStatus,expectedStatusWhenIdNotExist);

        verify(memberServiceImplementation,times(1)).findMemberById(anyLong());
    }
    @Test
    public void test_getMemberById_With_ExistId(){
        when(memberServiceImplementation.findMemberById(anyLong())).thenReturn(new ResponseEntity<>(testMember,expectedStatusWhenIdExist));

        ResponseEntity resultResponse = memberController.getMemberById(new Long(1));
        HttpStatus resultStatus = resultResponse.getStatusCode();
        Object resultMember = resultResponse.getBody();

        assertTrue(resultResponse != null);
        assertTrue(resultStatus != null);
        assertTrue(resultMember != null);
        assertEquals(resultStatus,expectedStatusWhenIdExist);
        assertEquals(resultMember,testMember);

        verify(memberServiceImplementation,times(1)).findMemberById(anyLong());
    }
    @After
    public void setToNull(){
        memberServiceImplementation = null;
        memberController = null;
        expectedStatusWhenIdNotExist = null;
        expectedStatusWhenIdExist = null;
        testMember = null;
    }
}
