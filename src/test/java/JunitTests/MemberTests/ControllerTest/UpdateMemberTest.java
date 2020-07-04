package JunitTests.MemberTests.ControllerTest;

import com.registerbook.registerbook.controller.MemberController;
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
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UpdateMemberTest {
    @Mock
    MemberServiceImplementation memberServiceImplementation;
    @InjectMocks
    MemberController memberController;

    Member toUpdateMember;
    Member updateThisMember;

    @Before
    public void init(){
        toUpdateMember = new Member();
        toUpdateMember.setName("Create test Bela");
        toUpdateMember.setAddress("BudaTest");
        toUpdateMember.setBand("TestBand");
        toUpdateMember.setEmail("testb@test.com");
        toUpdateMember.setYearOfBirth(1985);
        toUpdateMember.setCountry("TestCountry");
        toUpdateMember.setInstrument("TestGuitar");

        updateThisMember = new Member();
        updateThisMember.setName("Update test Janos");
        updateThisMember.setAddress("BudaTest");
        updateThisMember.setBand("TestBand");
        updateThisMember.setEmail("testb@test.com");
        updateThisMember.setYearOfBirth(1985);
        updateThisMember.setCountry("TestCountry");
        updateThisMember.setInstrument("TestGuitar");
    }
    @Test
    public void test_updateMember_WithAnExistingMember(){
        when(memberServiceImplementation.updateMemberIfExist(new Long(1), toUpdateMember)).thenReturn(new ResponseEntity(updateThisMember, HttpStatus.OK));

        ResponseEntity response = memberController.updateMember(new Long(1),toUpdateMember);
        HttpStatus responseStatus = response.getStatusCode();
        Object responseMember = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseMember != null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseMember instanceof Member);
        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(responseMember,updateThisMember);

        verify(memberServiceImplementation,times(1)).updateMemberIfExist(new Long(1),toUpdateMember);
    }
    @Test
    public void test_updateMember_WithANonExistingId(){
        when(memberServiceImplementation.updateMemberIfExist(new Long(1),toUpdateMember)).thenReturn(new ResponseEntity(null,HttpStatus.NOT_FOUND));

        ResponseEntity response = memberController.updateMember(new Long(1),toUpdateMember);
        HttpStatus responseStatus = response.getStatusCode();
        Object responseMember = response.getBody();

        assertTrue(response != null);
        assertTrue(responseStatus != null);
        assertTrue(responseMember == null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);

        verify(memberServiceImplementation,times(1)).updateMemberIfExist(new Long(1),toUpdateMember);
    }
    @After
    public void setToNull(){
        memberServiceImplementation = null;
        memberController = null;
        toUpdateMember = null;
        updateThisMember = null;
    }
}
