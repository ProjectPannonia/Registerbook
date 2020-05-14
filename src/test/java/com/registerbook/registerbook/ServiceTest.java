package com.registerbook.registerbook;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.MemberServiceImplementation;
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
    Member testMember1;
    Member testMember2;

    ArrayList<Member> testMemberList;

    @Mock
    MemberJpaRepository memberJpaRepository;

    @InjectMocks
    MemberServiceImplementation memberServiceImplementation;

    @Before
    public void init(){
        testMemberList = new ArrayList<Member>();
        testMember1 = new Member();
        testMember1.setName("Letenyei Ádám");
        testMember1.setId(new Long(1));
        testMember1.setInstrument("Guitar");
        testMember1.setCountry("Hungary");
        testMember1.setBand("Amon Amarth");
        testMember1.setEmail("ld@gm.hu");
        testMember1.setYearOfBirth(1989);
        testMember1.setAddress("BSZH");
        testMember2 = new Member();
        testMember2.setName("Józsi");
        testMember2.setId(new Long(2));
        testMember2.setInstrument("Guitar");
        testMember2.setCountry("Sweden");
        testMember2.setBand("Sabaton");
        testMember2.setEmail("sa@gm.hu");
        testMember2.setYearOfBirth(1979);
        testMember2.setAddress("Malmö");
        testMemberList.add(testMember1);
        testMemberList.add(testMember2);
    }

    @Test
    public void findAllTest(){
        when(memberJpaRepository.findAll()).thenReturn(testMemberList);
        List<Member> resultAllMember = memberServiceImplementation.getAllMember();

        assertEquals(testMemberList,resultAllMember);
        Member firstTestName = testMemberList.get(0);
        Member firstResultTestNAme = resultAllMember.get(0);
        assertThat(firstTestName.getName()).isSameAs(firstResultTestNAme.getName());
    }
}
