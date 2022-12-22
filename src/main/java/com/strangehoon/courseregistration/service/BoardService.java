package com.strangehoon.courseregistration.service;

import com.strangehoon.courseregistration.controller.validation.BoardUpdateForm;
import com.strangehoon.courseregistration.domain.Board;
import com.strangehoon.courseregistration.dto.BoardDto;
import com.strangehoon.courseregistration.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void savePost(BoardDto boardDto) {
        Board board = new Board(boardDto);
        boardRepository.save(board);
    }

    public List<BoardDto> findList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDto boardDto = new BoardDto(board);
            boardDtoList.add(boardDto);
        }
        return boardDtoList;

    }

    public BoardDto findPost(Long id) {
        Board board =  boardRepository.findById(id).get();
        BoardDto boardDto = new BoardDto(board);
        return boardDto;
    }

    @Transactional
    public void update(BoardDto boardDto) {
        Board foundBoard = boardRepository.findById(boardDto.getId()).get();
        foundBoard.update(boardDto);
    }

    @Transactional
    public void delete(Long id) {
        Board foundBoard = boardRepository.findById(id).get();
        System.out.println("BoardService.delete" + foundBoard);
        boardRepository.delete(foundBoard);
    }
}
