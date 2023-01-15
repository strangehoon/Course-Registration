package com.strangehoon.courseregistration.controller.Form;

import lombok.Data;

@Data
public class PocketClassForm {

    private Long partClassId;

    private Long studentId;

    public PocketClassForm(Long studentId) {
        this.studentId = studentId;
    }
}
