package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.errorHandler.CustomErrorType;
import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /* BASIC REST FUNCTIONS */

    // GET all basic musicians
    @GetMapping("/")
    public ResponseEntity<List<Member>> listAllMember(){
        List<Member> allMembers = memberMemberService.getAllMember();
        if(allMembers.isEmpty()){
            return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(allMembers,HttpStatus.OK);
    }

    // POST a new musician to the database
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createMember(@Valid @RequestBody final Member member){
        logger.info("Creating member: {}",member);
        if(memberMemberService.findByNameCreating(member.getName()) != null){
            return new ResponseEntity<Member>(new CustomErrorType("Unable to create new member. A member with name: " + member.getName()+ " already exist."),HttpStatus.CONFLICT);
        }
        memberMemberService.save(member);

        return new ResponseEntity<Member>(member,HttpStatus.CREATED);
    }

    // GET a musician by id
    @GetMapping("/{id}")
    public ResponseEntity<Member> getUserById(@PathVariable("id") final Long id){
        Member member = memberMemberService.findById(id);
        if(member == null){
            return new ResponseEntity<Member>(new CustomErrorType("Member with id " + id + " not found"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Member>(member,HttpStatus.OK);
    }

    // PUT first search a musician by id, then update properties
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@PathVariable final Long id, @RequestBody Member member){
        Member currentMember = memberMemberService.findById(id);
        if(currentMember == null){
            return new ResponseEntity<Member>(new CustomErrorType("Unable to update. Member with id " + id + " not found."),HttpStatus.NOT_FOUND);
        }
        currentMember.setName(member.getName());
        currentMember.setBand(member.getBand());
        currentMember.setAddress(member.getAddress());
        currentMember.setEmail(member.getEmail());
        currentMember.setFavouriteAnimal(member.getFavouriteAnimal());
        currentMember.setFavouriteMeal(member.getFavouriteMeal());
        return new ResponseEntity<Member>(currentMember,HttpStatus.OK);
    }

    // DELETE  musician by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable("id") final Long id){
        memberMemberService.deleteById(id);
        return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
    }

    // Get a member by name
    @GetMapping("/searchname/{name}")
    public ResponseEntity<List<Member>> getMemberByName(@PathVariable("name") final String name){
        List<Member> members = memberMemberService.getMembersByName(name);
        return new ResponseEntity<List<Member>>(members,HttpStatus.OK);
    }
    // GET members by specified property
    @PostMapping(value = "/searchproperty", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> getSpecifiedMembers(@RequestBody final String[] content){
        List<Member> specifiedMembers = memberMemberService.searchByProperty(content);
        return new ResponseEntity<List<Member>>(specifiedMembers,HttpStatus.OK);
    }
    //GET statistics
    @GetMapping("/statistics")
    public ResponseEntity<int[]> getStatistics(){
        int[] resultStatistics = memberMemberService.getStatistics();
        return new ResponseEntity<int[]>(resultStatistics,HttpStatus.OK);
    }
}