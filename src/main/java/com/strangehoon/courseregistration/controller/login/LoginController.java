package com.strangehoon.courseregistration.controller.login;

import com.strangehoon.courseregistration.domain.Manager;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.repository.ManagerRepository;
import com.strangehoon.courseregistration.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "index";
    }

    @PostMapping("/")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        Object object = loginService.login(form.getLoginId(), form.getPassword());
        if(object instanceof Manager) {
            log.info("bbbbbbbbbbbb");
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MANAGER, object);

            return "redirect:/managerHome";
        }
        else if (object instanceof  Student) {
            log.info("yyyyyyyyyyyyyyyyyyyyy");
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_STUDENT, object);

            return "redirect:/home";
        }
        else {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호 오류.");
            return "index";
        }

    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        //세션 삭제
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
