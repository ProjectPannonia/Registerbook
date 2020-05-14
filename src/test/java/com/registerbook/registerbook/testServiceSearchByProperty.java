package com.registerbook.registerbook;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.MemberServiceImplementation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class testServiceSearchByProperty {

    @Mock
    MemberJpaRepository memberJpaRepository;

    @InjectMocks
    MemberServiceImplementation memberServiceImplementation;

    List<Member> testReturnedMemberList;
    Member member1;
    Member member2;
    Member member3;
    Member member4;
    String[] testContentValue;

    @Before
    public void init(){
        member1.setId(new Long(1));
        member1.setName("Johan Hegg");
        member1.setAddress("Jönköping");
        member1.setBand("Amon Amarth");
        member1.setCountry("Sweden");
        member1.setEmail("jh@jhs.com");
        member1.setInstrument("Voice");
        member1.setYearOfBirth(1967);

        member2.setId(new Long(2));
        member2.setName("Jocke Wallgren");
        member2.setAddress("Göteborg");
        member2.setBand("Sabaton");
        member2.setCountry("Norway");
        member2.setEmail("jw@jws.com");
        member2.setInstrument("Drum");
        member2.setYearOfBirth(1982);

        member3.setId(new Long(3));
        member3.setName("Olavi Mikkonen");
        member3.setAddress("Malmö");
        member3.setBand("Korpiklani");
        member3.setCountry("Finnland");
        member3.setEmail("om@oms.com");
        member3.setInstrument("Violin");
        member3.setYearOfBirth(1978);

        member4.setId(new Long(4));
        member4.setName("Johan Söderberg");
        member4.setAddress("Kalmar");
        member4.setBand("Sabaton");
        member4.setCountry("Sweden");
        member4.setEmail("jss@js.com");
        member4.setInstrument("Guitar");
        member4.setYearOfBirth(1970);

        testReturnedMemberList = new ArrayList<>();
        testReturnedMemberList.add(member1);
        testReturnedMemberList.add(member2);
        testReturnedMemberList.add(member3);
        testReturnedMemberList.add(member4);

        testContentValue = new String[2];
        testContentValue[0] = "Name";
        testContentValue[1] = "Guitar";
    }

    @Test
    public void test(){
        when(memberJpaRepository.getMemberByName())
        memberServiceImplementation.searchBySpecifiedProperty();
    }

    @After
    public void destroy(){
        member1 = null;
        member2 = null;
        member3 = null;
        member4 = null;
        testReturnedMemberList = null;
    }
}
