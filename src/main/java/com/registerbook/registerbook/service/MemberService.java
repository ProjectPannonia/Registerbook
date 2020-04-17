package com.registerbook.registerbook.service;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    private List<Member> allMember = getAllMember();

    public List<Member> getAllMember() {
        List<Member> allMember = memberJpaRepository.findAll();
        return allMember;
    }

    // Get the members of a band
    public List<Member> getBandMembers(String bandName) {
        List<Member> bandMembers = new ArrayList<>();
        for (Member m : allMember) 
            if (m.getBand().equals(bandName)) bandMembers.add(m);

        return bandMembers;
    }

    // Get a list of members by name
    public List<Member> getMembersByName(String name) {
        List<Member> result = new ArrayList<>();
        for (int i = 0; i < allMember.size(); i++){
            Member actual = allMember.get(i);
            if(actual.getName().equals(name))
                result.add(actual);
        }
        return result;
    }

    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    public Member findById(Long id) {
        Member member = null;
        for (Member m : allMember) {
            if (m.getId() == id) member = m;
        }
        return member;
    }

    public void deleteById(Long id) {
        memberJpaRepository.deleteById(id);
    }

    public void delete(Member member) {
        memberJpaRepository.delete(member);
    }

    public Member findByNameCreating(String name) {
        Member result = null;
        for (int i = 0; i < allMember.size(); i++) {
            Member actual = allMember.get(i);
            if (actual.getName().equals(name))
                result = actual;
        }
        return result;
    }
    public List<Member> specifiedSearch(String[] content) {
        return specifier(content);
    }

    // Private methods
    private List<Member> specifier(String[] content){
        List<Member> result = new ArrayList<>();
        String property = content[0];
        String value = content[1];
        switch (property){
            case "Name" :  result = getMembersByName(value);
                break;
            case "Band" : result = getMembersByBand(value);
                break;
            case "Favourite animal" : result = getMembersByFavouriteAnimal(value);
                break;
            case "Favourite meal" : result = getMembersByFavouriteMeal(value);
                break;
            case "Instrument" : result = getMembersByInstrument(value);
                break;
            case "Country" : result = getMembersByCountry(value);
                break;
            case "City" : result = getMembersByCity(value);
                break;
        }
        return result;
    }
    private List<Member> getMembersByCity(String value) {
        List<Member> result = new ArrayList<>();
        for(Member m : allMember){
            if(m.getAddress().equals(value)){
                result.add(m);
            }
        }
        return result;
    }
    private List<Member> getMembersByCountry(String value) {
        List<Member> result = new ArrayList<>();
        for(Member m : allMember){
            if(m.getCountry().equals(value)){
                result.add(m);
            }
        }
        return result;
    }
    private List<Member> getMembersByInstrument(String value) {
        List<Member> result = new ArrayList<>();
        for(Member m : allMember){
            if(m.getInstrument().equals(value)){
                result.add(m);
            }
        }
        return result;
    }
    private List<Member> getMembersByBand(String value) {
        List<Member> result = new ArrayList<>();
        for(Member m : allMember){
            if(m.getBand().equals(value)) result.add(m);
        }
        return result;
    }
    private List<Member> getMembersByFavouriteMeal(String value) {
        List<Member> result = new ArrayList<>();
        for(Member m : allMember){
            if(m.getFavouriteMeal().equals(value)) {
                result.add(m);
            }
        }
        return result;
    }
    private List<Member> getMembersByFavouriteAnimal(String value) {
        List<Member> result = new ArrayList<>();
        for(Member m : allMember){
            if(m.getFavouriteAnimal().equals(value))
                result.add(m);
        }
        return result;
    }
}