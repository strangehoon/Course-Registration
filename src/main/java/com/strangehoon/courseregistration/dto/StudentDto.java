package com.strangehoon.courseregistration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentDto {

    private String loginId;

    private String password;

    private String majorName;

    private int schoolYear;

    private String name;

    private String phoneNumber;

    private double grade;

    public StudentDto(String loginId, String password, String majorName, int schoolYear, String name, String phoneNumber, double grade) {
        this.loginId = loginId;
        this.password = password;
        this.majorName = majorName;
        this.schoolYear = schoolYear;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.grade = grade;
    }
}
