package com.strangehoon.courseregistration.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Register {

    @Id @GeneratedValue
    @Column(name = "register_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partclass_id")
    private PartClass partclass ;

    private LocalDateTime registerDateTime;

    @Enumerated(EnumType.STRING)
    private RetakeStatus retakeStatus;
}
