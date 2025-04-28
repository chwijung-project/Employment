package com.chwimong.project.employment.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chwimong.project.employment.persisntence.mongo.repository.MentorpickEntityRepository;
import com.chwimong.project.employment.exception.EmploymentException;
import com.chwimong.project.employment.exception.MessageType;
import com.chwimong.project.employment.persisntence.mongo.entity.MentorpickEntity;
import com.chwimong.project.employment.usecase.MentorpickFindUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MentorpickService implements MentorpickFindUseCase{
    private final MentorpickEntityRepository mentorpickEntityRepository;

    @Autowired
    public MentorpickService(MentorpickEntityRepository mentorpickEntityRepository) {
        this.mentorpickEntityRepository = mentorpickEntityRepository;
        // Constructor
    }

    @Override
    public List<FindMentorpickResult> getMentorpick() {
    	try {
    		List<MentorpickEntity> entities = mentorpickEntityRepository.findAll();
    		return entities.stream().map(this::convertToFindMentorpickResult)
    				.toList();
    	} catch(Exception e) {
    		log.error("[MentorpickService] getMentorpick - error: {}", e.getMessage(), e);
    		throw new EmploymentException(MessageType.INTERNAL_SERVER_ERROR);
    	}
    }

    private FindMentorpickResult convertToFindMentorpickResult(MentorpickEntity entity) {
        return FindMentorpickResult.builder()
                .id(entity.getId())
                .role(entity.getRole())
                .jobtitle(entity.getJobtitle())
                .keywords(entity.getKeywords())
                .build();
    }

}