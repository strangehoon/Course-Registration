package com.strangehoon.courseregistration.controller.Form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
public class PartClassSaveForm {

    @NotBlank
    private String name;

    @NotBlank
    private String classNum;

    @Range(min=10, max=500)
    private int capacity;

    @Range(min=10, max=500)
    private int remainNum;

    @NotBlank
    private String professorName;

    @Range(min=2, max=4)
    private int credit;

    @Range(min=1, max=4)
    private int grade;

    @NotBlank
    private String dayTime;

    @NotBlank
    private String classroom;

    @NotBlank
    private String majorName;
}