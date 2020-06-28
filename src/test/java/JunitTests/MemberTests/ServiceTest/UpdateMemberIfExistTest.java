package JunitTests.MemberTests.ServiceTest;

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
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UpdateMemberIfExistTest {
    @InjectMocks
    MemberServiceImplementation service;
    @Mock
    MemberJpaRepository repo;

    Member memberInDb;
    Member valuesForUpdate;

    @Before
    public void init(){
        memberInDb = new Member();
        memberInDb.setName("Create test Bela");
        memberInDb.setAddress("BudaTest");
        memberInDb.setBand("TestBand");
        memberInDb.setEmail("testb@test.com");
        memberInDb.setYearOfBirth(1985);
        memberInDb.setCountry("TestCountry");
        memberInDb.setInstrument("TestGuitar");

        valuesForUpdate = new Member();
        valuesForUpdate.setName("Create test Miklos");
        valuesForUpdate.setAddress("BakonyTest");
        valuesForUpdate.setBand("TestBand");
        valuesForUpdate.setEmail("testb@test.com");
        valuesForUpdate.setYearOfBirth(1985);
        valuesForUpdate.setCountry("TestCountry");
        valuesForUpdate.setInstrument("TestGuitar");

    }

    @Test
    public void test_UpdateExistingMember_updateMemberIfExist(){
        when(repo.findMemberById(anyLong())).thenReturn(memberInDb);

        ResponseEntity response = service.updateMemberIfExist(new Long(1), valuesForUpdate);
        HttpStatus responseStatus = response.getStatusCode();
        Object responseBody = response.getBody();

        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatus instanceof HttpStatus);
        assertTrue(responseBody instanceof Member);

        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(((Member) responseBody).getName(),valuesForUpdate.getName());
        assertEquals(((Member) responseBody).getAddress(),valuesForUpdate.getAddress());
        assertEquals(((Member) responseBody).getBand(),valuesForUpdate.getBand());
        assertEquals(((Member) responseBody).getCountry(),valuesForUpdate.getCountry());
        assertEquals(((Member) responseBody).getEmail(),valuesForUpdate.getEmail());
        assertEquals(((Member) responseBody).getInstrument(),valuesForUpdate.getInstrument());
        assertEquals(((Member) responseBody).getYearOfBirth(),valuesForUpdate.getYearOfBirth());

        verify(repo,times(1)).findMemberById(new Long(1));
    }

    @After
    public void setToNull(){
        service = null;
        repo = null;
    }
}
