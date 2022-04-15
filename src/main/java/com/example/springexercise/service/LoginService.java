package com.example.springexercise.service;

import com.example.springexercise.controller.MemberForm;
import com.example.springexercise.domain.Member;
import com.example.springexercise.repository.MemberRepositoryInter;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LoginService {
    private final MemberRepositoryInter memberRepositoryInter;


    public LoginService(MemberRepositoryInter memberRepositoryInter) {
        this.memberRepositoryInter = memberRepositoryInter;
    }

    public Object login(Member member) throws NoSuchAlgorithmException {
        Member loginInfo = CheckMember(member);
        System.out.println("회원 DB 제대로 받아왔나?" + loginInfo.getEmail() + " // " + loginInfo.getPassword());
        String membersalt = loginInfo.getSalt();
        String fullcode = member.getPassword() + membersalt;
//        조합한 password + salt를 암호화해서 DB값과 비교
//        String saltpass = password + getSalt();
//        MessageDigest msg = MessageDigest.getInstance("SHA-512");
//        msg.update(fullcode.getBytes());
//        System.out.println("Member 작동되나 찍어봄 비밀번호 암호화 완료???1 -> " + password);
//        this.password = String.format("%128x", new BigInteger(1, msg.digest()));


        return member.getEmail();
    }

    private Member CheckMember(Member member) {
        memberRepositoryInter.findByEmail(member.getEmail());
        System.out.println("이메일로 회원유무 확인 id : " + memberRepositoryInter.findByEmail(member.getEmail()).get().getId());
        return memberRepositoryInter.findByEmail(member.getEmail()).get();
    }
//    private void CheckSalt(Member member) {
//        System.out.println("이메일로 salt값 조회");
//        memberRepositoryInter.SaltfindByEmail(member.getEmail());
//    }
}
