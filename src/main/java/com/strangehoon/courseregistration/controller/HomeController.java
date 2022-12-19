package com.strangehoon.courseregistration.controller;

import com.strangehoon.courseregistration.controller.login.SessionConst;
import com.strangehoon.courseregistration.domain.Manager;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public String homeLogin(HttpServletRequest request, Model model) {

        //세션이 없으면 home
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "real";
        }

        Student loginStudent = (Student) session.getAttribute(SessionConst.LOGIN_STUDENT);

        //세션에 회원 데이터가 없으면 home
        if (loginStudent == null) {
            return "real";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("student", loginStudent);
        return "home";
    }

    @GetMapping("/managerHome")
    public String managerHomeLogin(HttpServletRequest request, Model model) {

        //세션이 없으면 home
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "real";
        }

        Manager loginManager = (Manager) session.getAttribute(SessionConst.LOGIN_MANAGER);

        //세션에 회원 데이터가 없으면 home
        if (loginManager == null) {
            return "real";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("manager", loginManager);
        return "managerHome";
    }
}

