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
public class Major {

    @Id @GeneratedValue
    @Column(name = "major_id")
    private Long id;

    @OneToMany(mappedBy = "major")
    private List<PartClass> partClasses = new ArrayList<>();

    @OneToMany(mappedBy = "major")
    private List<Student> students = new ArrayList<>();

    private String name;

    //==생성 메서드==//
    public static Major createMajor(String name) {
        Major major = Major.builder()
                .name(name)
                .build();

        return major;
    }

    @Builder
    private Major(String name) {
        this.name = name;
    }

    //==비즈니스 로직==//
    public void updateMajorName(String majorName){
        name = majorName;
    }

}
