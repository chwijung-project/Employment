package com.chwimong.project.employment.ui.view.filterSystem;

import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentResult;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmploymentKeywordExtractionListView {

	@JsonValue
	private List<EmploymentKeywordExtractionView> keywordViewList;
	
	public EmploymentKeywordExtractionListView(List<FindEmploymentResult> results) {
		this.keywordViewList = results.stream()
			.map(EmploymentKeywordExtractionView::new)
			.collect(Collectors.toList());
	}
}
