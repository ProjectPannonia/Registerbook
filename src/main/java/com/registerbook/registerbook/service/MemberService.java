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

    public List<Member> getAllMember() {
        List<Member> allMember = memberJpaRepository.findAll();
        return allMember;
    }

    // Get the members of a band
    public List<Member> getBandMembers(String bandName) {
        List<Member> allMember = memberJpaRepository.findAll();
        List<Member> bandMembers = new ArrayList<>();
        for (Member m : allMember) {
            if (m.getBand().equals(bandName)) bandMembers.add(m);
        }
        return bandMembers;
    }

    // Get a list of members by name
    public List<Member> getMembersByName(String name) {
        List<Member> allMembers = memberJpaRepository.findAll();
        List<Member> result = new ArrayList<>();
        for (int i = 0; i < allMembers.size(); i++){
            Member actual = allMembers.get(i);
            if(actual.getName().equals(name))
                result.add(actual);
        }
        return result;
    }

    public void save(Member member) {
        memberJpaRepository.save(member);
    }
    public Member findById(Long id) {
        List<Member> allMembers = memberJpaRepository.findAll();
        Member member = null;
        for (Member m : allMembers) {
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
    // Get a member by name
    /*public Member findByName(String name) {
        List<Member> allMembers = memberJpaRepository.findAll();
        String[] separateTheName = separateName(name);
        String firstName = separateTheName[0].toString();
        String lastName = separateTheName[1];
        Member result = findByNameTags(firstName,lastName);

        return result;
    }
*/

    public Member findByNameCreating(String name) {
        List<Member> allMembers = memberJpaRepository.findAll();
        Member result = null;

        for (int i = 0; i < allMembers.size(); i++) {
            Member actual = allMembers.get(i);
            if (actual.getName().equals(name)) {
                result = actual;
            }
        }
        return result;
    }

    /*Private methods*/
    // getMembersByname assistant functions
    /*private Member findByNameTags(String firstName,String lastName){
        List<Member> allMembers= memberJpaRepository.findAll();
        for(int i = 0; i < allMembers.size(); i++){
            Member actual = allMembers.get(i);
            if(actual.getFirstName().equals(firstName)){
                if(actual.getLastName().equals(lastName))
                    return actual;
            }
        }
        return null;
    }*//*
    public List<Member> nameSearchResult(String name) {
        List<Member> allMembers = memberJpaRepository.findAll();
        List<Member> result = new ArrayList<>();

        for (Member m : allMembers) {
            if (m.getName().equals(name)) {
                result.add(m);
            }
        }
        return result;
    }
/*
    public String[] separateName(String name) {
        String[] result = new String[2];
        int upperCaseLetterPosition = 0;

        for (int i = 1; i < name.length(); i++){
            if (Character.isUpperCase(name.charAt(i))) {
                upperCaseLetterPosition = i;
                break;
            }
        }
        String firstName = name.substring(0,upperCaseLetterPosition).trim();
        String lastName = name.substring(upperCaseLetterPosition).trim();
        result[0] = firstName;
        result[1] = lastName;

        return result;
    }

*/
}