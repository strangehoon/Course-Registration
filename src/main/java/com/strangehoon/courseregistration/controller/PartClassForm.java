package com.strangehoon.courseregistration.controller;


import com.strangehoon.courseregistration.dto.PartClassDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PartClassForm {
    private Long id;
    private String name;
    private int credit;
    private String dayTime;
    private String classroom;

    private String majorName;

    public PartClassForm() {
    }

    public PartClassForm(String name, int credit, String dayTime, String classroom, String majorName) {
        this.name = name;
        this.credit = credit;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.majorName = majorName;
    }

    public PartClassForm(PartClassDto partClassDto) {
        this.id = partClassDto.getId();
        this.name = partClassDto.getName();
        this.credit = partClassDto.getCredit();
        this.dayTime = partClassDto.getDayTime();
        this.classroom = partClassDto.getClassroom();
        this.majorName = partClassDto.getMajorName();
    }
}
