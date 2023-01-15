package com.strangehoon.courseregistration.controller;


import com.strangehoon.courseregistration.controller.Form.BoardForm;
import com.strangehoon.courseregistration.dto.BoardDto;
import com.strangehoon.courseregistration.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //___________________________________________________관리자 영역_________________________________________________________

    @GetMapping("/managerBoard/list")
    public String listByManager(Model model, @PageableDefault Pageable pageable) {

        // 단순 조회 + 페이징 처리라 편의상 Dto를 뷰에 전달
        Page<BoardDto> boardList = boardService.findList(pageable);

        model.addAttribute("boardForm", new BoardForm());
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageNumber", boardList.getNumber());
        return "board/manager/list";
    }

    // 게시물 등록 폼
    @GetMapping("/managerBoard/new")
    public String postForm(Model model) {

        model.addAttribute("boardForm", new BoardForm());
        return "board/manager/postForm";
    }

    // 게시물 등록
    @PostMapping("/managerBoard/new")
    public String post(@Validated @ModelAttribute BoardForm boardForm, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "board/manager/postForm";
        }

        BoardDto boardDto = new BoardDto(boardForm);
        boardService.savePost(boardDto);
        model.addAttribute("message", "공지사항이 등록되었습니다.");
        model.addAttribute("searchUrl", "/managerBoard/list?page=1");

        return "message";
    }

    // 게시물 조회 및 수정 폼
    @GetMapping("/managerBoard")
    public String updateForm(@RequestParam("id") Long id, @RequestParam("order") Long order, Model model) {

        BoardDto boardDto = boardService.findPost(id);
        BoardForm boardForm = new BoardForm(boardDto);

        model.addAttribute("id", id);
        model.addAttribute("order", order);
        model.addAttribute("boardForm", boardForm);

        return "board/manager/updateForm";
    }

    // 게시물 수정
    @PostMapping("/managerBoard")
    public String update(@Validated @ModelAttribute BoardForm boardForm, BindingResult bindingResult, @RequestParam("id") Long id, @RequestParam("order") Long order, Model model) {


        if(bindingResult.hasErrors()) {

            model.addAttribute("id", id);
            model.addAttribute("order", order);
            model.addAttribute("boardForm", boardForm);
            return "board/manager/updateForm";
        }
        BoardDto boardDto = new BoardDto(boardForm);
        boardDto.setId(id);
        boardService.update(boardDto);
        model.addAttribute("message", "공지사항이 수정되었습니다.");
        //11번 게시물에서 수정하면 2번째 페이지로 가야함
        model.addAttribute("searchUrl", "/managerBoard/list?page="+ (order/11 +1));
        return "message";
    }

    // 게시물 삭제
    @GetMapping("/managerBoard/delete")
    public String delete(@RequestParam("id") Long id, @RequestParam("order") Long order, Model model) {

        boardService.delete(id);
        model.addAttribute("message", "공지사항이 삭제되었습니다.");
        //11번째 게시물에서 삭제하면 2번째 페이지로 가야함
        model.addAttribute("searchUrl", "/managerBoard/list?page="+(order/11 + 1));
        return "message";
    }

    //___________________________________________________학생 영역_________________________________________________________

    // 게시물 리스트 조회
    @GetMapping("/board/list")
    public String listByStudent(Model model, @PageableDefault Pageable pageable) {

        // 단순 조회 + 페이징 처리라 편의상 Dto를 뷰에 전달
        Page<BoardDto> boardList = boardService.findList(pageable);
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageNumber", boardList.getNumber());
        return "board/list";
    }

    // 게시물 조회
    @GetMapping("/board")
    public String content(@RequestParam("id")Long id, @RequestParam("count")Long count,  Model model) {

        BoardDto boardDto = boardService.findPost(id);
        BoardForm boardForm = new BoardForm(boardDto);
        model.addAttribute("id", id);
        model.addAttribute("count", count);
        model.addAttribute("boardForm", boardForm);
        return "board/content";
    }
}
