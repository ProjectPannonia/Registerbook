package com.registerbook.registerbook;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
    Member testMember;

    @Mock
    MemberJpaRepository mjr;

    @InjectMocks
    MemberService ms;

    @Before
    public void init(){
        testMember = new Member();
        testMember.setName("Letenyei Ádám");
        testMember.setId(new Long(1));
        testMember.setInstrument("Guitar");
        testMember.setCountry("Hungary");
        testMember.setBand("Amon Amarth");
        testMember.setEmail("ld@gm.hu");
        testMember.setYearOfBirth(1989);
        testMember.setAddress("BSZH");
    }

    @Test
    public void firstTest(){
        when(mjr.save(any(Member.class))).thenReturn(new Member());
        Member created = ms.saveNewMember(testMember);
        assertEquals(created.getName(),testMember.getName());
    }
}
