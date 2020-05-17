package com.registerbook.registerbook.controller;

import com.registerbook.registerbook.errorHandler.CustomErrorType;
import com.registerbook.registerbook.model.CountryEntity;
import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.model.MusicInstrument;
import com.registerbook.registerbook.service.countries.CountryServiceImplementation;
import com.registerbook.registerbook.service.musicinstruments.InstrumentServiceImplementation;
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
    private MemberServiceImplementation memberMemberServiceImplementation;
    private CountryServiceImplementation countryServiceImplementation;
    private InstrumentServiceImplementation instrumentServiceImplementation;

    @Autowired
    public void setMemberMemberServiceImplementation(MemberServiceImplementation memberMemberServiceImplementation) {
        this.memberMemberServiceImplementation = memberMemberServiceImplementation;
    }
    @Autowired
    public void setCountryServiceImplementation(CountryServiceImplementation countryServiceImplementation){
        this.countryServiceImplementation = countryServiceImplementation;
    }
    @Autowired
    public void setInstrumentServiceImplementation(InstrumentServiceImplementation instrumentServiceImplementation){
        this.instrumentServiceImplementation = instrumentServiceImplementation;
    }

    /* GET all basic musicians */
    @GetMapping("/")
    public ResponseEntity<List<Member>> listAllMember() {
        List<Member> allMembers = memberMemberServiceImplementation.getAllMember();
        if (allMembers.isEmpty()) {
            return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(allMembers, HttpStatus.OK);
    }

    /* POST a new musician to the database */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createMember(@Valid @RequestBody final Member member) {
        logger.info("Creating member: {}", member);
        if (memberMemberServiceImplementation.checkMemberWithThisNameAlreadyInDatabase(member.getName()) != null) {
            return new ResponseEntity<Member>(new CustomErrorType("Unable to create new member. A member with name: " + member.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        memberMemberServiceImplementation.saveNewMember(member);

        return new ResponseEntity<Member>(member, HttpStatus.CREATED);
    }

    /* GET a musician by id */
    @GetMapping("/{id}")
    public ResponseEntity<Member> getUserById(@PathVariable("id") final Long id) {
        Member member = memberMemberServiceImplementation.findMemberById(id);
        if (member == null) {
            return new ResponseEntity<Member>(new CustomErrorType("Member with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    /* PUT first search a musician by id, then update properties */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@PathVariable final Long id, @RequestBody Member member) {
        Member currentMember = memberMemberServiceImplementation.findMemberById(id);
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
        memberMemberServiceImplementation.deleteMemberById(id);
        return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
    }

    /* GET members by specified property */
    @PostMapping(value = "/searchproperty", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> getSpecifiedMembers(@RequestBody final String[] propertyAndValue) {
        List<Member> specifiedMembers = memberMemberServiceImplementation.searchBySpecifiedProperty(propertyAndValue);
        return new ResponseEntity<List<Member>>(specifiedMembers, HttpStatus.OK);
    }

    /* GET statistics */
    @GetMapping("/statistics")
    public ResponseEntity<StatisticData> getStatistics() {
        StatisticData resultStatistics = memberMemberServiceImplementation.getStatistics();
        return new ResponseEntity<StatisticData>(resultStatistics, HttpStatus.OK);
    }

    @GetMapping("/adminGuiRest")
    public ResponseEntity<List<CountryEntity>> loadCountriesToTheServer() {
        List<CountryEntity> result = countryServiceImplementation.loadCountriesToTheServer();
        return new ResponseEntity<List<CountryEntity>>(result, HttpStatus.OK);
    }

    @GetMapping("/getCountries")
    public ResponseEntity<String[]> getListOfCountries(){
        String[] result = countryServiceImplementation.getListOfCountries();
        return new ResponseEntity<String[]>(result,HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllCountries")
    public void deleteRegisteredCountries() {
        countryServiceImplementation.deleteAllCountries();
    }

    @DeleteMapping("/drop")
    public void drop(){
        countryServiceImplementation.dropTable();
    }

    @GetMapping("/getInstruments")
    public ResponseEntity<List<MusicInstrument>> getAllIstruments(){
        List<MusicInstrument> allInstruments =  instrumentServiceImplementation.getAllInstruments();
        return new ResponseEntity<List<MusicInstrument>>(allInstruments,HttpStatus.OK);
    }

    @PostMapping(value = "/createNewInstruemnt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MusicInstrument> createMember(@Valid @RequestBody final MusicInstrument musicInstrument) {
        logger.info("Creating member: {}", musicInstrument);
        if (instrumentServiceImplementation.isThisInstrumentAlreadiInDatabase(musicInstrument.getInstrumentName()) != null) {
            //return new ResponseEntity<MusicInstrument>(new CustomErrorType("Unable to create new music instrument. A music instrument with name: " + musicInstrument.getInstrumentName() + " already exist."), HttpStatus.CONFLICT);
        }
        instrumentServiceImplementation.Save(musicInstrument);

        return new ResponseEntity<MusicInstrument>(musicInstrument, HttpStatus.CREATED);
    }

}



