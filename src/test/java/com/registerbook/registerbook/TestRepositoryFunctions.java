package com.registerbook.registerbook;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class TestRepositoryFunctions {

    MemberJpaRepository mjr;
    List<Member> result = new ArrayList<>();
    @Before
    public void init(){
        mjr = Mockito.mock(MemberJpaRepository.class);

        Member member1 = new Member();
        member1.setId(new Long(1));
        member1.setName("Letenyei Ádám");
        member1.setAddress("BSZH");
        member1.setEmail("ld@ld.com");
        member1.setBand("Rammstein");
        member1.setCountry("Hungary");
        member1.setInstrument("Guitar");

        Member member2 = new Member();
        member2.setId(new Long(2));
        member2.setName("Cser Dorottya");
        member2.setAddress("Farád");
        member2.setEmail("csd@csd.com");
        member2.setBand("Rammstein");
        member2.setCountry("Hungary");
        member2.setInstrument("Drum");

        Member member3 = new Member();
        member3.setId(new Long(3));
        member3.setName("Béla");
        member3.setAddress("BP");
        member3.setEmail("b@b.com");
        member3.setBand("Mettalica");
        member3.setCountry("Germany");
        member3.setInstrument("Rhymth guitar");

        Member member4 = new Member();
        member4.setId(new Long(4));
        member4.setName("Józsi");
        member4.setAddress("Nyíregyháza");
        member4.setEmail("j@j.com");
        member4.setBand("Amon Amarth");
        member4.setCountry("Sweden");
        member4.setInstrument("Drum");

        result.add(member1);
        result.add(member2);
        result.add(member3);
        result.add(member4);
    }

    @Test
    public void test(){
        when(mjr.findAll()).thenReturn(result);


        int expected = 4;
        int result = mjr.findAll().size();
        assertEquals(expected,result);
    }
    @Test
    public void testFindById(){
        when(mjr.findMemberById(new Long(1))).thenReturn(result.get(0));
        String expectedName = "Letenyei Ádám";
        String resultName = mjr.findMemberById(new Long(1)).getName();
        assertEquals(expectedName,resultName);
    }
}
