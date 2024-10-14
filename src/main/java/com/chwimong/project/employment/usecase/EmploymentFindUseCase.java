package com.chwimong.project.employment.usecase;

import lombok.*;

import java.util.List;

import com.chwimong.project.config.Criteria;

public interface EmploymentFindUseCase {
	
    List<FindEmploymentResult> getEmployments(Criteria cri, EmploymentFindQuery query);
    int getEmploymentsSize();
    
    FindEmploymentResult getEmployment(EmploymentFindQuery query);
    
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    @Setter
    @ToString
    class EmploymentFindQuery {
        
    	String id;
    	String job;
    	String region;
    	String sort;
    	String closed;
    	String offset;

        public EmploymentFindQuery(String id, String job, String region, String sort, String closed, String offset) {
            this.id = id;
            this.job = job;
            this.region = region;
            this.sort = sort;
            this.closed = closed;
            this.offset = offset;
        }
        
        public String getOffset() {
            return (offset == null || offset.isEmpty()) ? "1" : offset;
        }
        
        public String getSort() {
            return (sort == null || sort.isEmpty()) ? "latest_order" : sort;
        }
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
        private String closed;
        private String crawlingDate;
        private String logo;
    }
}