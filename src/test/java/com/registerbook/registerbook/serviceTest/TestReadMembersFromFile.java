package com.registerbook.registerbook.serviceTest;

import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.service.register.fileOperation.fileReader.MembersFileReader;
import org.junit.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestReadMembersFromFile {
    String testMemberLine = "Johan Söderberg,Amon Amarth,Guitar,Sweden,Göteborg,js@amonamarth.sw,1976";

    @Test
    public void testMemberSeparate1(){
        List<Member> loaded = MembersFileReader.readMembersFromFile("D://LoadMembersToServer.txt");
        for (Member m : loaded){
            System.out.println(m.toString());
        }
        assertNotEquals(null,loaded);
    }
}
