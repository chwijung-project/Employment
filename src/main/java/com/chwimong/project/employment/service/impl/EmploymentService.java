package com.chwimong.project.employment.service.impl;

import com.chwimong.project.employment.persisntence.mongo.repository.EmploymentEntityRepository;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmploymentService implements EmploymentFindUseCase {
    private final EmploymentEntityRepository employmentEntityRepository;

    @Autowired
    public EmploymentService(EmploymentEntityRepository employmentEntityRepository) {
        this.employmentEntityRepository = employmentEntityRepository;
    }
    @Override
    public List<FindEmploymentResult> getEmployments() {
        return null;
    }

    @Override
    public FindEmploymentResult getEmployment(EmploymentFindQuery query) {
        return null;
    }
}
