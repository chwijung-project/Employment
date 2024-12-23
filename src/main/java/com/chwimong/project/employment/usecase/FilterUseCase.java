package com.chwimong.project.employment.usecase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface FilterUseCase {

	FilterResult findUnFilteredEmployments();
	
	@Getter
    @ToString
    @Builder
	class UnFilteredEmploymentsResult {
		private String id;
	    private String main;
	    private String require;
	    private String thanks;
	}
	
	@Getter
    @AllArgsConstructor
    class FilterResult {
        private final int totalCount;
        private final int successCount;
        private final boolean isSuccess;
    }
}
