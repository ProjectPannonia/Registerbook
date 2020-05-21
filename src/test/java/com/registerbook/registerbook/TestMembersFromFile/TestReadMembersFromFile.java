package com.registerbook.registerbook.TestMembersFromFile;

import com.registerbook.registerbook.repository.model.Member;
import com.registerbook.registerbook.repository.model.fileReader.MembersFileReader;
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
