package com.chwimong.project.employment.ui.view;

import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmploymentListView {
    private List<EmploymentsInfo> employments;

    public EmploymentListView(List<FindEmploymentResult> results) {
        this.employments = results.stream()
            .map(this::convertToEmploymentInfo)
            .collect(Collectors.toList());
    }

    private EmploymentsInfo convertToEmploymentInfo(FindEmploymentResult result) {
        return new EmploymentsInfo(
            result.getRecruit(),
            result.getCompany(),
            result.getRegion(),
            result.getJob(),
            result.getUrl(),
            result.getEndDate(),
            result.getClosed(),
            result.getCrawlingDate(),
            result.getLogo()
        );
    }

    @Getter
    @AllArgsConstructor
    public static class EmploymentsInfo {
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
}