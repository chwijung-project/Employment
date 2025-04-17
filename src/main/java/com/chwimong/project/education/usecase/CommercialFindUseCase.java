package com.chwimong.project.education.usecase;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface CommercialFindUseCase {
	
	List<FindCommercialResult> getCommercials();
	
	@Getter
	@ToString
	@Builder
	class FindCommercialResult {
		private String id;
		private String provider;
		private String title;
		private String summary;
		private String category;
		private String type;
		private String logo;
		private String url;
	}
}
