package com.chwimong.project.employment.ui.view;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class EmploymentView {
	private String recruit;
    private String company;
    private String region;
    private String job;
    private String url;
    private String endDate;
    private Boolean closed;
    private String crawlingDate;
    private String logo;

    public EmploymentView(EmploymentFindUseCase.FindEmploymentResult result) {
    	this.recruit = result.getRecruit();
        this.company = result.getCompany();
        this.region = result.getRegion();
        this.job = result.getJob();
        this.url = result.getUrl();
        this.endDate = result.getEndDate();
        this.closed = result.getClosed();
        this.crawlingDate = result.getCrawlingDate();
        this.logo = result.getLogo();
    }
}
