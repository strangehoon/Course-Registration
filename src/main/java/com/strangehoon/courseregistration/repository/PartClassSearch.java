package com.strangehoon.courseregistration.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;

@Getter @Setter
public class PartClassSearch {

    private String majorName;

    private Integer schoolYear;

    private String partClassName;

}
