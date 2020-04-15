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

    // Get the members of a specified band
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
        List<Member> result = nameSearchResult(name);
        return result;
    }

    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    // Get a member by name
    public Member findByName(String name) {
        List<Member> allMembers = memberJpaRepository.findAll();
        String[] separateTheName = separateName(name);
        String firstName = separateTheName[0].toString();
        String lastName = separateTheName[1];
        Member result = findByNameTags(firstName,lastName);

        return result;
    }

    public Member findByNameCreating(String firstName, String lastName) {
        List<Member> allMembers = memberJpaRepository.findAll();
        Member result = null;

        for (int i = 0; i < allMembers.size(); i++) {
            Member actual = allMembers.get(i);
            if (actual.getFirstName().equals(firstName) && actual.getLastName().equals(lastName)) {
                result = actual;
            }
        }

        return result;
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

    /*Private methods*/
    // getMembersByname assistant functions
    private Member findByNameTags(String firstName,String lastName){
        List<Member> allMembers= memberJpaRepository.findAll();
        for (Member m : allMembers){
            if(m.getFirstName().equals(firstName)&& m.getLastName().equals(lastName)){
                return m;
            }
        }
        return null;
    }
    private List<Member> nameSearchResult(String name) {
        List<Member> allMembers = memberJpaRepository.findAll();
        List<Member> result = new ArrayList<>();
        String[] separatedName = separateName(name);
        for (Member m : allMembers) {
            if (m.getFirstName().equals(separatedName[0]) && m.getLastName().equals(separatedName[1])) {
                result.add(m);
            }
        }
        return result;
    }

    private String[] separateName(String name) {
        char[] chars = name.toCharArray();
        ArrayList<Character> firstName = new ArrayList<>();
        firstName.add(chars[0]);
        ArrayList<Character> lastName = new ArrayList<>();
        boolean flag = false;
        for (int i = 1; i < chars.length; i++){
            char act = chars[i];
            if(Character.isUpperCase(act)) flag = true;
            if(!Character.isLetter(act)) continue;
            if(!flag) firstName.add(act);
            if(flag) lastName.add(act);
        }
        String[] result = new String[]{firstName.toString(),lastName.toString()};
        return result;
    }


}