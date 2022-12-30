package com.strangehoon.courseregistration.controller;


import com.strangehoon.courseregistration.controller.login.SessionConst;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.RegisterDto;
import com.strangehoon.courseregistration.repository.RegisterRepository;
import com.strangehoon.courseregistration.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    //수강신청 등록
    @PostMapping(value = "/register/new") @ResponseBody
    public Boolean register(@RequestParam Long studentId, Model model) {

        // 수강신청 시 기존에 신청했던 내영은 먼저 삭제를 해야함, 안그러면 중복 이슈 발생
        registerService.deleteAll(studentId);
        
        Boolean flag = registerService.registerPocket(studentId);

        return flag;
    }

    // 수강신청 시간표 조회
    @GetMapping(value = "register/list")
    public String list(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Object login = session.getAttribute(SessionConst.LOGIN_STUDENT);
        Student student = (Student)login;

        List<RegisterDto> registerDto = registerService.findRegisterDto(student.getId());

        Map<String, String> timeTable =   registerService.makeTimeTable(registerDto);


        model.addAttribute("timeTable", timeTable);
        return "register/registerList";
    }


}
