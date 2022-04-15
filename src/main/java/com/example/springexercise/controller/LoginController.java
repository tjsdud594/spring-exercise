package com.example.springexercise.controller;

import com.example.springexercise.domain.Member;
import com.example.springexercise.service.LoginService;
import com.example.springexercise.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;

@Controller
public class LoginController {

    private  final LoginService LoginService;

    @Autowired
    public LoginController(LoginService LoginService) {
        this.LoginService = LoginService;
    }

    @GetMapping("/login")
    public  String LoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String LoginMember(MemberForm form, Model model) throws NoSuchAlgorithmException {
        Member member = new Member();

        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        System.out.println("loginController 이메일 입력됬나 form.getEmail() ->" + form.getEmail());

        try {
            LoginService.login(member);
            System.out.println("로그인완료!!");

            return  "redirect:/";
        } catch (Exception e) {
            System.out.println("로그인 에러발생");
            e.printStackTrace(); //오류 출력(방법은 여러가지)
//            throw e; //최상위 클래스가 아니라면 무조건 던져주자
            model.addAttribute("error", "존재하지 않는 회원입니다!");
            return "login";
        }

    }
}
