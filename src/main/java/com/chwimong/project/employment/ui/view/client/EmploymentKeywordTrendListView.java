
package com.chwimong.project.employment.ui.view.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentResult;

import lombok.Getter;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Getter
@ToString
public class EmploymentKeywordTrendListView {
    private List<EmploymentKeywordCountInfo> employmentskeywordcounts;

    public EmploymentKeywordTrendListView(List<FindEmploymentResult> result) {
        Map<String, Long> keywordCountMap = new HashMap<>();

        for (FindEmploymentResult item : result) {
            String keywordStr = item.getKeyword();  
            if (keywordStr != null && !keywordStr.isBlank()) {
                String[] keywords = keywordStr.split(",");
                for (String rawKeyword : keywords) {
                    String keyword = rawKeyword.trim();
                    if (!keyword.isEmpty()) {
                        keywordCountMap.put(keyword, keywordCountMap.getOrDefault(keyword, 0L) + 1);
                    }
                }
            }
        }

        this.employmentskeywordcounts = keywordCountMap.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // 내림차순 정렬
            .limit(20)
            .map(entry -> new EmploymentKeywordCountInfo(entry.getKey(), entry.getValue()))
            .toList();
    }
    
    @Getter
    @AllArgsConstructor
    public static class EmploymentKeywordCountInfo {
        private String keyword;
        private Long count;
    }


}
