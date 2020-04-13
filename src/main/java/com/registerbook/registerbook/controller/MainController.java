package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register/member")
public class MainController {
    public static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private MemberService memberMemberService;

    @Autowired
    public void setMemberMemberService(MemberService memberMemberService){
        this.memberMemberService = memberMemberService;
    }

}
