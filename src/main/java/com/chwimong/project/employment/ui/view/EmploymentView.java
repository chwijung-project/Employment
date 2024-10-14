package com.chwimong.project.employment.ui.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class EmploymentView {
	private String recruit;
    private String company;
    private String region;
    private String job;
    private String url;
    private String endDate;
    private String closed;
    private String crawlingDate;
    private String logo;
}
