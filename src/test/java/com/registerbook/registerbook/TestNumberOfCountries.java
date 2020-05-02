package com.registerbook.registerbook;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.service.statistics.MembersOfSpecifiedCountry;
import com.registerbook.registerbook.service.statistics.Statistics;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestNumberOfCountries {
    List<Member> allMembers = null;
    Statistics statistics = null;

    @Before
    public void init(){
        allMembers = new ArrayList<>();
        statistics = new Statistics();
        Member member1 = new Member();
        member1.setId(new Long(1));
        member1.setName("Letenyei Ádám");
        member1.setFavouriteMeal("Krumplis tészta");
        member1.setFavouriteAnimal("Kutya");
        member1.setAddress("BSZH");
        member1.setEmail("ld@ld.com");
        member1.setBand("Rammstein");
        member1.setCountry("Hungary");
        member1.setInstrument("Guitar");
        Member member2 = new Member();
        member2.setId(new Long(2));
        member2.setName("Cser Dorottya");
        member2.setFavouriteMeal("Leves");
        member2.setFavouriteAnimal("Macska");
        member2.setAddress("Farád");
        member2.setEmail("csd@csd.com");
        member2.setBand("Rammstein");
        member2.setCountry("Hungary");
        member2.setInstrument("Drum");
        Member member3 = new Member();
        member3.setId(new Long(3));
        member3.setName("Béla");
        member3.setFavouriteMeal("Bableves");
        member3.setFavouriteAnimal("krokodil");
        member3.setAddress("BP");
        member3.setEmail("b@b.com");
        member3.setBand("Mettalica");
        member3.setCountry("Germany");
        member3.setInstrument("Rhymth guitar");
        Member member4 = new Member();
        member4.setId(new Long(4));
        member4.setName("Józsi");
        member4.setFavouriteMeal("Krumpli");
        member4.setFavouriteAnimal("Ló");
        member4.setAddress("Nyíregyháza");
        member4.setEmail("j@j.com");
        member4.setBand("Amon Amarth");
        member4.setCountry("Sweden");
        member4.setInstrument("Drum");
        Member member5 = new Member();
        member5.setId(new Long(5));
        member5.setName("Kata");
        member5.setFavouriteMeal("Káposzta");
        member5.setFavouriteAnimal("Zebra");
        member5.setAddress("Nyíregy");
        member5.setEmail("k@j.com");
        member5.setBand("Amon Amarth");
        member5.setCountry("Hungary");
        member5.setInstrument("Drum");
        Member member6 = new Member();
        member6.setId(new Long(6));
        member6.setName("Feri");
        member6.setFavouriteMeal("Káposzta");
        member6.setFavouriteAnimal("Zebra");
        member6.setAddress("Nyíregy");
        member6.setEmail("k@j.com");
        member6.setBand("Amon Amarth");
        member6.setCountry("UK");
        member6.setInstrument("Drum");
        Member member7 = new Member();
        member7.setId(new Long(7));
        member7.setName("Kata");
        member7.setFavouriteMeal("Káposzta");
        member7.setFavouriteAnimal("Zebra");
        member7.setAddress("Nyíregy");
        member7.setEmail("k@j.com");
        member7.setBand("Amon Amarth");
        member7.setCountry("Norway");
        member7.setInstrument("Drum");
        Member member8 = new Member();
        member8.setId(new Long(8));
        member8.setName("Kata");
        member8.setFavouriteMeal("Káposzta");
        member8.setFavouriteAnimal("Zebra");
        member8.setAddress("Nyíregy");
        member8.setEmail("k@j.com");
        member8.setBand("Amon Amarth");
        member8.setCountry("USA");
        member8.setInstrument("Drum");
        Member member9 = new Member();
        member9.setId(new Long(9));
        member9.setName("Kata");
        member9.setFavouriteMeal("Káposzta");
        member9.setFavouriteAnimal("Zebra");
        member9.setAddress("Nyíregy");
        member9.setEmail("k@j.com");
        member9.setBand("Amon Amarth");
        member9.setCountry("Finnland");
        member9.setInstrument("Drum");
        Member member10 = new Member();
        member10.setId(new Long(10));
        member10.setName("Kata");
        member10.setFavouriteMeal("Káposzta");
        member10.setFavouriteAnimal("Zebra");
        member10.setAddress("Nyíregy");
        member10.setEmail("k@j.com");
        member10.setBand("Amon Amarth");
        member10.setCountry("Canada");
        member10.setInstrument("Drum");
        allMembers.add(member1);
        allMembers.add(member2);
        allMembers.add(member3);
        allMembers.add(member4);
        allMembers.add(member5);
        allMembers.add(member6);
        allMembers.add(member7);
        allMembers.add(member8);
        allMembers.add(member9);
        allMembers.add(member10);
        //['Germany','Sweden','Hungary','UK','USA','Norway','Finnland','Canada'];
    }
    @Test
    public void test(){
        List<MembersOfSpecifiedCountry> result = statistics.numberOfMembersByCountry(allMembers);

        int expectedCanadian = 1;// 0
        int resultCanadian = result.get(0).getNumberFromThisCountry();
        assertEquals(expectedCanadian,resultCanadian);

        int expectedFinnish = 1; // 1
        int resultFinnish = result.get(1).getNumberFromThisCountry();
        assertEquals(expectedFinnish,resultFinnish);

        int expectedGerman = 1; // 2
        int resultGerman = result.get(2).getNumberFromThisCountry();
        assertEquals(resultGerman,resultGerman);

        int expectedHungairan = 3; // 3
        int resultHungarian = result.get(3).getNumberFromThisCountry();
        assertEquals(expectedHungairan,resultHungarian);

        int expectedNorwegian = 1; // 4
        int resultNorwegian = result.get(4).getNumberFromThisCountry();
        assertEquals(expectedNorwegian,resultNorwegian);

        int expectedSweden = 1; // 5
        int resultSweden = result.get(5).getNumberFromThisCountry();
        assertEquals(expectedSweden,resultSweden);

        int expectedUk = 1; // 6
        int resultUk = result.get(6).getNumberFromThisCountry();
        assertEquals(expectedUk,resultUk);

        int expectedAmerican = 1; // 7
        int resultAmerican = result.get(7).getNumberFromThisCountry();
        assertEquals(expectedAmerican,resultAmerican);

        for (int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).getCountry());
        }

    }
}
