package com.chwimong.project.employment.usecase;

import lombok.*;

import java.util.List;

import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import com.chwimong.project.employment.ui.common.Criteria;

public interface EmploymentFindUseCase {

    List<FindEmploymentResult> getEmployments(Criteria cri, EmploymentFindQuery query);
    List<FindEmploymentResult> getEmploymentsWithCategory(EmploymentWithCategoryQuery query);
    int getEmploymentsSize(); //???

    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    class EmploymentWithCategoryQuery {
        String category;

    }

    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    class EmploymentFindQuery {

    	String id;
    	String job;
    	String region;
    	String sort;
    	Boolean closed;
    	String offset;
    }

    @Getter
    @ToString
    @Builder
    class FindEmploymentResult {
    	private String recruit;
        private String company;
        private String region;
        private String job;
        private String url;
        private String endDate;
        private Boolean closed;
        private String crawlingDate;
        private String logo;
    }
}
