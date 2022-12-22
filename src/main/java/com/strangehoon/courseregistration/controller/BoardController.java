package com.strangehoon.courseregistration.controller;


import com.strangehoon.courseregistration.controller.validation.BoardSaveForm;
import com.strangehoon.courseregistration.controller.validation.BoardUpdateForm;
import com.strangehoon.courseregistration.dto.BoardDto;
import com.strangehoon.courseregistration.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String listByManager(Model model) {
        List<BoardDto> boardList = boardService.findList();
        model.addAttribute("boardList", boardList);
        return "board/manager/list";
    }

    @GetMapping("/managerBoard/new")
    public String create(Model model) {

        System.out.println("BoardController.qwee");
        return "board/manager/post";
    }

    @PostMapping("/managerBoard/new")
    public String save(@ModelAttribute BoardSaveForm boardSaveForm, Model model) {

        System.out.println("BoardController.save" + boardSaveForm.getAuthor());
        BoardDto boardDto = new BoardDto(boardSaveForm);


        boardService.savePost(boardDto);
        model.addAttribute("message", "공지사항이 등록되었습니다.");
        model.addAttribute("searchUrl", "/managerBoard/list");
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
        System.out.println("BoardController.xxx" + id);
        System.out.println("BoardController.xxx" + boardUpdateform);

        BoardDto boardDto = new BoardDto(id, boardUpdateform);
        System.out.println("BoardController.update" + id);
        System.out.println("BoardController.update" + boardUpdateform.getContent());
        System.out.println("BoardController.update" + boardDto.getContent());

        boardService.update(boardDto);

        model.addAttribute("message", "공지사항이 수정되었습니다.");
        model.addAttribute("searchUrl", "/managerBoard/list");
        return "message";
    }

    @GetMapping("/managerBoard/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        boardService.delete(id);
        model.addAttribute("message", "공지사항이 삭제되었습니다.");
        model.addAttribute("searchUrl", "/managerBoard/list");
        return "message";

    }

    //___________________________________________________학생 영역_________________________________________________________
    @GetMapping("/board/list")
    public String list(Model model) {
        List<BoardDto> boardList = boardService.findList();
        model.addAttribute("boardList", boardList);
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
