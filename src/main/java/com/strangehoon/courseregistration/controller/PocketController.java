package com.strangehoon.courseregistration.controller;

import com.strangehoon.courseregistration.controller.login.SessionConst;
import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.domain.Pocket;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.dto.PocketClassDto;
import com.strangehoon.courseregistration.dto.QPartClassDto;
import com.strangehoon.courseregistration.repository.PartClassSearch;
import com.strangehoon.courseregistration.repository.PocketRepository;

import com.strangehoon.courseregistration.repository.StudentRepository;
import com.strangehoon.courseregistration.service.PartClassService;
import com.strangehoon.courseregistration.service.PocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final PartClassService partClassService;

    //장바구니 등록
    @PostMapping(value = "/pocketList/new") @ResponseBody
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

    //장바구니 조회
    @GetMapping(value = "/pocketList")
    public String ListByUsedStudent(@PageableDefault Pageable pageable, Model model) {


//        List<Pocket> foundPocket = pocketService.findPocket(1L);
        Page<PartClassDto> pocketList = partClassService.pocketClassList(1L, pageable);

        model.addAttribute("pocketList", pocketList);
        model.addAttribute("pageNumber", pocketList.getNumber());
        return "pocket/pocketList";
    }

    //장바구니 내역 삭제
    @GetMapping(value = "/pocketList/{partClassId}/delete")
    public String deletePartClass(@PathVariable("partClassId") Long partClassId, Model model) {
        pocketService.deletePocketClass(partClassId);

        model.addAttribute("message", "담아두기 내역을 삭제했습니다.");
        model.addAttribute("searchUrl", "/pocketList");
        return "message";
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
