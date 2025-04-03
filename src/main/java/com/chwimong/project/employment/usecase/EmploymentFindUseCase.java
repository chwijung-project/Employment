package com.chwimong.project.employment.usecase;

import java.util.List;

import org.springframework.data.domain.Page;

import com.chwimong.project.employment.ui.common.Criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public interface EmploymentFindUseCase {

    Page<FindEmploymentResult> getEmployments(Criteria cri, EmploymentFindQuery query);
    List<FindEmploymentResult> getEmploymentsWithCategory(EmploymentWithCategoryQuery query);
    List<FindEmploymentResult> getEmploymentsWithKeyword(EmploymentWithKeywordQuery query);
    
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    class EmploymentWithCategoryQuery {
        String jobtitle;
    }
    
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    class EmploymentWithKeywordQuery {
        String keyword;
    }

    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    class EmploymentFindQuery {

    	String id;
    	String job;
    	String region;
    	String sort;
    	String offset;
    	String searchValue;
    }

    @Getter
    @ToString
    @Builder
    class FindEmploymentResult {
    	private String id;
    	private String recruit;
        private String company;
        private String region;
        private String job;
        private String url;
        private String endDate;
        private Boolean closed;
        private String crawlingDate;
        private String logo;
        private String main;
        private String require;
        private String thanks;
        private String fullTxt;
        private String keyword;
    }
}
