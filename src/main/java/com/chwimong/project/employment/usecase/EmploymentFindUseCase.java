package com.chwimong.project.employment.usecase;

import lombok.*;

import java.util.List;

public interface EmploymentFindUseCase {
    List<FindEmploymentResult> getEmployments();
    FindEmploymentResult getEmployment(EmploymentFindQuery query);

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    @ToString
    class EmploymentFindQuery {
        String id;

        public EmploymentFindQuery(String id) {
            this.id = id;
        }
    }

    @Getter
    @ToString
    @Builder
    class FindEmploymentResult {

    }
}
