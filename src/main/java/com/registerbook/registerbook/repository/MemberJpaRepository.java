package com.registerbook.registerbook.repository;

import com.registerbook.registerbook.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member,Long> {
    List<Member> findAll();
}
