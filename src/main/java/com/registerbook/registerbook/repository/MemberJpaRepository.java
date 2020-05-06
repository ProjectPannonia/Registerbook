package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.model.Member;
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

    @Query(value = "SELECT COUNT(name) FROM members",nativeQuery = true)
    int allMembers();

    //@Query("SELECT m.COUNTRY,COUNT(m.COUNTRY) FROM Members AS m GROUP BY m.COUNTRY ORDER BY m.COUNTRY")
    //@Query(value = "SELECT m.COUNTRY,COUNT(m.COUNTRY) FROM Members AS m GROUP BY m.COUNTRY ORDER BY m.COUNTRY")
    //List<Object[]> countByCountryObjects();

    //List<Object[]> countByCountryObjects2();
}
