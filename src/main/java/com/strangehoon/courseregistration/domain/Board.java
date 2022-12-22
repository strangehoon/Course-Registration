package com.strangehoon.courseregistration.domain;

import com.strangehoon.courseregistration.dto.BoardDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String title;

    private String content;

    //BoardDto를 Board로 변환
    public Board(BoardDto boardDto){
        this.id = boardDto.getId();
        this.author=boardDto.getAuthor();
        this.title=boardDto.getTitle();
        this.content=boardDto.getContent();
    }

    public void update(BoardDto boardDto) {
        this.id = boardDto.getId();
        this.author = boardDto.getAuthor();
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }

}
