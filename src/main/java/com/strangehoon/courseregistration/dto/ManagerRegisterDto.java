package com.strangehoon.courseregistration.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ManagerRegisterDto {

    private Long id;
    private String classNum;
    private String name;
    private int grade;
    private int credit;
    private int capacity;
    private String professorName;
    private String dayTime;
    private String classroom;
    private String majorName;
    private String studentId;
    private LocalDateTime registerDate;

    @QueryProjection
    public ManagerRegisterDto(Long id, String classNum, String name, int grade, int credit, int capacity, String professorName, String dayTime, String classroom, String studentId, LocalDateTime registerDate, String majorName) {
        this.id = id;
        this.classNum = classNum;
        this.name = name;
        this.grade = grade;
        this.credit = credit;
        this.capacity = capacity;
        this.professorName = professorName;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.majorName = majorName;
        this.studentId = studentId;
        this.registerDate = registerDate;
    }
}
