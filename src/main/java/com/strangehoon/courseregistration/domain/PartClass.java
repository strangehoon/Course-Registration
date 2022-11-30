package com.strangehoon.courseregistration.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartClass {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_class_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    private String classNum;

    private String name;

    private int grade;

    private int credit;

    private int capacity;

    private int remainNum;

    private String professorName;

    private String dayTime;

    private String classroom;

    @OneToMany(mappedBy = "partClass")
    private List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "partClass")
    private List<Pocket> pockets = new ArrayList<>();




    //==연관관계 메서드==//
    public void putMajor(Major major) {
        if (this.major != null) {
            this.major.getPartClasses().remove(this);
        }
        this.major = major;
        major.getPartClasses().add(this);
    }

    //==생성 메서드==//
    public static PartClass createPartClass(Major major, String classNum, String name, int grade, int credit, int capacity, int remainNum, String professorName, String dayTime, String classroom) {
        PartClass partClass = PartClass.builder()
                .classNum(classNum)
                .name(name)
                .grade(grade)
                .credit(credit)
                .capacity(capacity)
                .remainNum(remainNum)
                .professorName(professorName)
                .dayTime(dayTime)
                .classroom(classroom)
                .build();
        
        partClass.putMajor(major);
        return partClass;
    }
    @Builder
    private PartClass(String classNum, String name, int grade, int credit, int capacity, int remainNum, String professorName, String dayTime, String classroom) {
        this.classNum = classNum;
        this.name = name;
        this.grade = grade;
        this.credit = credit;
        this.capacity = capacity;
        this.remainNum = remainNum;
        this.professorName = professorName;
        this.dayTime = dayTime;
        this.classroom = classroom;
    }

    //==비즈니스 로직==//
    public void updatePartClass(String majorName, String classNum, String name, int grade, int credit, int capacity, int remainNum, String professorName, String dayTime,String classroom) {
        this.classNum = classNum;
        this.name = name;
        this.credit = credit;
        this.grade = grade;
        this.capacity = capacity;
        this.remainNum = remainNum;
        this.professorName = professorName;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.major.updateMajorName(majorName);
    }

    public void updateId() {
        this.id -=1;
    }
}
