package com.registerbook.registerbook.service.member;

import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.service.member.statistics.StatisticData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    ResponseEntity<Member> findMemberById(Long id);

    List<Member> getAllMember();

    void saveNewMember(Member member);

    void deleteMemberById(Long id);

    Member checkMemberWithThisNameAlreadyInDatabase(String name);

    List<Member> searchBySpecifiedProperty(String[] propertyAndValue);

    StatisticData getStatistics();

    void loadMembersFromFileToServer(String path);

    ResponseEntity<String> writeMembersToFile(String fileName);

    ResponseEntity getAllRegisteredMembers();

    ResponseEntity<Member> saveNewMemberIfNotExist(Member member);



    ResponseEntity<Member> updateMemberIfExist(Long id, Member member);
}
