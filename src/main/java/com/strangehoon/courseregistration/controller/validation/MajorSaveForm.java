package com.strangehoon.courseregistration.controller.validation;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MajorSaveForm {
    @NotBlank
    private String name;
}
