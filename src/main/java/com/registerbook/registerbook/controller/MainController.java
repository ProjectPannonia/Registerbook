package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.controller.errorHandler.CustomErrorType;
import com.registerbook.registerbook.repository.model.Member;
import com.registerbook.registerbook.service.register.MemberServiceImplementation;
import com.registerbook.registerbook.service.register.specialObjectsForStatistics.StatisticData;
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
    private MemberServiceImplementation memberServiceImplementation;


    @Autowired
    public void setMemberServiceImplementation(MemberServiceImplementation memberServiceImplementation) {
        this.memberServiceImplementation = memberServiceImplementation;
    }

    /* GET all basic musicians */
    @GetMapping("/")
    public ResponseEntity<List<Member>> listAllMember() {
        List<Member> allMembers = memberServiceImplementation.getAllMember();
        if (allMembers.isEmpty()) {
            return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(allMembers, HttpStatus.OK);
    }

    /* POST a new musician to the database */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createMember(@Valid @RequestBody final Member member) {
        logger.info("Creating member: {}", member);
        if (memberServiceImplementation.checkMemberWithThisNameAlreadyInDatabase(member.getName()) != null) {
            return new ResponseEntity<Member>(new CustomErrorType("Unable to create new member. A member with name: " + member.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        memberServiceImplementation.saveNewMember(member);

        return new ResponseEntity<Member>(member, HttpStatus.CREATED);
    }

    /* GET a musician by id */
    @GetMapping("/{id}")
    public ResponseEntity<Member> getUserById(@PathVariable("id") final Long id) {
        Member member = memberServiceImplementation.findMemberById(id);
        if (member == null) {
            return new ResponseEntity<Member>(new CustomErrorType("Member with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    /* PUT first search a musician by id, then update properties */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@PathVariable final Long id, @RequestBody Member member) {
        Member currentMember = memberServiceImplementation.findMemberById(id);
        if (currentMember == null) {
            return new ResponseEntity<Member>(new CustomErrorType("Unable to update. Member with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentMember.setName(member.getName());
        currentMember.setBand(member.getBand());
        currentMember.setAddress(member.getAddress());
        currentMember.setEmail(member.getEmail());
        currentMember.setYearOfBirth(member.getYearOfBirth());

        return new ResponseEntity<Member>(currentMember, HttpStatus.OK);
    }

    /* DELETE  musician by id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable("id") final Long id) {
        memberServiceImplementation.deleteMemberById(id);
        return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
    }

    /* GET members by specified property */
    @PostMapping(value = "/searchproperty", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> getSpecifiedMembers(@RequestBody final String[] propertyAndValue) {
        List<Member> specifiedMembers = memberServiceImplementation.searchBySpecifiedProperty(propertyAndValue);
        return new ResponseEntity<List<Member>>(specifiedMembers, HttpStatus.OK);
    }

    /* GET statistics */
    @GetMapping("/statistics")
    public ResponseEntity<StatisticData> getStatistics() {
        StatisticData resultStatistics = memberServiceImplementation.getStatistics();
        return new ResponseEntity<StatisticData>(resultStatistics, HttpStatus.OK);
    }
}


