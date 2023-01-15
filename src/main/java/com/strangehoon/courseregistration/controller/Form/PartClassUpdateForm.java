package com.strangehoon.courseregistration.controller.Form;

import com.strangehoon.courseregistration.dto.PartClassDto;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PartClassUpdateForm {

    @NotNull
    private Long id;

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

    public PartClassUpdateForm() {

    }

    public PartClassUpdateForm(PartClassDto partClassDto) {
        this.id = partClassDto.getId();
        this.name = partClassDto.getName();
        this.classNum = partClassDto.getClassNum();
        this.capacity = partClassDto.getCapacity();
        this.remainNum = partClassDto.getRemainNum();
        this.professorName = partClassDto.getProfessorName();
        this.credit = partClassDto.getCredit();
        this.grade = partClassDto.getGrade();
        this.dayTime = partClassDto.getDayTime();
        this.classroom = partClassDto.getClassroom();
        this.majorName = partClassDto.getMajorName();
    }

}
