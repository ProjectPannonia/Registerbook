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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class WriteMembersToFile {
    @Mock
    MemberJpaRepository memberJpaRepository;
    @InjectMocks
    MemberServiceImplementation memberServiceImplementation;

    List<Member> responseEmptyDb;
    List<Member> responseNonEmptyDb;
    Member testM1;
    Member testM2;

    @Before
    public void init(){
        responseEmptyDb = new ArrayList<>();

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

        responseNonEmptyDb = new ArrayList<>();
        responseNonEmptyDb.add(testM1);
        responseNonEmptyDb.add(testM2);
    }
    @Test
    public void test_writeMembersToFile_When_DbIsEmpty(){
        when(memberJpaRepository.findAll()).thenReturn();
    }

    @After
    public void setToNull(){
        memberJpaRepository = null;
        memberServiceImplementation = null;
        responseEmptyDb = null;
        responseNonEmptyDb = null;
    }
}
