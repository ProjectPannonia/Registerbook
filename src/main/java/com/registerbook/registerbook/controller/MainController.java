package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/register/member")
public class MainController {
    public static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private MemberService memberMemberService;

    @Autowired
    public void setMemberMemberService(MemberService memberMemberService){
        this.memberMemberService = memberMemberService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Member>> listAllMember(){
        List<Member> allMembers = memberMemberService.getAllMember();
        if(allMembers.isEmpty()){
            return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(allMembers,HttpStatus.OK);
    }
    @GetMapping("/bandMembers/{bandName}")
    public ResponseEntity<List<Member>> getBandMembers(@PathVariable("bandName") String bandName){
        List<Member> bandMembers = memberMemberService.getBandMembers(bandName);
        if(bandMembers.isEmpty()){
            return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(bandMembers,HttpStatus.OK);
    }
    @GetMapping("/searchByName/{name}")
    public ResponseEntity<List<Member>> getMembersByName(@PathVariable("name") String name){
        List<Member> membersByName = memberMemberService.getMembersByName(name);
        if (membersByName.isEmpty()){
            return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(membersByName,HttpStatus.OK);
    }

}
