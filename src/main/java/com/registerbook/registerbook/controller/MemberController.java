package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.controller.errorHandler.apiError.ResourceNotFoundException;
import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.service.member.MemberServiceImplementation;
import com.registerbook.registerbook.service.member.statistics.StatisticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/register/member")
public class MemberController {

    //public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private MemberServiceImplementation memberServiceImplementation;

    @Autowired
    public void setMemberServiceImplementation(MemberServiceImplementation memberServiceImplementation) {
        this.memberServiceImplementation = memberServiceImplementation;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") final Long id) throws ResourceNotFoundException{
        ResponseEntity result = memberServiceImplementation.findMemberById(id);
        return result;
    }

    @GetMapping("/")
    public ResponseEntity<List<Member>> getAllRegisteredMembers() {
        return memberServiceImplementation.getAllRegisteredMembers();
    }

    @GetMapping(value = "/writeMembersToFile/{fileName}")
    public ResponseEntity<String> createFileFromMembers(@PathVariable("fileName") final String fileName) {
         return memberServiceImplementation.writeMembersToFile(fileName);
    }


    @GetMapping("/statistics")
    public ResponseEntity<StatisticData> getStatistics() {
        StatisticData resultStatistics = memberServiceImplementation.getStatistics();
        return new ResponseEntity<>(resultStatistics, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createMember(@Valid @RequestBody final Member member) {
        return memberServiceImplementation.saveNewMemberIfNotExist(member);
    }

    @PostMapping(value = "/searchproperty", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> getSpecifiedMembers(@RequestBody final String[] propertyAndValue) {
        return new ResponseEntity<>(memberServiceImplementation.searchBySpecifiedProperty(propertyAndValue), HttpStatus.OK);
    }

    @PostMapping(value = "/loadMembersFromFile/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loadMembersFromFile(@RequestBody final String filePath){
        memberServiceImplementation.loadMembersFromFileToServer(filePath);
        return new ResponseEntity<>("loaded",HttpStatus.OK);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@PathVariable final Long id, @RequestBody Member member) {
        return memberServiceImplementation.updateMemberIfExist(id,member);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable("id") final Long id) {
        memberServiceImplementation.deleteMemberById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}