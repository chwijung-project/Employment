package com.chwimong.project.education.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chwimong.project.education.exception.EducationException;
import com.chwimong.project.education.ui.view.ApiResponseView;
import com.chwimong.project.education.ui.view.project.ProjectListView;
import com.chwimong.project.education.usecase.ProjectFindUseCase;
import com.chwimong.project.employment.exception.MessageType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/educations/projects") 
@Tag(name = "프로젝트 API", description= "프로젝트 정보 제공 API")
public class ProjectController {
	private final ProjectFindUseCase projectFindUseCase;

	@Autowired
	public ProjectController(ProjectFindUseCase projectFindUseCase) {
		this.projectFindUseCase = projectFindUseCase;
	}
	
	@GetMapping("")
	@Operation(summary = "프로젝트 목록 조회", description = "AI/IT 분야 프로젝트 사례 목록을 조회")
	public ResponseEntity<ApiResponseView<ProjectListView>> getProjects() {
		
		try {
			var projectResults = projectFindUseCase.getProjects();
			
			ProjectListView projectListView = new ProjectListView(projectResults);
			ApiResponseView<ProjectListView> responseView = new ApiResponseView<>(projectListView);
			
			return ResponseEntity.ok(responseView);
			
		} catch(Exception e) {
			log.error("[ProjectController] getProjects 프로젝트 목록 조회 실패 ", e.getMessage(), e);
			throw new EducationException(MessageType.INTERNAL_SERVER_ERROR);
		}
	}
}
