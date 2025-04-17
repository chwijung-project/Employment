package com.chwimong.project.education.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chwimong.project.education.exception.EducationException;
import com.chwimong.project.education.persistence.mongo.entity.ProjectEntity;
import com.chwimong.project.education.persistence.mongo.repository.ProjectEntityRepository;
import com.chwimong.project.education.usecase.ProjectFindUseCase;
import com.chwimong.project.employment.exception.MessageType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectService implements ProjectFindUseCase {
	private final ProjectEntityRepository projectEntityRepository;
	
	@Autowired
	public ProjectService(ProjectEntityRepository projectEntityRepository) {
		this.projectEntityRepository = projectEntityRepository; 
	}
	
	@Override
	public List<FindProjectResult> getProjects() {
		
		try {
			List<ProjectEntity> entities = projectEntityRepository.findAll();

			return entities.stream()
					.map(this::convertToFindProjectsResult)
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error("[ProjectService] getProjects");
			throw new EducationException(MessageType.INTERNAL_SERVER_ERROR);
		}
	}
	
	private FindProjectResult convertToFindProjectsResult(ProjectEntity entity) {
		return FindProjectResult.builder()
			.id(entity.getId())
			.projectName(entity.getProjectName())
	        .teamName(entity.getTeamName())
	        .description(entity.getDescription())
	        .mainfunction(entity.getMainfunction())
	        .category(entity.getCategory())
	        .notionUrl(entity.getNotionUrl())
	        .duration(FindProjectResult.Duration.builder()
	            .start(entity.getDuration().getStart())
	            .end(entity.getDuration().getEnd())
	            .build())
	        .industry(entity.getIndustry())
	        .image(entity.getImage())
			.build();
	}
}
