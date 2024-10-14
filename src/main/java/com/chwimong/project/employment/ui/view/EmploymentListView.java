package com.chwimong.project.employment.ui.view;

import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.employment.usecase.EmploymentFindUseCase.FindEmploymentResult;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmploymentListView {
	
    private final List<EmploymentView> employments;

    public EmploymentListView(List<FindEmploymentResult> result) {
        this.employments = result.stream()
            .map(this::convertToEmploymentView)
            .collect(Collectors.toList());
    }

    private EmploymentView convertToEmploymentView(FindEmploymentResult result) {
        return new EmploymentView(
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
}

