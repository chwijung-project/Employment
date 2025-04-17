package com.chwimong.project.education.ui.view.commercial;

import com.chwimong.project.education.usecase.CommercialFindUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommercialView {
	private String id;
	private String provider;
	private String title;
	private String summary;
	private String category;
	private String type;
	private String logo;
	private String url;
	
	public CommercialView(CommercialFindUseCase.FindCommercialResult result) {
		this.id = result.getId();
		this.provider = result.getProvider();
		this.title = result.getTitle();
		this.summary = result.getSummary();
		this.category = result.getCategory();
		this.type = result.getType();
		this.logo = result.getLogo();
		this.url = result.getUrl();
	}
	
}
