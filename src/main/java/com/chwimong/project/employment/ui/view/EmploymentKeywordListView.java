package com.chwimong.project.employment.ui.view;

import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentResult;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmploymentKeywordListView {

	@JsonValue
	private List<EmploymentKeywordView> keywordViewList;
	
	public EmploymentKeywordListView(List<FindEmploymentResult> results) {
		this.keywordViewList = results.stream()
			.map(EmploymentKeywordView::new)
			.collect(Collectors.toList());
	}
}
