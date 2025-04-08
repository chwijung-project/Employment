package com.chwimong.project.employment.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chwimong.project.employment.exception.ChwimongException;
import com.chwimong.project.employment.exception.MessageType;
import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import com.chwimong.project.employment.persisntence.mongo.repository.EmploymentEntityRepository;
import com.chwimong.project.employment.ui.common.Criteria;
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
    public Page<FindEmploymentResult> getEmployments(Criteria cri, EmploymentFindQuery query) {
    	try {
	    	int index = cri.getPageNum() -1;
			int count = cri.getAmount();
	
			Pageable paging = PageRequest.of(index, count);
			Page<EmploymentEntity> employmentEntities = employmentEntityRepository.getEmployments(paging, query);
			
			return employmentEntities.map(this::convertToFindEmploymentsResult);
			
    	} catch (Exception e) {
            log.error("[EmploymentService] 채용정보 조회 실패 - cri: {}, query: {}, error: {}", cri, query, e.getMessage(), e);
            throw new ChwimongException(MessageType.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<FindEmploymentResult> getEmploymentsWithCategory(EmploymentWithCategoryQuery query) {
    	try {
    		List<EmploymentEntity> entities = employmentEntityRepository.findByFilteredJobtitleEquals(query.getJobtitle());
    		
    		return entities.stream()
    				.map(this::convertToCategoryResult)
    				.collect(Collectors.toList());
    	} catch(Exception e) {
    		log.error("[EmploymentService] getEmploymentsWithCategory");
    		throw new ChwimongException(MessageType.INTERNAL_SERVER_ERROR);
    	}
    }

    @Override
	public List<FindEmploymentResult> getEmploymentsWithKeyword(EmploymentWithKeywordQuery query) {
    	try {
    		List<EmploymentEntity> entities = employmentEntityRepository.findByKeywordEquals(query.getKeyword());
    		
    		return entities.stream()
    				.map(this::convertToKeywordResult)
    				.collect(Collectors.toList());
    	} catch(Exception e) {
    		log.error("[EmploymentService] getEmploymentsWithKeyword");
    		throw new ChwimongException(MessageType.INTERNAL_SERVER_ERROR);
    	}
	}
    
    private FindEmploymentResult convertToFindEmploymentsResult(EmploymentEntity entity) {
        return FindEmploymentResult.builder()
            .recruit(entity.getRecruit())
            .company(entity.getCompany())
            .region(entity.getRegion())
            .job(entity.getJob())
            .url(entity.getUrl())
            .endDate(entity.getEndDate())
            .crawlingDate(entity.getCrawlingDate())
            .logo(entity.getLogo())
            .build();
    }
    
    private FindEmploymentResult convertToCategoryResult(EmploymentEntity entity) {
        return FindEmploymentResult.builder()
            .id(entity.getId())
            .main(entity.getMain())
            .require(entity.getRequire())
            .thanks(entity.getThanks())
            .fullTxt(entity.getFullTxt())
            .build();
    }
    
    private FindEmploymentResult convertToKeywordResult(EmploymentEntity entity) {
    	return FindEmploymentResult.builder()
    		.id(entity.getId())
    		.thanks(entity.getThanks())
    		.require(entity.getRequire())
    		.build();
    }

}
