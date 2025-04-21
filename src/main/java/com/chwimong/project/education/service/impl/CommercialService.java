package com.chwimong.project.education.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chwimong.project.education.exception.EducationException;
import com.chwimong.project.education.persistence.mongo.entity.CommercialEntity;
import com.chwimong.project.education.persistence.mongo.repository.CommercialEntityRepository;
import com.chwimong.project.education.usecase.CommercialFindUseCase;
import com.chwimong.project.employment.exception.MessageType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommercialService implements CommercialFindUseCase { 
	private final CommercialEntityRepository commercialEntityRepository;
	
	@Autowired
	public CommercialService(CommercialEntityRepository commercialEntityRepository) {
		this.commercialEntityRepository = commercialEntityRepository;
	}
	
	@Override
	public List<FindCommercialResult> getCommercials() {
		
		try {
			List<CommercialEntity> entities = commercialEntityRepository.findAll();
			
			return entities.stream()
					.map(this::convertToFindCommercialResult)
					.collect(Collectors.toList());
		} catch(Exception e) {
			log.error("[CommercialService] getCommercials");
			throw new EducationException(MessageType.INTERNAL_SERVER_ERROR);
		}
	}
	
	private FindCommercialResult convertToFindCommercialResult(CommercialEntity entity) {
		return FindCommercialResult.builder()
			.id(entity.getId())
			.provider(entity.getProvider())
			.title(entity.getTitle())
			.summary(entity.getSummary())
			.category(entity.getCategory())
			.type(entity.getType())
			.logo(entity.getLogo())
			.url(entity.getUrl())
			.build();
	}
}
