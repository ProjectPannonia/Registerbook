package com.registerbook.registerbook;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.service.statistics.Statistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStatistics {
    Statistics statistics = null;
    List<Member> testArray = null;

    @Before
    public void init(){
       /* statistics = new Statistics();
        testArray = new ArrayList<>();
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
        testArray.add(member1);
        testArray.add(member2);
        testArray.add(member3);
        testArray.add(member4);
    }

    @Test
    public void test(){
        int[] test = statistics.getStatitstics(testArray);
        int resultNumberOfRegisteredMembers = test[0];
        int resultNumberOfRegisteredBands = test[1];
        int expectedNumberOfRegisteredMembers = 4;
        int expectedNumberOfRegisteredBands = 3;
        System.out.println("Regisztrált zenészek - Expected: " + resultNumberOfRegisteredMembers + ", Result: " + test[0]);
        System.out.println("Registrált bandák - Expected: " + resultNumberOfRegisteredBands + ", Result: " + test[1]);
        assertEquals(resultNumberOfRegisteredMembers,expectedNumberOfRegisteredMembers);
        assertEquals(resultNumberOfRegisteredBands,expectedNumberOfRegisteredBands);
    }

    @After
    public void setToNull(){
        testArray = null;
        statistics = null;
    }
    */

}
}
