package com.chwimong.project.employment.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chwimong.project.employment.exception.ChwimongException;
import com.chwimong.project.employment.exception.MessageType;
import com.chwimong.project.employment.ui.view.ApiResponseView;
import com.chwimong.project.employment.ui.view.EmploymentKeywordListView;
import com.chwimong.project.employment.ui.view.EmploymentSummaryListView;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/view/compact-employments")
@Tag(name = "요약 채용정보 API", description= "요약 채용정보 조회를 위한 메인 API")
@ToString
public class CompactEmploymentController {
    private final EmploymentFindUseCase employmentFindUseCase;

    @Autowired
    public CompactEmploymentController(EmploymentFindUseCase employmentFindUseCase) {
        this.employmentFindUseCase = employmentFindUseCase;
    }

    @GetMapping("")
    @Operation(summary = "직무명 필터링용 요약 채용정보 조회", description = "조건에 따른 요약 채용정보 목록을 조회")
    public ResponseEntity<ApiResponseView<EmploymentSummaryListView>> getCompactEmploymentsForFiltering(
    		@RequestParam(value = "jobtitle", required = false) String jobtitle
    ) {
    	
    	try {
    		log.info("[CompactEmploymentController] getCompactEmploymentsForFiltering 직무명 필터링용 요약 채용정보 조회 요청 - jobtitle: {}", jobtitle);
    		
    		var query = new EmploymentFindUseCase.EmploymentWithCategoryQuery(jobtitle);
    		var employmentResults = employmentFindUseCase.getEmploymentsWithCategory(query);
    		
    		EmploymentSummaryListView summaryListView = new EmploymentSummaryListView(employmentResults);
    		ApiResponseView<EmploymentSummaryListView> responseView = new ApiResponseView<>(summaryListView);
    		
    		return ResponseEntity.ok(responseView);
    		
    	} catch(Exception e) {
    		log.error("[CompactEmploymentController] getCompactEmploymentsForFiltering 요약 채용정보 조회 실패 - jobtitle: {}", jobtitle, e.getMessage());
    		throw new ChwimongException(MessageType.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping("/keyword")
    @Operation(summary = "키워드 추출용 요약 채용정보 조회", description = "조건에 따른 요약 채용정보 목록을 조회")
    public ResponseEntity<ApiResponseView<EmploymentKeywordListView>> getCompactEmploymentsForKeyword(
    		@RequestParam(value = "thanks", required = false) String thanks
	) {
    	
    	try {
    		log.info("[CompactEmploymentController] getCompactEmploymentsForKeyword 키워드 추출용 요약 채용정보 조회 요청 - keyword: {}", thanks);
    		
    		var query = new EmploymentFindUseCase.EmploymentWithKeywordQuery(thanks);
    		var employmentResults = employmentFindUseCase.getEmploymentsWithKeyword(query);
    		
    		EmploymentKeywordListView keywordListView = new EmploymentKeywordListView(employmentResults);
    		ApiResponseView<EmploymentKeywordListView> responseView = new ApiResponseView<>(keywordListView);
    		
    		return ResponseEntity.ok(responseView);
    		
    	} catch(Exception e) {
    		log.error("[CompactEmploymentController] getCompactEmploymentsForKeyword 요약 채용정보 조회 실패 - keyword: {}", thanks, e.getMessage());
    		throw new ChwimongException(MessageType.INTERNAL_SERVER_ERROR);
    	}
    }
}

