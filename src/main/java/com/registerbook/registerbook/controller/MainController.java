package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Member> createMember(@RequestBody final Member member){
        logger.info("Creating member: {}",member);
        if(memberMemberService.findByNameCreating(member.getFirstName(), member.getLastName()) != null){
            //return new ResponseEntity<Member>(new CustomErrorType("Unable to create new member. A member with name: " + member.getFirstName() + " " + member.getLastName() + " already exist."),HttpStatus.CONFLICT);
        }
        memberMemberService.save(member);

        return new ResponseEntity<Member>(member,HttpStatus.CREATED);
    }

    // GET a musician by id
    @GetMapping("/{id}")
    public ResponseEntity<Member> getUserById(@PathVariable("id") final Long id){
        Member member = memberMemberService.findById(id);
        if(member == null){
            //return new ResponseEntity<Member>(new CustomErrorType("Member with id " + id + " not found"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Member>(member,HttpStatus.OK);
    }

    // PUT first search a musician by id, then update properties
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@PathVariable final Long id, @RequestBody Member member){
        Member currentMember = memberMemberService.findById(id);
        if(currentMember == null){
            //return new ResponseEntity<Member>(new CustomErrorType("Unable to update. Member with id " + id + " not found."),HttpStatus.NOT_FOUND);
        }
        currentMember.setFirstName(member.getFirstName());
        currentMember.setLastName(member.getLastName());
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



    /* ADVANCED REST FUNCTIONS */

    //Delete member by name
    /*@DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<Member> deleteMemberByName(@PathVariable("name") final String name){
        Member member = memberMemberService.findByName(name);
        memberMemberService.delete(member);
        return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
    }
    */
    //get band members
    /*@GetMapping("/bandMembers/{bandName}")
    public ResponseEntity<List<Member>> getBandMembers(@PathVariable("bandName") String bandName){
        List<Member> bandMembers = memberMemberService.getBandMembers(bandName);
        if(bandMembers.isEmpty()){
            return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(bandMembers,HttpStatus.OK);
    }*/

    //get members list by name
    /*@GetMapping("/searchByName/{name}")
    public ResponseEntity<List<Member>> getMemberListByName(@PathVariable("name") String name){
        List<Member> membersByName = memberMemberService.getMembersByName(name);
        if (membersByName.isEmpty()){
            return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(membersByName,HttpStatus.OK);
    }
    */
    
    // Get a member by name
    @GetMapping("/searchWithName/{name}")
    public ResponseEntity<Member> getMemberByName(@PathVariable("name") String name){
        Member member = memberMemberService.findByName(name);
        if(member == null){
            return new ResponseEntity<Member>(member,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Member>(member,HttpStatus.OK);
    }
}