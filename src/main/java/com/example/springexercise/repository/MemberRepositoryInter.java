package com.example.springexercise.repository;

import com.example.springexercise.domain.Member;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface MemberRepositoryInter {

    Member save(Member member) throws NoSuchAlgorithmException;

    Optional<Member> findByEmail(String email);

//    Optional<Member> SaltfindByEmail(String email);

    List<Member> findAll();
}
