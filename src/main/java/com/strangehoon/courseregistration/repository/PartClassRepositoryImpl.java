package com.strangehoon.courseregistration.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.dto.QPartClassDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static com.querydsl.core.QueryModifiers.offset;
import static com.strangehoon.courseregistration.domain.QMajor.major;
import static com.strangehoon.courseregistration.domain.QPartClass.partClass;
import static com.strangehoon.courseregistration.domain.QPocket.pocket;
import static com.strangehoon.courseregistration.domain.QStudent.student;

public class PartClassRepositoryImpl implements PartClassRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PartClassRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }



    public List<PartClassDto> findPocketAll(Long studentId) {
        List<PartClassDto> content = queryFactory
                .select(new QPartClassDto(
                        partClass.id,
                        partClass.classNum,
                        partClass.name,
                        partClass.grade,
                        partClass.credit,
                        partClass.capacity,
                        partClass.remainNum,
                        partClass.professorName,
                        partClass.dayTime,
                        partClass.classroom,
                        major.name))
                .from(partClass, pocket)
                .leftJoin(partClass.major, major)
                .leftJoin(pocket.partClass, partClass)
                .leftJoin(pocket.student, student)
                .where(
                        student.id.eq(studentId),
                        pocket.partClass.id.eq(partClass.id),
                        pocket.student.id.eq(studentId),
                        partClass.major.id.eq(major.id)
                )
                .distinct()
                .fetch();


        return content;
    }

    @Override
    public Page<PartClassDto> findPartClassDtoAll(PartClassSearch partClassSearch, Pageable pageable) {

        List<PartClassDto> content = queryFactory
                .select(new QPartClassDto(
                        partClass.id,
                        partClass.classNum,
                        partClass.name,
                        partClass.grade,
                        partClass.credit,
                        partClass.capacity,
                        partClass.remainNum,
                        partClass.professorName,
                        partClass.dayTime,
                        partClass.classroom,
                        major.name))
                .from(partClass)
                .leftJoin(partClass.major, major)
                .where(partClassNameEq(partClassSearch.getPartClassName()),
                        partClassGradeEq(partClassSearch.getSchoolYear()),
                        majorNameEq(partClassSearch.getMajorName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(partClass.count())
                .from(partClass)
                .leftJoin(partClass.major, major)
                .where(partClassNameEq(partClassSearch.getPartClassName()),
                        partClassGradeEq(partClassSearch.getSchoolYear()),
                        majorNameEq(partClassSearch.getMajorName())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression partClassNameEq(String partClassName) {
        if(!StringUtils.hasText(partClassName)) {
            return null;
        }
        return partClass.name.contains(partClassName);
    }

    private BooleanExpression partClassGradeEq(Integer schoolYear) {
        if(schoolYear == null) {
            return null;
        }
        return partClass.grade.eq(schoolYear);
    }

    private BooleanExpression majorNameEq(String majorName) {
        if(!StringUtils.hasText(majorName)) {
            return null;
        }
        return major.name.eq(majorName);
    }
}
