package com.registerbook.registerbook.service.register;

import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.service.register.statistics.StatisticData;
import java.util.List;

public interface MemberService {
    List<Member> getAllMember();
    void saveNewMember(Member member);
    Member findMemberById(Long id);
    void deleteMemberById(Long id);
    Member checkMemberWithThisNameAlreadyInDatabase(String name);
    List<Member> searchBySpecifiedProperty(String[] propertyAndValue);
    StatisticData getStatistics();
    void loadMembersFromFileToServer(String path);
}
