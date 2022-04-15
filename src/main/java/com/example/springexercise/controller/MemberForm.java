package com.example.springexercise.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("MemberForm에서 찍히나 name  ->  " + name);
        this.name = name;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        System.out.println("MemberForm에서 찍히나 email  ->  " + email);
        this.email = email;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("MemberForm에서 찍히나 password  ->  " + password);
        this.password = password;
    }
}
