package com.chwimong.project.education.ui.view.commercial;

import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.education.usecase.CommercialFindUseCase.FindCommercialResult;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommercialListView { 
	
	@JsonValue
	private List<CommercialView> commercialViewList;
	
	public CommercialListView(List<FindCommercialResult> result) {
		this.commercialViewList = result.stream()
			.map(CommercialView::new)
			.collect(Collectors.toList());
	}
}
