package com.chwimong.project.employment.usecase;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface FilterUseCase {

	List<UnFilteredEmploymentsResult> findUnFilteredEmployments();

	@Getter
    @ToString
    @Builder
	class UnFilteredEmploymentsResult {
		private String id;
	    private String main;
	    private String require;
	    private String thanks;
	}
}
