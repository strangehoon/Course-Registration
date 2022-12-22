package com.strangehoon.courseregistration.dto;

import com.strangehoon.courseregistration.controller.validation.BoardSaveForm;
import com.strangehoon.courseregistration.controller.validation.BoardUpdateForm;
import com.strangehoon.courseregistration.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    // saveForm을 Dto로 변환
    public BoardDto (BoardSaveForm form) {
        this.author= form.getAuthor();
        this.title=form.getTitle();
        this.content=form.getContent();
        this.createdDate=form.getCreatedDate();
        this.modifiedDate=form.getModifiedDate();
    }

    // updateForm을 Dto로 변환
    public BoardDto (Long id, BoardUpdateForm form) {
        this.id = id;
        this.author = form.getAuthor();
        this.title = form.getTitle();
        this.content = form.getContent();
        this.createdDate = form.getCreatedDate();
        this.modifiedDate = form.getModifiedDate();
    }

    // Board를 BoardDto로 변환
    public BoardDto(Board board) {
        this.id= board.getId();
        this.author= board.getAuthor();
        this.title= board.getTitle();
        this.content=board.getContent();
        this.createdDate=board.getCreatedDate();
        this.modifiedDate=board.getModifiedDate();
    }

    public BoardDto(Long id, String author, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
