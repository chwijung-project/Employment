package com.chwimong.project.employment.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chwimong.project.employment.ui.view.ApiResponseView;
import com.chwimong.project.employment.ui.view.client.MentorpickListView;
import com.chwimong.project.employment.usecase.MentorpickFindUseCase;

import io.swagger.v3.oas.annotations.Operation;

import com.chwimong.project.employment.exception.EmploymentException;
import com.chwimong.project.employment.exception.MessageType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/mentorpicks")

@Tag(name = "멘토추천 API", description= "AI 직무별 핵심 기술 역량 추천 정보 제공 API")
public class MentorpickController {
    private final MentorpickFindUseCase mentorpickFindUseCase;

    @Autowired
    public MentorpickController(MentorpickFindUseCase  mentorpickFindUseCase) {
        this.mentorpickFindUseCase = mentorpickFindUseCase;
    }

    @GetMapping("")
    @Operation(summary = "멘토픽 목록 조회", description = "직무별로 필요한 기술 스택 정보를 카테고리별로 조회")
    public ResponseEntity<ApiResponseView<MentorpickListView>> getEmployments() {

    	try {
    		var result = mentorpickFindUseCase.getMentorpick();
    		
    		MentorpickListView view = new MentorpickListView(result.stream().toList());
    		ApiResponseView<MentorpickListView> response = ApiResponseView.of(MessageType.OK, view);
    		
    		return ResponseEntity.ok(response);
    	} catch(Exception e) {
    		log.error("[MentorpickController] getEmployments 멘토픽 목록 조회 실패 ", e.getMessage(), e);
    		throw new EmploymentException(MessageType.INTERNAL_SERVER_ERROR);
    	}
    }
}
    		
    
