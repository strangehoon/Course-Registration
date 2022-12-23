package com.strangehoon.courseregistration.controller;


import com.strangehoon.courseregistration.controller.validation.BoardSaveForm;
import com.strangehoon.courseregistration.controller.validation.BoardUpdateForm;
import com.strangehoon.courseregistration.dto.BoardDto;
import com.strangehoon.courseregistration.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    //___________________________________________________관리자 영역_________________________________________________________
    @GetMapping("/managerBoard/list")
    public String listByManager(Model model, @PageableDefault Pageable pageable) {
        Page<BoardDto> boardList = boardService.findList(pageable);
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageNumber", boardList.getNumber());
        return "board/manager/list";
    }

    @GetMapping("/managerBoard/new")
    public String create(Model model) {

        return "board/manager/post";
    }

    @PostMapping("/managerBoard/new")
    public String save(@ModelAttribute BoardSaveForm boardSaveForm, Model model) {

        BoardDto boardDto = new BoardDto(boardSaveForm);


        boardService.savePost(boardDto);
        model.addAttribute("message", "공지사항이 등록되었습니다.");
        model.addAttribute("searchUrl", "/managerBoard/list?page=\"+ (id/11 +1))");
        return "message";
    }

    @GetMapping("/managerBoard/{id}")
    public String update(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.findPost(id);
        BoardUpdateForm boardUpdateForm = new BoardUpdateForm(boardDto);
        model.addAttribute("id", id);
        model.addAttribute("boardUpdateForm", boardUpdateForm);
        return "board/manager/update";
    }

    @PostMapping("/managerBoard/{id}")
    public String update(@PathVariable Long id, @ModelAttribute BoardUpdateForm boardUpdateform, Model model) {

        BoardDto boardDto = new BoardDto(id, boardUpdateform);

        boardService.update(boardDto);

        model.addAttribute("message", "공지사항이 수정되었습니다.");
        model.addAttribute("searchUrl", "/managerBoard/list?page="+ (id/11 +1));    //11번 게시물에서 수정하면 2번째 페이지로 가야함
        return "message";
    }

    @GetMapping("/managerBoard/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        boardService.delete(id);
        model.addAttribute("message", "공지사항이 삭제되었습니다.");
        model.addAttribute("searchUrl", "/managerBoard/list?page="+(id/11 +1));     //11번째 게시물에서 삭제하면 2번째 페이지로 가야함
        return "message";

    }

    //___________________________________________________학생 영역_________________________________________________________
    @GetMapping("/board/list")
    public String list(Model model, @PageableDefault Pageable pageable) {
        Page<BoardDto> boardList = boardService.findList(pageable);
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageNumber", boardList.getNumber());
        return "board/list";
    }


    @GetMapping("/board/{id}")
    public String content(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.findPost(id);
        BoardUpdateForm boardUpdateForm = new BoardUpdateForm(boardDto);
        model.addAttribute("id", id);
        model.addAttribute("boardUpdateForm", boardUpdateForm);
        return "board/content";
    }


}
