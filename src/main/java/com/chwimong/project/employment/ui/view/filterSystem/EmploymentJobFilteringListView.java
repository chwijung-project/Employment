package com.chwimong.project.employment.ui.view.filterSystem;

import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentResult;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmploymentJobFilteringListView {
	
	@JsonValue
    private List<EmploymentJobFilteringView> summaryViewList;

    public EmploymentJobFilteringListView(List<FindEmploymentResult> results) {
        this.summaryViewList = results.stream()
            .map(EmploymentJobFilteringView::new)
            .collect(Collectors.toList());
    }
}
