package com.chwimong.project.employment.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chwimong.project.config.Criteria;
import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import com.chwimong.project.employment.persisntence.mongo.repository.EmploymentEntityRepository;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmploymentService implements EmploymentFindUseCase {
    private final EmploymentEntityRepository employmentEntityRepository;

    @Autowired
    public EmploymentService(EmploymentEntityRepository employmentEntityRepository) {
        this.employmentEntityRepository = employmentEntityRepository;
    }
    
    @Override
    public int getEmploymentsSize() {
    	List<EmploymentEntity> employmentEntities = employmentEntityRepository.findAll();
    	return employmentEntities.size();
    }
    
    @Override
    public List<FindEmploymentResult> getEmployments(Criteria cri, EmploymentFindQuery query) {
    	
    	int index = cri.getPageNum() -1;
		int count = cri.getAmount();
		
		Pageable paging = PageRequest.of(index, count);
		Page<EmploymentEntity> employmentEntities = employmentEntityRepository.getEmployments(paging, query);
    	
        return employmentEntities.stream()
            .map(this::convertToFindEmploymentsResult)
            .collect(Collectors.toList());
    }

    private FindEmploymentResult convertToFindEmploymentsResult(EmploymentEntity entity) {
        return FindEmploymentResult.builder()
            .recruit(entity.getRecruit())
            .company(entity.getCompany())
            .region(entity.getRegion())
            .job(entity.getJob())
            .url(entity.getUrl())
            .endDate(entity.getEndDate())
            .closed(entity.getClosed())
            .crawlingDate(entity.getCrawlingDate())
            .logo(entity.getLogo())
            .build();
    }
    
    @Override
    public FindEmploymentResult getEmployment(EmploymentFindQuery query) {

    	return null;
    }
}
