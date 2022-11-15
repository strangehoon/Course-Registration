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
    @Column(name = "partclass_id")
    private Long id;

    @OneToMany(mappedBy = "partclass")
    private List<Register> register;

    @OneToMany(mappedBy = "partclass")
    private List<Pocket> pocket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    private ClassStatus status;

    private String name;

    private int credit;

    private String dayTime;

    private String classroom;
}
