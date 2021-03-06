package com.registerbook.registerbook.service.member;

import com.registerbook.registerbook.controller.errorHandler.apiError.ResourceNotFoundException;
import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.member.fileOperation.fileReader.MembersFileReader;
import com.registerbook.registerbook.service.member.fileOperation.fileWriter.FileWriter;
import com.registerbook.registerbook.service.member.statistics.UpdatedStatistic.CountryAndQuantity;
import com.registerbook.registerbook.service.member.statistics.StatisticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImplementation implements MemberService {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Override
    public ResponseEntity<Member> findMemberById(Long id){
        Member member = memberJpaRepository.findMemberById(id);

        if(member == null)
            throw new ResourceNotFoundException(Member.class,"id",id.toString());

        return new ResponseEntity<>(member,HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllRegisteredMembers() {
        List<Member> allMembers = memberJpaRepository.findAll();
        HttpStatus responseStatus = !allMembers.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(allMembers,responseStatus);
    }

    @Override
    public ResponseEntity<String> writeMembersToFile(String fileName) {
        List<Member> membersInDatabase = memberJpaRepository.findAll();
        String answerToFrontEnd = !membersInDatabase.isEmpty() ? "File created. Name: " + fileName + ".txt" : "Database empty.";
        HttpStatus responseStatus = !membersInDatabase.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        if (!membersInDatabase.isEmpty())
            FileWriter.writeToFile(membersInDatabase,fileName);

        return new ResponseEntity<>(answerToFrontEnd,responseStatus);
    }

    @Override
    public ResponseEntity<Member> saveNewMemberIfNotExist(Member member) {
        Member searchedMember = memberJpaRepository.findMemberByName(member.getName());
        HttpStatus responseStatus = (searchedMember == null) ?  HttpStatus.CREATED : HttpStatus.CONFLICT;

        if (searchedMember == null){
            memberJpaRepository.save(member);
            searchedMember = memberJpaRepository.findMemberByName(member.getName());
        }

        return new ResponseEntity<>(searchedMember,responseStatus);
    }



    @Override
    public ResponseEntity<Member> updateMemberIfExist(Long id, Member member) {
        Member requiredToUpdate = memberJpaRepository.findMemberById(id);
        HttpStatus responseStatus = (requiredToUpdate != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        Member updatedMember = updateMemberValues(requiredToUpdate,member);

        memberJpaRepository.save(updatedMember);

        return new ResponseEntity<Member>(updatedMember,responseStatus);
    }

    @Override
    public List<Member> getAllMember() {
        return memberJpaRepository.findAll();
    }

    @Override
    public void saveNewMember(Member member){
        memberJpaRepository.save(member);
    }

    @Override
    public void deleteMemberById(Long id) {
        memberJpaRepository.deleteById(id);
    }

    @Override
    public Member checkMemberWithThisNameAlreadyInDatabase(String name) {
        return memberJpaRepository.findMemberByName(name);
    }
    // Specified search(propertyAndValue[0] = property, propertyAndValue[1] = searchedValue)
    @Override
    public List<Member> searchBySpecifiedProperty(String[] propertyAndValue) {
        return specifier(propertyAndValue);
    }

    // Method to get statistics
    @Override
    public StatisticData getStatistics(){

        int numberOfRegisteredMembers = memberJpaRepository.numberOfMembers();
        int numberOfMusicBands = memberJpaRepository.numberOfRegisteredBands();
        List<CountryAndQuantity> countryAndQuantities = new ArrayList<>();
        List<String> registeredUniqueCountries = memberJpaRepository.registeredCountries();
        int numberOfCountries =  registeredUniqueCountries.size();

        for (int i = 0; i < registeredUniqueCountries.size(); i++){
            String actualCountryName = registeredUniqueCountries.get(i);
            int numberFromActualCountry = memberJpaRepository.numberOfMembersPerSpecifiedCountry(actualCountryName);
            countryAndQuantities.add(new CountryAndQuantity(actualCountryName,numberFromActualCountry));
        }
        return new StatisticData(numberOfRegisteredMembers,numberOfMusicBands,countryAndQuantities,numberOfCountries);
    }

    @Override
    public void loadMembersFromFileToServer(String path) {
        List<Member> loadedMembersFromFile = MembersFileReader.readMembersFromFile(path);
        Member checkItInDatabase;

        for (Member m : loadedMembersFromFile){
            checkItInDatabase = memberJpaRepository.getMemberByName(m.getName());
            if(checkItInDatabase == null){
                memberJpaRepository.save(m);
            }else {
                continue;
            }
        }
    }

    /* Private assistant methods */

    private Member updateMemberValues(Member oldProperties, Member newProperties){
        Member updatedMember = new Member();

        updatedMember.setName(newProperties.getName());
        updatedMember.setAddress(newProperties.getAddress());
        updatedMember.setBand(newProperties.getBand());
        updatedMember.setYearOfBirth(newProperties.getYearOfBirth());
        updatedMember.setEmail(newProperties.getEmail());
        updatedMember.setInstrument(newProperties.getInstrument());
        updatedMember.setCountry(newProperties.getCountry());

        return updatedMember;
    }
    private List<Member> specifier(String[] content){
        List<Member> result = new ArrayList<>();
        String property = content[0];
        String value = content[1];
        switch (property){
            case "Id" : result = memberJpaRepository.getMemberById(Long.parseLong(value));
                break;
            case "Name" :  result.add(memberJpaRepository.getMemberByName(value));
                break;
            case "Band" : result = memberJpaRepository.getBandMembers(value);
                break;
            case "Instrument" : result = memberJpaRepository.getMemberByInstrument(value);
                break;
            case "Country" : result = memberJpaRepository.getMemberByCountry(value);
                break;
            case "City" : result = memberJpaRepository.getMemberByCity(value);
                break;
        }
        return result;
    }
}