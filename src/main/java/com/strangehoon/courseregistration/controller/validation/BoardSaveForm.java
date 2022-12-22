package com.strangehoon.courseregistration.controller.validation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardSaveForm {

    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
