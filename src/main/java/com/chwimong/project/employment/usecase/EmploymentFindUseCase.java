package com.chwimong.project.employment.usecase;

import com.chwimong.project.employment.dto.EmploymentDTO;

import java.util.List;

public interface EmploymentFindUseCase {
    List<EmploymentDTO> getEmployments(); //TODO: FindQuery 를 Param 으로
    EmploymentDTO getEmployment(String id);
}
