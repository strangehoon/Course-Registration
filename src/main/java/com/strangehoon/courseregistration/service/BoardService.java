package com.strangehoon.courseregistration.service;

import com.strangehoon.courseregistration.domain.Board;
import com.strangehoon.courseregistration.dto.BoardDto;
import com.strangehoon.courseregistration.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<BoardDto> findList(Pageable pageable) {
        int initpage = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); //스프링 데이터 jpa에서는 페이지가 0부터 시작 따라서 이행을 넣음
        pageable = PageRequest.of(initpage, 10, Sort.by(Sort.Direction.ASC,"id")); // <- Sort 추가
        Page<BoardDto> page = boardRepository.findBoardList(pageable);

        return page;
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
