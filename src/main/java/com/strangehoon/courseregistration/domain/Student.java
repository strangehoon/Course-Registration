package com.strangehoon.courseregistration.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @OneToMany(mappedBy = "student")
    private List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Pocket> pockets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    private int schoolYear;

    private String name;

    private String phoneNumber;

    private double grade;

    //==연관관계 메서드==//
    public void setMajor(Major major) {
        this.major = major;
        major.getStudents().add(this);
    }

}
