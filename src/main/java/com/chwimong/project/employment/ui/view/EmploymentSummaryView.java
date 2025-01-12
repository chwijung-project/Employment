package com.chwimong.project.employment.ui.view;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmploymentSummaryView {
    private String id;
    private String main;
    private String require;
    private String thanks;
    private String fullTxt;

    public EmploymentSummaryView(EmploymentFindUseCase.FindEmploymentResult result) {
        this.id = result.getRecruit();
        this.main = result.getCompany();
        this.require = result.getRegion();
        this.thanks = result.getJob();
        this.fullTxt = result.getUrl();
    }
}
