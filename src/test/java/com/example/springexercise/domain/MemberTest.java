package com.example.springexercise.domain;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void getSalt() {
    }

    @Test
    void setSalt(String salt) throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
//        this.salt = salt;
//        System.out.println("Member 작동되나 찍어봄 salt값 생성됨?? -> " + salt);
//        System.out.println("Member 작동되나 찍어봄 random 생성됨?? -> " + random);
        System.out.println("Member 작동되나 찍어봄 bytes 생성됨?? -> " + bytes);
    }
}