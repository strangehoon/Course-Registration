package com.strangehoon.courseregistration.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDto {

    private String dayTime;

    private String name;

    private int credit;

    public RegisterDto(String dayTime, String name, int credit) {
        this.dayTime = dayTime;
        this.name = name;
        this.credit = credit;
    }
}
