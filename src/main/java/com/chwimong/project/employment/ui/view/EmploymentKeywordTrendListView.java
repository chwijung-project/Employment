
package com.chwimong.project.employment.ui.view;

import java.util.List;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentKeywordTrendResult;

import lombok.Getter;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Getter
@ToString
public class EmploymentKeywordTrendListView {
    private List<EmploymentKeywordCountInfo> employmentskeywordcounts;

    public EmploymentKeywordTrendListView(List<FindEmploymentKeywordTrendResult> result) {
        this.employmentskeywordcounts = result.stream()
            .map(this::convertToEmploymentKeywordCountInfo)
            .toList();
    }  

    private EmploymentKeywordCountInfo convertToEmploymentKeywordCountInfo(FindEmploymentKeywordTrendResult result) {
        return new EmploymentKeywordCountInfo(
            result.getKeyword(),
            result.getCount()
        );
    }
    
    @Getter
    @AllArgsConstructor
    public static class EmploymentKeywordCountInfo {
        private String keyword;
        private Long count;
    }


}
