package com.registerbook.registerbook.service;

import com.registerbook.registerbook.repository.MemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberJpaRepository memberJpaRepository;


}
