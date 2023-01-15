package com.strangehoon.courseregistration.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.servlet.http.Part;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Register extends BaseEntityByRegister{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_class_id")
    private PartClass partClass ;


    //==연관관계 메서드==//
    public void putStudent(Student student) {
        if(this.student != null) {
            this.student.getRegisters().remove(this);
        }
        this.student = student;
        student.getRegisters().add(this);
    }

    public void putPartClass(PartClass partClass) {
        if(this.partClass != null) {
            this.partClass.getRegisters().remove(this);
        }
        this.partClass = partClass;
        partClass.getRegisters().add(this);
    }

    //==생성메서드==//
    public static Register createRegister(PartClass partClass, Student student) {
        Register register = new Register();
        register.putPartClass(partClass);
        register.putStudent(student);
        return register;
    }
}
