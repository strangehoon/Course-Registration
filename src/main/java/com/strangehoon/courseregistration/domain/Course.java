package com.strangehoon.courseregistration.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id @GeneratedValue
    @Column(name = "course_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @OneToMany(mappedBy = "course")
    private List<PartClass> partClasses;

    //==연관관계 메서드==//
    public void setMajor(Major major) {
        this.major = major;
        major.getCourses().add(this);
    }
}
