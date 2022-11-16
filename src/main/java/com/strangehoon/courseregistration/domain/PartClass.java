package com.strangehoon.courseregistration.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartClass {

    @Id @GeneratedValue
    @Column(name = "part_class_id")
    private Long id;

    @OneToMany(mappedBy = "partClass")
    private List<Register> registers;

    @OneToMany(mappedBy = "partClass")
    private List<Pocket> pockets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    private ClassStatus status;

    private String name;

    private int credit;

    private String dayTime;

    private String classroom;

    //==연관관계 메서드==//
    public void setCourse(Course course) {
        this.course = course;
        course.getPartClasses().add(this);
    }
}
