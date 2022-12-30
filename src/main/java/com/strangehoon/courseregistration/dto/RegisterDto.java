package com.strangehoon.courseregistration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    private Long partClassId;

    private Long studentId;

    private String dayTime;

    private String name;

    private int credit;

    public RegisterDto(String dayTime, String name, int credit) {
        this.dayTime = dayTime;
        this.name = name;
        this.credit = credit;
    }

    public RegisterDto() {
    }

    public RegisterDto(Long partClassId, Long studentId) {
        this.partClassId = partClassId;
        this.studentId = studentId;
    }

    public RegisterDto(Long studentId) {
        this.studentId = studentId;
    }
}
