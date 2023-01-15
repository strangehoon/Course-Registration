package com.strangehoon.courseregistration.controller.Form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
public class MajorForm {

    private Long id;

    @NotBlank
    private String name;

    public MajorForm(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
