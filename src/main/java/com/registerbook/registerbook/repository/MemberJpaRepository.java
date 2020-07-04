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

    @Query(value = "SELECT COUNT(name) FROM members",nativeQuery = true)
    int numberOfMembers();

    @Query(value = "SELECT country FROM members GROUP BY country ORDER BY country",nativeQuery = true)
    List<String> registeredCountries();

    @Query(value = "SELECT COUNT(DISTINCT band) FROM members", nativeQuery = true)
    int numberOfRegisteredBands();

    @Query(value = "SELECT s FROM Member s WHERE s.band = :sband")
    List<Member> getBandMembers(@Param("sband") String sband);

    @Query(value = "SELECT s FROM Member s WHERE s.name = :memberName")
    Member getMemberByName(@Param("memberName") String memberName);

    @Query(value = "SELECT s FROM Member s WHERE s.address = :mCity")
    List<Member> getMemberByCity(@Param("mCity") String mCity);

    @Query(value = "SELECT s FROM Member s WHERE s.instrument = :mInstrument")
    List<Member> getMemberByInstrument(@Param("mInstrument") String mInstrument);

    @Query(value = "SELECT s FROM Member s WHERE s.country = :mCountry ")
    List<Member> getMemberByCountry(@Param("mCountry") String mCountry);

    @Query(value = "SELECT s FROM Member s WHERE s.id = :sId")
    Member findMemberById(@Param("sId") Long sId);

    @Query(value = "SELECT s FROM Member s WHERE s.name = :sName")
    Member findMemberByName(@Param("sName") String sName);

    @Query(value = "SELECT COUNT(s.name) FROM Member s WHERE s.country =:country")
    int numberOfMembersPerSpecifiedCountry(@Param("country") String country);

    @Query(value = "SELECT s FROM Member s WHERE s.id = :sId")
    List<Member> getMemberById(@Param("sId") Long sId);

}
