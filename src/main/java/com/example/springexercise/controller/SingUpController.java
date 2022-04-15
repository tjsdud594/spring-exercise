package com.example.springexercise.controller;

import com.example.springexercise.domain.Member;
import com.example.springexercise.repository.MemberRepositoryInter;
import com.example.springexercise.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SingUpController {

    private final SignUpService signUpService;

    @Autowired
    public SingUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/signup")
    public String createFrom() {
        return "signup";
    }

    @PostMapping("/signup")
    public String create(MemberForm form, Model model) throws NoSuchAlgorithmException {
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.saltPassword(form.getPassword());
        member.makeSalt();
        System.out.println("SignupController 비밀번호 저장됬나 member.getpassword() ->" + member.getPassword());
        System.out.println("SignupController salt 입력됬나 member.getsalt() ->" + member.getSalt());

        model.addAttribute("message", "이미 가입된 회원입니다!");

        try {
            System.out.println("회원가입함수 들어간다아아ㅏ");
            signUpService.join(member);
            return "redirect:/";
        } catch (IllegalStateException e) {
            return "signup";
        }


//            return "redirect:/";
        }
    }

