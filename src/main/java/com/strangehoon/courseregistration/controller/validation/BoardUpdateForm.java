package com.strangehoon.courseregistration.controller.validation;

import com.strangehoon.courseregistration.domain.Board;
import com.strangehoon.courseregistration.dto.BoardDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardUpdateForm {

    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardUpdateForm(BoardDto boardDto) {
        this.author = boardDto.getAuthor();
        this.title = boardDto.getTitle();
        this.content= boardDto.getContent();
        this.createdDate = boardDto.getCreatedDate();
        this.modifiedDate = boardDto.getModifiedDate();
    }

    public BoardUpdateForm() {
    }
}
