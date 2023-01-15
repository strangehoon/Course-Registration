package com.strangehoon.courseregistration.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.strangehoon.courseregistration.controller.Form.PartClassForm;
import com.strangehoon.courseregistration.controller.Form.PartClassSaveForm;
import com.strangehoon.courseregistration.controller.Form.PartClassUpdateForm;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PartClassDto {
    private Long id;
    private String classNum;
    private String name;
    private int grade;
    private int credit;
    private int remainNum;
    private int capacity;
    private String professorName;
    private String dayTime;
    private String classroom;

    private String majorName;


    public PartClassDto(PartClassForm partClassForm) {
        this.classNum = partClassForm.getClassNum();
        this.capacity = partClassForm.getCapacity();
        this.remainNum = partClassForm.getRemainNum();
        this.professorName = partClassForm.getProfessorName();
        this.name = partClassForm.getName();
        this.grade = partClassForm.getGrade();
        this.credit = partClassForm.getCredit();
        this.dayTime = partClassForm.getDayTime();
        this.classroom = partClassForm.getClassroom();
        this.majorName = partClassForm.getMajorName();
    }

    public PartClassDto(PartClassForm partClassForm, Long id) {
        this.classNum = partClassForm.getClassNum();
        this.capacity = partClassForm.getCapacity();
        this.remainNum = partClassForm.getRemainNum();
        this.professorName = partClassForm.getProfessorName();
        this.id = id;
        this.name = partClassForm.getName();
        this.grade = partClassForm.getGrade();
        this.credit = partClassForm.getCredit();
        this.dayTime = partClassForm.getDayTime();
        this.classroom = partClassForm.getClassroom();
        this.majorName = partClassForm.getMajorName();
    }

    @QueryProjection
    public PartClassDto(Long id, String classNum, String name, int grade, int credit, int capacity, int remainNum,String professorName, String dayTime, String classroom, String majorName) {
        this.id = id;
        this.classNum = classNum;
        this.capacity = capacity;
        this.remainNum = remainNum;
        this.professorName = professorName;
        this.name = name;
        this.grade = grade;
        this.credit = credit;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.majorName = majorName;
    }

    public PartClassDto(String classNum, String name, int grade, int credit, int capacity, int remainNum, String professorName, String dayTime, String classroom, String majorName) {
        this.classNum = classNum;
        this.capacity = capacity;
        this.remainNum = remainNum;
        this.professorName = professorName;
        this.name = name;
        this.grade = grade;
        this.credit = credit;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.majorName = majorName;
    }

    // 분반 등록용 폼을 DTO로 변환
    public PartClassDto(PartClassSaveForm form) {
        this.classNum = form.getClassNum();
        this.capacity = form.getCapacity();
        this.remainNum = form.getRemainNum();
        this.professorName = form.getProfessorName();
        this.name = form.getName();
        this.grade = form.getGrade();
        this.credit = form.getCredit();
        this.dayTime = form.getDayTime();
        this.classroom = form.getClassroom();
        this.majorName = form.getMajorName();
    }

    //분반 수정용 폼을 DTO로 변환
    public PartClassDto(PartClassUpdateForm form) {
        this.id = form.getId();
        this.classNum = form.getClassNum();
        this.capacity = form.getCapacity();
        this.remainNum = form.getRemainNum();
        this.professorName = form.getProfessorName();
        this.name = form.getName();
        this.grade = form.getGrade();
        this.credit = form.getCredit();
        this.dayTime = form.getDayTime();
        this.classroom = form.getClassroom();
        this.majorName = form.getMajorName();
    }

}
