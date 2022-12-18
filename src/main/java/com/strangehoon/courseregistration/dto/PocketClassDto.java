package com.strangehoon.courseregistration.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PocketClassDto {


    private Long partClassId;

    private Long studentId;

    public PocketClassDto() {
    }

    public PocketClassDto(Long studentId) {
        this.studentId = studentId;
    }

    public PocketClassDto(Long partClassId, Long studentId) {
        this.partClassId = partClassId;
        this.studentId = studentId;
    }



    public Long getPartClassId() {
        return partClassId;
    }

    public void setPartClassId(Long partClassId) {
        this.partClassId = partClassId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
