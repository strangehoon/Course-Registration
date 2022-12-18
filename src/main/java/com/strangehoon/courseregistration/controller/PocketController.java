package com.strangehoon.courseregistration.controller;

import com.strangehoon.courseregistration.controller.login.SessionConst;
import com.strangehoon.courseregistration.domain.Pocket;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.dto.PocketClassDto;
import com.strangehoon.courseregistration.dto.QPartClassDto;
import com.strangehoon.courseregistration.repository.PartClassSearch;
import com.strangehoon.courseregistration.repository.PocketRepository;

import com.strangehoon.courseregistration.repository.StudentRepository;
import com.strangehoon.courseregistration.service.PocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PocketController {

    private final PocketService pocketService;
    private final StudentRepository studentRepository;

    //장바구니 등록
    @PostMapping(value = "/pocketPartClasses/new") @ResponseBody
    public boolean create(@ModelAttribute PocketClassDto pocketClassDto, Model model) {
        System.out.println("pocketClassDto.partClassId = " + pocketClassDto.getPartClassId());
        System.out.println("pocketClassDto.studentId = " + pocketClassDto.getStudentId());
        if(pocketService.checkPocket(pocketClassDto)) {
            pocketService.savePocket(pocketClassDto);
//            model.addAttribute("message", "선택하신 과목들을 담았습니다.");
//            model.addAttribute("searchUrl", "/partClasses");
            return true;
        }
        else {
//            model.addAttribute("message", "이미 장바구니에 있습니다.");
//            model.addAttribute("searchUrl", "/partClasses");
            return false;
        }
    }

//    @GetMapping(value = "/pocketPartClass")
//    public String pocketClass(HttpServletRequest request, @ModelAttribute("partClassForm") PartClassDto partClassDto, Model model) {
//        //세션이 없으면 home
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return "index";
//        }
//
//        Student loginStudent = (Student) session.getAttribute(SessionConst.LOGIN_STUDENT);
//
//        //세션에 회원 데이터가 없으면 home
//        if (loginStudent == null) {
//            return "index";
//        }
//
//        //로그인한 사용자 정보
//        Student student = studentRepository.findById(loginStudent.getId()).get();
//
//        pocketService.savePocketClass(student, partClassDto);
//
//        List<Pocket> pocketList= pocketService.findAllPocket();
//        model.addAttribute("pocketList", pocketList);
//        return "pocket/pocketList";
//    }
}
