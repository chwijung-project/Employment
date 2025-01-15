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
        this.id = result.getId();
        this.main = result.getMain();
        this.require = result.getRequire();
        this.thanks = result.getThanks();
        this.fullTxt = result.getFullTxt();
    }
}
