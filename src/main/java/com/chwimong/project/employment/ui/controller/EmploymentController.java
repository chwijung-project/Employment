package com.chwimong.project.employment.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chwimong.project.config.Criteria;
import com.chwimong.project.config.Page;
import com.chwimong.project.employment.exception.MessageType;
import com.chwimong.project.employment.ui.view.ApiResponseView;
import com.chwimong.project.employment.ui.view.EmploymentListView;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.EmploymentFindQuery;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentResult;

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

    @GetMapping("/list")
    public ResponseEntity<ApiResponseView<EmploymentListView>> getEmployments(@ModelAttribute EmploymentFindQuery query) {

    	Criteria cri = new Criteria(Integer.valueOf(query.getOffset()), 10);
    	int total = employmentFindUseCase.getEmploymentsSize();
    	
    	Page pageInfo = new Page(cri, total);
    	
        List<FindEmploymentResult> employmentResults = employmentFindUseCase.getEmployments(cri, query);
        EmploymentListView employmentListView = new EmploymentListView(employmentResults);
        
        ApiResponseView<EmploymentListView> responseView = ApiResponseView.of(MessageType.OK, employmentListView, pageInfo);
        
        return ResponseEntity.ok(responseView);
    }
    
//  @GetMapping("/{id}")
//  public ResponseEntity<ApiResponseView<EmploymentView>> getEmployment(@ModelAttribute EmploymentFindQuery query) {
//
//      return ResponseEntity.ok().build();
//  }
}
