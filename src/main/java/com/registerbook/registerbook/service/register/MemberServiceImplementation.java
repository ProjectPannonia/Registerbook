package com.registerbook.registerbook.service.register;

import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.register.fileOperation.fileReader.MembersFileReader;
import com.registerbook.registerbook.service.register.fileOperation.fileWriter.FileWriter;
import com.registerbook.registerbook.service.register.statistics.UpdatedStatistic.CountryAndQuantity;
import com.registerbook.registerbook.service.register.statistics.StatisticData;
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
    public ResponseEntity<Member> findMemberByIdIfExist(Long id) {
        Member searchedMember = memberJpaRepository.findMemberById(id);
        HttpStatus responseStatus = (searchedMember == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(searchedMember,responseStatus);
    }

    @Override
    public ResponseEntity<Member> updateMemberIfExist(Long id, Member member) {
        Member searchedToUpdate = memberJpaRepository.findMemberById(id);
        HttpStatus responseStatus = (searchedToUpdate != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        searchedToUpdate.setName(member.getName());
        searchedToUpdate.setInstrument(member.getInstrument());
        searchedToUpdate.setCountry(member.getCountry());
        searchedToUpdate.setYearOfBirth(member.getYearOfBirth());
        searchedToUpdate.setEmail(member.getEmail());
        searchedToUpdate.setBand(member.getBand());
        searchedToUpdate.setAddress(member.getAddress());

        memberJpaRepository.save(searchedToUpdate);

        return new ResponseEntity<Member>(searchedToUpdate,responseStatus);
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
    public Member findMemberById(Long id) {
        return memberJpaRepository.findMemberById(id);
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
    private List<Member> specifier(String[] content){
        List<Member> result = new ArrayList<>();
        String property = content[0];
        String value = content[1];
        switch (property){
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

    public Member getMemberById(String name) {
        return memberJpaRepository.getMemberByName(name);
    }
}