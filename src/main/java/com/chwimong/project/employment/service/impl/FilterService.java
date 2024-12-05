package com.chwimong.project.employment.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import com.chwimong.project.employment.persisntence.mongo.repository.EmploymentEntityRepository;
import com.chwimong.project.employment.usecase.FilterUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FilterService implements FilterUseCase {

	private final EmploymentEntityRepository employmentEntityRepository;

    @Autowired
    public FilterService(EmploymentEntityRepository employmentEntityRepository) {
        this.employmentEntityRepository = employmentEntityRepository;
    }
    
    @Override
//    @Scheduled(cron = "0 0 16 ? * MON", zone = "Asia/Seoul")
    public List<UnFilteredEmploymentsResult> findUnFilteredEmployments() {
	   List<EmploymentEntity> unfiltered = 
	       employmentEntityRepository.findByJobtitleFilterEquals("");

	   if(!unfiltered.isEmpty()) {
	       List<UnFilteredEmploymentsResult> results = unfiltered.stream()
	           .map(this::convertToUnFilteredResult)
	           .collect(Collectors.toList());
	           
	       sendToFilterSystem(results);
	       return results;
	   }
	   
	   return Collections.emptyList();
	}

	private UnFilteredEmploymentsResult convertToUnFilteredResult(EmploymentEntity entity) {
	   return UnFilteredEmploymentsResult.builder()
	       .id(entity.getId())
	       .main(entity.getMain())
	       .require(entity.getRequire())
	       .thanks(entity.getThanks())
	       .build();
	}

	private void sendToFilterSystem(List<UnFilteredEmploymentsResult> results) {
		
		ObjectMapper objectMapper = new ObjectMapper();
	    try {
	        String jsonData = objectMapper.writeValueAsString(results);
	        // 전송로직 구성
	    } catch (JsonProcessingException e) {
	        log.error("전송실패", e);
	    }
	}
}
