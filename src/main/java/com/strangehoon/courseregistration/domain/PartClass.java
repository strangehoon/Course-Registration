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

    @OneToMany(mappedBy = "partClass")
    private List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "partClass")
    private List<Pocket> pockets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    private String name;

    private int credit;

    private String dayTime;

    private String classroom;


    //==연관관계 메서드==//
    public void putMajor(Major major) {
        if (this.major != null) {
            this.major.getPartClasses().remove(this);
        }
        this.major = major;
        major.getPartClasses().add(this);
    }

    //==생성 메서드==//
    public static PartClass createPartClass(Major major, String name, int credit, String dayTime, String classroom) {
        PartClass partClass = PartClass.builder()
                .name(name)
                .credit(credit)
                .dayTime(dayTime)
                .classroom(classroom)
                .build();
        
        partClass.putMajor(major);
        return partClass;
    }
    @Builder
    private PartClass(String name, int credit, String dayTime, String classroom) {
        this.name = name;
        this.credit = credit;
        this.dayTime = dayTime;
        this.classroom = classroom;
    }

    //==비즈니스 로직==//
    public void updatePartClass(String majorName, String name, int credit, String dayTime,String classroom) {
        this.name = name;
        this.credit = credit;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.major.updateMajorName(majorName);
    }

    public void updateId() {
        this.id -=1;
    }
}
