package com.strangehoon.courseregistration.dto;

import com.strangehoon.courseregistration.controller.PartClassForm;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PartClassDto {
    private Long id;
    private String name;
    private int credit;
    private String dayTime;
    private String classroom;

    private String majorName;

    public PartClassDto(PartClassForm partClassForm) {
        this.name = partClassForm.getName();
        this.credit = partClassForm.getCredit();
        this.dayTime = partClassForm.getDayTime();
        this.classroom = partClassForm.getClassroom();
        this.majorName = partClassForm.getMajorName();
    }

    public PartClassDto(PartClassForm partClassForm, Long id) {
        this.id = id;
        this.name = partClassForm.getName();
        this.credit = partClassForm.getCredit();
        this.dayTime = partClassForm.getDayTime();
        this.classroom = partClassForm.getClassroom();
        this.majorName = partClassForm.getMajorName();
    }

    public PartClassDto(Long id, String name, int credit, String dayTime, String classroom, String majorName) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.majorName = majorName;
    }

    public PartClassDto(String name, int credit, String dayTime, String classroom, String majorName) {
        this.name = name;
        this.credit = credit;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.majorName = majorName;
    }

}
