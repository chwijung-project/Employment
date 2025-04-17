package com.chwimong.project.employment.ui.view.client;

import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentCountResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmploymentCountListView {
    private List<EmploymentCount> employments;

    public EmploymentCountListView(List<FindEmploymentCountResult> results) {
        this.employments = results.stream()
            .map(this::convertToEmploymentInfo)
            .collect(Collectors.toList());
    }

    private EmploymentCount convertToEmploymentInfo(FindEmploymentCountResult result) {
        return new EmploymentCount(
            result.getCrawlingDate(),
            result.getMonth(),
            result.getJobs().stream()
                .map(job -> new JobData(job.getFilteredJobtitle(), job.getCount()))
                .collect(Collectors.toList())
        );
    }

    @Getter
    @AllArgsConstructor
    public static class EmploymentCount {
        private String crawlingDate;  
        private int month;  
        private List<JobData> jobs;  
    }

    @Getter
    @AllArgsConstructor
    public static class JobData {
        private String filteredJobtitle; 
        private Long count;
    }
}
