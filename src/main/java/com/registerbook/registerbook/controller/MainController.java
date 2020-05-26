package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.controller.errorHandler.CustomErrorType;
import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.service.register.MemberServiceImplementation;
import com.registerbook.registerbook.service.register.statistics.StatisticData;
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

    @GetMapping("/")
    public ResponseEntity<List<Member>> sendRegisteredMembersToFrontEnd() {
        return memberServiceImplementation.getAllRegisteredMembers();
    }

    @GetMapping(value = "/writeMembersToFile/{fileName}")
    public ResponseEntity<String> writeMembersToFile(@PathVariable("fileName") final String fileName) {
         return memberServiceImplementation.writeMembersToFile(fileName);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createMember(@Valid @RequestBody final Member member) {
        /*logger.info("Creating member: {}", member);
        //ResponseEntity<Member> saveThisMember = memberServiceImplementation.saveNewMemberIfNotExist(member.getName());
        if (memberServiceImplementation.checkMemberWithThisNameAlreadyInDatabase(member.getName()) != null) {
            return new ResponseEntity<Member>(new CustomErrorType("Unable to create new member. A member with name: " + member.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        memberServiceImplementation.saveNewMember(member);

        Member result = memberServiceImplementation.getMemberById(member.getName());
        return new ResponseEntity<Member>(result, HttpStatus.CREATED);

*/
        return memberServiceImplementation.saveNewMemberIfNotExist(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") final Long id) {
        return memberServiceImplementation.findMemberByIdIfExist(id);
    }

    /* PUT first search a musician by id, then update properties */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@PathVariable final Long id, @RequestBody Member member) {
        /*ResponseEntity<Member> toUpdateMember = memberServiceImplementation.updateMemberIfExist(id,member);
        Member currentMember = memberServiceImplementation.findMemberById(id);
        if (currentMember == null) {
            return new ResponseEntity<Member>(new CustomErrorType("Unable to update. Member with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentMember.setName(member.getName());
        currentMember.setBand(member.getBand());
        currentMember.setAddress(member.getAddress());
        currentMember.setEmail(member.getEmail());
        currentMember.setYearOfBirth(member.getYearOfBirth());
        memberServiceImplementation.saveNewMember(currentMember);
        
        return new ResponseEntity<Member>(currentMember, HttpStatus.OK);

         */
        return memberServiceImplementation.updateMemberIfExist(id,member);
    }
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
    @GetMapping(value = "/loadMembersFromFile/{filePath}")
    public ResponseEntity<String> loadMembersFromFile(@PathVariable("filePath") final String filePath){
        memberServiceImplementation.loadMembersFromFileToServer("D://LoadMembersToServer.txt");
        return new ResponseEntity<String>("loaded",HttpStatus.OK);
    }

}


