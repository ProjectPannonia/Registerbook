package com.registerbook.registerbook.service.register;

import com.registerbook.registerbook.model.entities.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import com.registerbook.registerbook.service.register.fileOperation.fileReader.MembersFileReader;
import com.registerbook.registerbook.service.register.statistics.StatisticData;
import com.registerbook.registerbook.service.register.statistics.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImplementation implements MemberService {

    @Autowired
    MemberJpaRepository memberJpaRepository;

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
        int numberOfRegisteredBands = memberJpaRepository.numberOfRegisteredBands();
        List<String> registeredCountries = memberJpaRepository.registeredCountries();
        List<Integer> memberPerCountry = memberJpaRepository.numberOfMembersPerCountry();
        Statistics statistics = new Statistics();
        StatisticData result = statistics.getAdvancedStatistics(numberOfRegisteredMembers,numberOfRegisteredBands,registeredCountries,memberPerCountry);

        return result;
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