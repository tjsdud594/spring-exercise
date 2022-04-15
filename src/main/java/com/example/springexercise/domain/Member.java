package com.example.springexercise.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String salt;

//    ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Member 이름은 제대로 받아져???   ->  " + name);
        this.name = name;
    }

//    Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        System.out.println("Member 작동되나 찍어봄 이메일 가져옴?? -> " + email);
        this.email = email;
    }

//    Password
    public String getPassword() {
        return password;
    }

    public  void  setPassword(String password) {
        System.out.println("Member 작동되나 찍어봄 비밀번호 가져옴?? -> " + password);
        this.password = password;
    }

//    비밀번호 암호화해서 세팅
    public Object saltPassword(String password) throws NoSuchAlgorithmException {
        String saltpass = password + getSalt();
        MessageDigest msg = MessageDigest.getInstance("SHA-512");
        msg.update(saltpass.getBytes());
        System.out.println("Member 작동되나 찍어봄 비밀번호 암호화 완료???1 -> " + password);
        this.password = String.format("%128x", new BigInteger(1, msg.digest()));
        System.out.println("Member 작동되나 찍어봄 비밀번호 암호화 완료???2 -> " + this.password);
        return this.password;
    }

//    Salt
    public String makeSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        this.salt = new String(Base64.getEncoder().encode(bytes));
//        System.out.println("Member 작동되나 찍어봄 bytes 생성됨?? -> " + bytes.toString());
        System.out.println("Member 작동되나 찍어봄 salt 암호화됨?? -> " + salt);
        return salt;
    }

    public String getSalt() {
        return salt;
    }

//    출처 및 참고 : https://bluemint.tistory.com/13
    public void setSalt(String salt) throws NoSuchAlgorithmException {
//        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//        byte[] bytes = new byte[16];
//        random.nextBytes(bytes);
//        this.salt = new String(Base64.getEncoder().encode(bytes));
//        System.out.println("Member 작동되나 찍어봄 bytes 생성됨?? -> " + bytes);
//        System.out.println("Member 작동되나 찍어봄 salt 암호화됨?? -> " + salt);
        this.salt = salt;
    }

}
