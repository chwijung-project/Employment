package com.chwimong.project.employment.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.chwimong.project.employment.exception.ChwimongException;
import com.chwimong.project.employment.exception.MessageType;
import com.chwimong.project.employment.ui.common.Criteria;
import com.chwimong.project.employment.ui.common.Page;
import com.chwimong.project.employment.ui.request.FindEmploymentRequest;
import com.chwimong.project.employment.ui.view.ApiResponseView;
import com.chwimong.project.employment.ui.view.EmploymentCountListView;
import com.chwimong.project.employment.ui.view.EmploymentKeywordTrendListView;
import com.chwimong.project.employment.ui.view.EmploymentKeywordMapListByJobtitleView;
import com.chwimong.project.employment.ui.view.EmploymentListView;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.EmploymentFindQuery;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/employments")
@Tag(name = "채용정보 API", description= "채용정보 검색 및 조회를 위한 메인 API")
public class EmploymentController {
    private final EmploymentFindUseCase employmentFindUseCase;

    @Autowired
    public EmploymentController(EmploymentFindUseCase employmentFindUseCase) {
        this.employmentFindUseCase = employmentFindUseCase;
    }

    @GetMapping("")
    @Operation(summary = "채용정보 조회", description = "조건에 따른 채용정보 목록을 페이징 처리하여 조회")
    public ResponseEntity<ApiResponseView<EmploymentListView>> getEmployments(@ModelAttribute FindEmploymentRequest request) {

    	try {
	    	log.info("[EmploymentController] getEmployments 채용정보 조회 요청 - job: {}, region: {}, searchValue: {}", 
	    			request.getJob(), request.getRegion(), request.getSearchValue());
	    	
	    	EmploymentFindQuery query = new EmploymentFindQuery(
		        request.getId(),
		        request.getJob(),
		        request.getRegion(),
		        request.getSort(),
		        request.getOffset(),
		        request.getSearchValue()
		    );
	    	
	    	var cri = new Criteria(Integer.valueOf(query.getOffset()), 10);
	    	var resultPage = employmentFindUseCase.getEmployments(cri, query);
	        var pageInfo = new Page(cri, (int) resultPage.getTotalElements());
	        pageInfo.setSearchValue(request.getSearchValue());
	        
	        EmploymentListView employmentListView = new EmploymentListView(resultPage.getContent());
	        ApiResponseView<EmploymentListView> responseView = ApiResponseView.of(MessageType.OK, employmentListView, pageInfo);
	        
	        return ResponseEntity.ok(responseView);
	        
    	} catch (Exception e) {
    		log.error("[EmploymentController] getEmployments 채용정보 조회 실패 - request: {}, error: {}", 
    				request, e.getMessage());
	        throw new ChwimongException(MessageType.INTERNAL_SERVER_ERROR);
    	}
    }

    @GetMapping("/dashboard")
    @Operation(summary = "직무별, 크롤링 주차별 채용공고 개수 조회", description = "직무별로 크롤링 주차에 따른 채용공고 개수 조회")
    public ResponseEntity<ApiResponseView<EmploymentCountListView>> getWeeklyEmploymentCountByJob() {

    	var result = employmentFindUseCase.getEmploymentCountResults();
        EmploymentCountListView employmentListView = new EmploymentCountListView(result.stream().toList());
        ApiResponseView<EmploymentCountListView> responseView = ApiResponseView.of(MessageType.OK, employmentListView);
        
        return ResponseEntity.ok(responseView);
    }

    @GetMapping("/keyword-trend")
    @Operation(summary = "hot, steady 트렌드 키워드", description = "키워드의 빈도 추이를 알기 위해 hot, steady 키워드를 조회")
    public ResponseEntity<ApiResponseView<EmploymentKeywordTrendListView>> getKeywordTrend(
            @RequestParam(name = "filter", required = true) String filter) {
        
                if (!"steady".equalsIgnoreCase(filter) && !"new".equalsIgnoreCase(filter)) {
                    return ResponseEntity.badRequest().body(
                                                            ApiResponseView.of(
                                                                MessageType.BAD_REQUEST,
                                                                null,
                                                                null,
                                                                "유효하지 않은 filter 값입니다. 'steady' 또는 'new'만 허용됩니다."
                                                            )
                                                        );
                }
    
        var result = employmentFindUseCase.getEmploymentKeywordTrendResults(filter);

        EmploymentKeywordTrendListView employmentListView = new EmploymentKeywordTrendListView(result);
        ApiResponseView<EmploymentKeywordTrendListView> responseView = ApiResponseView.of(MessageType.OK, employmentListView);

        return ResponseEntity.ok(responseView);
    }


    @GetMapping("/keyword-map")
    @Operation(summary = "직무별 키워드 맵", description = "직무별 키워드의 리스트 조회")
    public ResponseEntity<ApiResponseView<EmploymentKeywordMapListByJobtitleView>> getKeywordMapByJobtitle() {

        var result = employmentFindUseCase.getEmploymentKeywordMapByJobtitleResults();

        EmploymentKeywordMapListByJobtitleView employmentListView = new EmploymentKeywordMapListByJobtitleView(result);
        ApiResponseView<EmploymentKeywordMapListByJobtitleView> responseView = ApiResponseView.of(MessageType.OK, employmentListView);

        return ResponseEntity.ok(responseView);
    }
    
}
