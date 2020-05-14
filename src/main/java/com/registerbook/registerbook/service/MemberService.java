package com.registerbook.registerbook.service;

import com.registerbook.registerbook.model.CountryEntity;
import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.service.statistics.specialObjectsForStatistics.StatisticData;
import java.util.List;

public interface MemberService {
    List<Member> getAllMember();
    void saveNewMember(Member member);
    Member findMemberById(Long id);
    void deleteMemberById(Long id);
    Member checkMemberWithThisNameAlreadyInDatabase(String name);
    List<Member> searchBySpecifiedProperty(String[] propertyAndValue);
    StatisticData getStatistics();
    List<CountryEntity> loadCountriesToTheServer();
}
