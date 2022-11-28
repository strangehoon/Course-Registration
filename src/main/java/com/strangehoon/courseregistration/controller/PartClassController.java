package com.strangehoon.courseregistration.controller;



import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.service.PartClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PartClassController {

    private final PartClassService partClassService;

    //분반 등록 폼
    @GetMapping(value = "/partClasses/new")
    public String createForm(Model model) {
        model.addAttribute("partClassForm", new PartClassForm());
        return "partClass/createPartClassForm";
    }

    //분반 등록
    @PostMapping(value = "/partClasses/new")
    public String create(PartClassForm partClassForm, Model model) {
        partClassService.createPartClass(new PartClassDto(partClassForm));

        model.addAttribute("message", "신규 분반이 등록되었습니다.");
        model.addAttribute("searchUrl", "/partClasses");

        return "message";
    }

    //분반 조회
    @GetMapping(value = "/partClasses")
    public String List(Model model) {
        List<PartClassDto> partClassDtoAll = partClassService.findPartClasses();
        List<PartClassForm> partClassForm = new ArrayList<>();
        for(PartClassDto partClassDto : partClassDtoAll) {
            partClassForm.add(new PartClassForm(partClassDto));
        }

        model.addAttribute("partClassForm", partClassForm);
        return "partClass/partClassList";
    }


    //분반 수정 폼
    @GetMapping(value = "/partClasses/{partClassId}/edit")
    public String updatePartClassForm(@PathVariable("partClassId") Long partClassId, Model model) {
        PartClassDto partClassDto = partClassService.findPartClassOne(partClassId);
        log.info("info log ={}", partClassId);
        PartClassForm partClassForm = new PartClassForm(partClassDto);

        model.addAttribute("partClassForm",partClassForm);
        return "partClass/updatePartClassForm";
    }

    //분반 수정
    @PostMapping(value = "/partClasses/{partClassId}/edit")
    public String updateItem(@ModelAttribute("form") PartClassForm partClassForm, Model model) {
        PartClassDto partClassDtoWithId = new PartClassDto(partClassForm, partClassForm.getId());

        partClassService.updatePartClass(partClassDtoWithId);
        model.addAttribute("message", "분반 정보를 수정했습니다.");
        model.addAttribute("searchUrl", "/partClasses");
        return "message";
    }




    /*테스트용 데이터*/
    @PostConstruct
    public void init() {
        partClassService.createPartClass(new PartClassDto("전자회로", 3, "월1화1수1", "P101", "전자전기공학부"));
        partClassService.createPartClass(new PartClassDto("기초데이터베이스", 4, "월3수3목3", "T501", "컴퓨터공학부"));
        partClassService.createPartClass(new PartClassDto("반도체공학2", 3, "월7목7금7", "P302", "전자전기공학부"));
        partClassService.createPartClass(new PartClassDto("신호와시스템", 3, "화2수2금2", "P201", "전자전기공학부"));
    }

}
