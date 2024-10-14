package com.chwimong.project.employment.persisntence.mongo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.EmploymentFindQuery;

public interface EmploymentRepositoryCustom {
    Page<EmploymentEntity> getEmployments(Pageable pageable, EmploymentFindQuery query);
}
