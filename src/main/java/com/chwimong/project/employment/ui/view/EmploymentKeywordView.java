package com.chwimong.project.employment.ui.view;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmploymentKeywordView {

	private String id;
	private String thanks;
	
	public EmploymentKeywordView(EmploymentFindUseCase.FindEmploymentResult result) {
		this.id = result.getId();
		this.thanks = result.getThanks();
	}
}
