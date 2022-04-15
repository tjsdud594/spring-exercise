package com.example.springexercise.service;

import com.example.springexercise.domain.Member;
import com.example.springexercise.repository.MemberRepositoryInter;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

//@Transactional
@Service
public class SignUpService {
    private final MemberRepositoryInter memberRepositoryInter;


    public SignUpService(MemberRepositoryInter memberRepositoryInter) {
        this.memberRepositoryInter = memberRepositoryInter;
    }


    //    회원가입
    public Object join(Member member) throws NoSuchAlgorithmException {
        System.out.println("조인함수 시작!!!---->    " + member.getEmail());
//        validateDuplicateMember(member);
        System.out.println("중복확인 완료");
        memberRepositoryInter.save(member);
        System.out.println("SignUpService 확인  ->  " + member.getName() + " ++++ " + member.getEmail());
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepositoryInter.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

//    private void validateDuplicateMember(Member member) {
//        memberRepositoryInter.fi
//    }
}
