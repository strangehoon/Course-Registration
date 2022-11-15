package com.strangehoon.courseregistration.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Major {

    @Id @GeneratedValue
    @Column(name = "major_id")
    private Long id;

    @OneToMany(mappedBy = "major")
    private List<Course> course;

    @OneToMany(mappedBy = "major")
    private List<Student> student;

    private String name;

}
