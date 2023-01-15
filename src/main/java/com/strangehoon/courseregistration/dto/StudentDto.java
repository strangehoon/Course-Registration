package com.strangehoon.courseregistration.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class StudentDto {

    private String loginId;

    private String password;

    private String majorName;

    private int schoolYear;

    private String name;

    public StudentDto(String loginId, String password, String majorName, int schoolYear, String name) {
        this.loginId = loginId;
        this.password = password;
        this.majorName = majorName;
        this.schoolYear = schoolYear;
        this.name = name;
    }
}
