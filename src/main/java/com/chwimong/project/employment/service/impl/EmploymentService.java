package com.chwimong.project.employment.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    	int index = cri.getPageNum() -1;
		int count = cri.getAmount();

		Pageable paging = PageRequest.of(index, count);
		Page<EmploymentEntity> employmentEntities = employmentEntityRepository.getEmployments(paging, query);

		return employmentEntities.map(this::convertToFindEmploymentsResult);
    }

    @Override
    public List<FindEmploymentResult> getEmploymentsWithCategory(EmploymentWithCategoryQuery query) {

    	List<EmploymentEntity> entities = employmentEntityRepository.findByJobtitleFilterEquals(query.getJobtitle());

    	return entities.stream()
	        .map(this::convertToCategoryResult)
	        .collect(Collectors.toList());
    }

    @Override
    public List<FindEmploymentCountResult> getEmploymentCountResults() {

        List<EmploymentGroupedQuery> rawData = employmentEntityRepository.findGroupedEmploymentData();
        Map<String, FindEmploymentCountResult> groupedMap = new LinkedHashMap<>();

        for (EmploymentGroupedQuery data : rawData) {
            groupedMap.computeIfAbsent(data.getCrawlingDate(), date -> 
                FindEmploymentCountResult.builder()
                    .crawlingDate(date)
                    .month(data.getMonth())
                    .jobs(new ArrayList<>()) 
                    .build()
            ).getJobs().add(new JobData(data.getFilteredJobtitle(), data.getCount()));
        }
        
        return new ArrayList<>(groupedMap.values());
    } 
    
    @Override
    public List<FindEmploymentKeywordTrendResult> getEmploymentKeywordTrendResults(String filter) {
        List<EmploymentKeywordTrendQuery> rawData = null;

        if ("steady".equals(filter)) {
            rawData = employmentEntityRepository.findEmploymentSteadyKeywordTrend();
        } else if ("new".equals(filter)) {
            Date threeMonthsAgo = Date.from(LocalDate.now().minusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant());
            rawData = employmentEntityRepository.findEmploymentHotKeywordTrend(threeMonthsAgo);
        }
        return rawData.stream().map(this::convertToKeywordTrendResult).collect(Collectors.toList());
        
    }

    @Override
    public List<FindEmploymentKeywordMapResult> getEmploymentKeywordMapByJobtitleResults() {
        List<EmploymentKeywordMapQuery> rawData = employmentEntityRepository.findEmploymentKeywordMapByJobtitle();
        return rawData.stream().map(this::convertToKeywordResult).collect(Collectors.toList());
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
    
    private FindEmploymentKeywordTrendResult convertToKeywordTrendResult(EmploymentKeywordTrendQuery query) {
        return FindEmploymentKeywordTrendResult.builder()
            .keyword(query.getKeyword())
            .count(query.getCount())
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

    private FindEmploymentKeywordMapResult convertToKeywordResult(EmploymentKeywordMapQuery query) {
        return FindEmploymentKeywordMapResult.builder()
            .jobtitle(query.getJobtitle())
            .keywords(query.getKeywords())
            .build();
    }   
}
