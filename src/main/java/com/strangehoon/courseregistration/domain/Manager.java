package com.strangehoon.courseregistration.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    private String loginId;

    private String password;

    //==생성 메서드==//
    public static Manager createManager(String loginId, String password) {
        Manager manager = Manager.builder()
                .loginId(loginId)
                .password(password)
                .build();

        return manager;
    }

    @Builder
    private Manager(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
