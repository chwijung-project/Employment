package com.chwimong.project.employment.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chwimong.project.employment.exception.MessageType;
import com.chwimong.project.employment.ui.common.Criteria;
import com.chwimong.project.employment.ui.common.Page;
import com.chwimong.project.employment.ui.request.FindEmploymentRequest;
import com.chwimong.project.employment.ui.view.ApiResponseView;
import com.chwimong.project.employment.ui.view.EmploymentListView;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.EmploymentFindQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/employments")
public class EmploymentController {
    private final EmploymentFindUseCase employmentFindUseCase;

    @Autowired
    public EmploymentController(EmploymentFindUseCase employmentFindUseCase) {
        this.employmentFindUseCase = employmentFindUseCase;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponseView<EmploymentListView>> getEmployments(@ModelAttribute FindEmploymentRequest request) {

    	EmploymentFindQuery query = new EmploymentFindQuery(
	        request.getId(),
	        request.getJob(), 
	        request.getRegion(),
	        request.getSort(),
	        request.getClosed(),
	        request.getOffset()
	    );
    	
    	var cri = new Criteria(Integer.valueOf(query.getOffset()), 10);
    	var resultPage = employmentFindUseCase.getEmployments(cri, query);
        var pageInfo = new Page(cri, (int) resultPage.getTotalElements());
        
        EmploymentListView employmentListView = new EmploymentListView(resultPage.getContent());
        ApiResponseView<EmploymentListView> responseView = ApiResponseView.of(MessageType.OK, employmentListView, pageInfo);
        
        return ResponseEntity.ok(responseView);
    }
    
}
