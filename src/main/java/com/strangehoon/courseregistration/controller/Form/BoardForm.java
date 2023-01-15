package com.strangehoon.courseregistration.controller.Form;

import com.strangehoon.courseregistration.dto.BoardDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BoardForm {

    @NotBlank
    private String author;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardForm(String author, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public BoardForm(BoardDto boardDto) {
        this.author = boardDto.getAuthor();
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.createdDate = boardDto.getCreatedDate();
        this.modifiedDate = boardDto.getModifiedDate();
    }

    public BoardForm() {
    }
}
