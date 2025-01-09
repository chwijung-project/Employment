package com.chwimong.project.employment.ui.controller;

import com.chwimong.project.employment.ui.view.ApiResponseView;
import com.chwimong.project.employment.ui.view.EmploymentSummaryListView;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/view/compact-employments")
@ToString
public class CompactEmploymentController {
    private final EmploymentFindUseCase employmentFindUseCase;

    @Autowired
    public CompactEmploymentController(EmploymentFindUseCase employmentFindUseCase) {
        this.employmentFindUseCase = employmentFindUseCase;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponseView<EmploymentSummaryListView>> getCompactEmployments(
            @RequestParam(value = "category", required = false, defaultValue = "no-job") String category
    ) {
        var query = new EmploymentFindUseCase.EmploymentWithCategoryQuery(category);

        var employmentResults = employmentFindUseCase.getEmploymentsWithCategory(query);

        //TODO: employment Result to EmploymentSummaryListView

        return ResponseEntity.ok().build();
    }

}
