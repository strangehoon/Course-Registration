package com.strangehoon.courseregistration.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    private String loginId;

    private String password;

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


    //==연관관계 메서드==//
    public void putMajor(Major major) {
        if(this.major != null) {
            this.major.getStudents().remove(this);
        }
        this.major = major;
        major.getStudents().add(this);
    }

    //==생성 메서드==//
    public static Student createStudent(Major major, String loginId, String password, int schoolYear, String name, String phoneNumber) {
        Student student = Student.builder()
                .loginId(loginId)
                .password(password)
                .schoolYear(schoolYear)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
        student.putMajor(major);
        return student;
    }

    @Builder
    private Student(String loginId, String password, int schoolYear, String name, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.schoolYear = schoolYear;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
