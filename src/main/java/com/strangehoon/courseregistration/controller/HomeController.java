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

    //___________________________________________________관리자 영역_________________________________________________________
    @GetMapping("/managerHome")
    public String homeLoginByManager(HttpServletRequest request, Model model) {

        //세션이 없으면 home
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }
        Manager loginManager = (Manager) session.getAttribute(SessionConst.LOGIN_MANAGER);

        //세션에 회원 데이터가 없으면 home
        if (loginManager == null) {
            return "index";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("manager", loginManager);
        return "managerHome";
    }

    //___________________________________________________학생 영역_________________________________________________________

    @GetMapping("/home")
    public String homeLoginByStudent(HttpServletRequest request, Model model) {

        //세션이 없으면 home
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }

        Student loginStudent = (Student) session.getAttribute(SessionConst.LOGIN_STUDENT);
        //세션에 회원 데이터가 없으면 home
        if (loginStudent == null) {
            return "index";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("student", loginStudent);
        return "home";
    }
}

