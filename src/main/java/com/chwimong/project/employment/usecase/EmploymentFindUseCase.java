package com.chwimong.project.employment.usecase;

import com.chwimong.project.employment.dto.EmploymentDTO;
import lombok.*;

import java.util.List;

public interface EmploymentFindUseCase {
    List<EmploymentDTO> getEmployments(); //TODO: FindQuery 를 Param 으로
    EmploymentDTO getEmployment(EmploymentFindQuery query);

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
