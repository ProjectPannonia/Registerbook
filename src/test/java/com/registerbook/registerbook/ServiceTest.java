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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
    Member testMember;
    Member testMember2;
    ArrayList<Member> testAllMember;

    @Mock
    MemberJpaRepository memberJpaRepository;

    @InjectMocks
    MemberService memberService;

    @Before
    public void init(){
        testAllMember = new ArrayList<Member>();
        testMember = new Member();
        testMember.setName("Letenyei Ádám");
        testMember.setId(new Long(1));
        testMember.setInstrument("Guitar");
        testMember.setCountry("Hungary");
        testMember.setBand("Amon Amarth");
        testMember.setEmail("ld@gm.hu");
        testMember.setYearOfBirth(1989);
        testMember.setAddress("BSZH");
        testMember2 = new Member();
        testMember2.setName("Józsi");
        testMember2.setId(new Long(2));
        testMember2.setInstrument("Guitar");
        testMember2.setCountry("Sweden");
        testMember2.setBand("Sabaton");
        testMember2.setEmail("sa@gm.hu");
        testMember2.setYearOfBirth(1979);
        testMember2.setAddress("Malmö");
        testAllMember.add(testMember);
        testAllMember.add(testMember2);
    }

    @Test
    public void findAllTest(){
        when(memberJpaRepository.findAll()).thenReturn(testAllMember);
        List<Member> resultAllMember = memberService.getAllMember();

        assertEquals(testAllMember,resultAllMember);
        Member firstTestName = testAllMember.get(0);
        Member firstResultTestNAme = resultAllMember.get(0);
        assertThat(firstTestName.getName()).isSameAs(firstResultTestNAme.getName());
    }
}
