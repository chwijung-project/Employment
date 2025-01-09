package com.chwimong.project.employment.ui.controller;

import com.chwimong.project.employment.ui.view.ApiResponseView;
import com.chwimong.project.employment.ui.view.EmploymentSummaryView;
import com.chwimong.project.employment.usecase.FilterUseCase;
import com.chwimong.project.employment.usecase.FilterUseCase.FilterResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v2/filterSystem")
public class FilterController {
    private final FilterUseCase filterUseCase;

    @Autowired
    public FilterController(FilterUseCase filterUseCase) {
        this.filterUseCase = filterUseCase;
    }

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponseView<EmploymentSummaryView>> requestUnFilteredRenew() {
        FilterResult result = filterUseCase.findUnFilteredEmployments();

        return ResponseEntity.ok().build();
    }
}
