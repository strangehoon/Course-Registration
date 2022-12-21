package com.strangehoon.courseregistration.controller.validation;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MajorUpdateForm {
    @NotBlank
    private String name;
}
