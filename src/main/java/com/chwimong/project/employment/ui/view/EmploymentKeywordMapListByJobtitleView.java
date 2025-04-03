package com.chwimong.project.employment.ui.view;

import java.util.List;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentKeywordMapResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmploymentKeywordMapListByJobtitleView {

    private List<EmploymentKeywordMapInfo> employmentskeywordmap;

    public EmploymentKeywordMapListByJobtitleView(List<FindEmploymentKeywordMapResult> result) {
        this.employmentskeywordmap = result.stream()
            .map(this::convertToEmploymentKeywordMapInfo)
            .toList();
    }
    private EmploymentKeywordMapInfo convertToEmploymentKeywordMapInfo(FindEmploymentKeywordMapResult result) {
        return new EmploymentKeywordMapInfo(
            result.getJobtitle(),
            result.getKeywords()
        );
    }
    @Getter
    @AllArgsConstructor
    public static class EmploymentKeywordMapInfo {
        private String jobtitle;
        private List<String> keywords;
    }

}
