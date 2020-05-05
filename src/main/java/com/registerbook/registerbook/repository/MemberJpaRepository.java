package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.service.statistics.AdvancedStatistics;
import com.registerbook.registerbook.service.statistics.MembersOfSpecifiedCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member,Long> {
    List<Member> findAll();

    @Query(value = "select s from Member s where s.country = :country")
    List<Member> countPerCountry(@Param("country")String country);

}
