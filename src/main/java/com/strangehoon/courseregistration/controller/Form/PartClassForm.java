package com.strangehoon.courseregistration.controller.Form;


import com.strangehoon.courseregistration.dto.PartClassDto;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PartClassForm {

    private Long id;
    private String name;
    private String classNum;
    private int capacity;
    private int remainNum;
    private String professorName;
    private int credit;
    private int grade;
    private String dayTime;
    private String classroom;
    private String majorName;

    public PartClassForm() {
    }

    public PartClassForm(String classNum, String name, int grade, int credit, int capacity, int remainNum, String professorName, String dayTime, String classroom, String majorName) {

        this.classNum = classNum;
        this.capacity = capacity;
        this.remainNum = remainNum;
        this.professorName = professorName;
        this.name = name;
        this.grade =grade;
        this.credit = credit;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.majorName = majorName;
    }

    public PartClassForm(PartClassDto partClassDto) {

        this.classNum = partClassDto.getClassNum();
        this.capacity = partClassDto.getCapacity();
        this.remainNum = partClassDto.getRemainNum();
        this.professorName = partClassDto.getProfessorName();
        this.id = partClassDto.getId();
        this.name = partClassDto.getName();
        this.grade = partClassDto.getGrade();
        this.credit = partClassDto.getCredit();
        this.dayTime = partClassDto.getDayTime();
        this.classroom = partClassDto.getClassroom();
        this.majorName = partClassDto.getMajorName();
    }
}
