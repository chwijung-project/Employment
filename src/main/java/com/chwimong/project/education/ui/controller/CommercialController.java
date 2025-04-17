package com.chwimong.project.education.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chwimong.project.education.exception.EducationException;
import com.chwimong.project.education.ui.view.ApiResponseView;
import com.chwimong.project.education.ui.view.commercial.CommercialListView;
import com.chwimong.project.education.usecase.CommercialFindUseCase;
import com.chwimong.project.employment.exception.MessageType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/educations/commercials")
@Tag(name = "부트캠프 정보 API", description= "부트캠프 검색 및 조회를 위한 메인 API")
public class CommercialController {
	private final CommercialFindUseCase commercialFindUseCase;
	
	@Autowired
	public CommercialController(CommercialFindUseCase commercialFindUseCase) {
		this.commercialFindUseCase = commercialFindUseCase; 
	}
	
	@GetMapping("")
	@Operation(summary = "부트캠프 정보 조회", description = "부트캠프 목록을 조회")
	public ResponseEntity<ApiResponseView<CommercialListView>> getCommercials() {
		
		try {
			var commercialResults = commercialFindUseCase.getCommercials();
			
			CommercialListView commercialListView = new CommercialListView(commercialResults);
			ApiResponseView<CommercialListView> responseView = new ApiResponseView<>(commercialListView);
			
			return ResponseEntity.ok(responseView);
			
		} catch(Exception e) {
			log.error("[CommercialController] getCommercials 부트캠프 정보 조회 실패 ", e.getMessage());
			throw new EducationException(MessageType.INTERNAL_SERVER_ERROR);
		}
	}
}
