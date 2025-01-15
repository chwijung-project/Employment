package com.chwimong.project.employment.ui.view;

import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentResult;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmploymentSummaryListView {
	
	@JsonValue
    private List<EmploymentSummaryView> summaryViewList;

    public EmploymentSummaryListView(List<FindEmploymentResult> results) {
        this.summaryViewList = results.stream()
            .map(EmploymentSummaryView::new)
            .collect(Collectors.toList());
    }
}
