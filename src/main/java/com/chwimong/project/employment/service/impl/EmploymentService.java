package com.chwimong.project.employment.service.impl;

import com.chwimong.project.employment.dto.EmploymentDTO;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmploymentService implements EmploymentFindUseCase {
    @Override
    public List<EmploymentDTO> getEmployments() {
        return null;
    }

    @Override
    public EmploymentDTO getEmployment(String id) {
        return null;
    }
}
