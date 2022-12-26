package com.strangehoon.courseregistration.controller;


import com.strangehoon.courseregistration.repository.RegisterRepository;
import com.strangehoon.courseregistration.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    //수강신청 등록
    @PostMapping(value = "/register/new")
    public String register(@RequestParam Long studentId, Model model) {

        registerService.registerPocket(studentId);

        model.addAttribute("message", "수강신청을 완료했습니다.");
        model.addAttribute("searchUrl", "/pocketList");
        return "message";

    }
}
