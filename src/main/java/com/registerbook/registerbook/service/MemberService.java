package com.registerbook.registerbook.service;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.statistics.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberJpaRepository memberJpaRepository;


    public List<Member> getAllMember() {
        return memberJpaRepository.findAll();
    }
/*
    public void saveNewMember(Member member) {
        memberJpaRepository.save(member);
    }
*/
    public void saveNewMember(Member member){
        memberJpaRepository.save(member);
    }
    public Member findMemberById(Long id) {
        return memberJpaRepository.findMemberById(id);
    }

    public void deleteMemberById(Long id) {
        memberJpaRepository.deleteById(id);
    }

    public Member checkMemberWithThisNameAlreadyInDatabase(String name) {
        return memberJpaRepository.findMemberByName(name);
    }
    // Specified search(content[0] = property, content[1] = searchedText)
    public List<Member> searchBySpecifiedProperty(String[] content) {
        return specifier(content);
    }
    // Method to get statistics
    public StatisticData getStatistics(){
        int numberOfRegisteredMembers = memberJpaRepository.numberOfMembers();
        int numberOfRegisteredBands = memberJpaRepository.numberOfRegisteredBands();
        List<String> registeredCountries = memberJpaRepository.registeredCountries();
        List<Integer> memberPerCountry = memberJpaRepository.numberOfMembersPerCountry();
        Statistics statistics = new Statistics();
        StatisticData result = statistics.getAdvancedStatistics(numberOfRegisteredMembers,numberOfRegisteredBands,registeredCountries,memberPerCountry);

        return result;
    }

    /* Private assistant methods */
    private List<Member> specifier(String[] content){
        List<Member> result = new ArrayList<>();
        String property = content[0];
        String value = content[1];
        switch (property){
            case "Name" :  result = memberJpaRepository.getMemberByName(value);
                break;
            case "Band" : result = memberJpaRepository.getBandMembers(value);;
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