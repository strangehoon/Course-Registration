package com.strangehoon.courseregistration.controller.Form;

import lombok.Data;

@Data
public class RegisterForm {

    private Long partClassId;

    private Long studentId;

    private String dayTime;

    private String name;

    private int credit;

    public RegisterForm(Long partClassId, Long studentId, String dayTime, String name, int credit) {
        this.partClassId = partClassId;
        this.studentId = studentId;
        this.dayTime = dayTime;
        this.name = name;
        this.credit = credit;
    }

    public RegisterForm(Long studentId) {
        this.studentId = studentId;
    }
}
