package com.chwimong.project.education.usecase;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface ProjectFindUseCase { 

	List<FindProjectResult> getProjects();
	
	@Getter
	@ToString
	@Builder
	class FindProjectResult {
		private String id;
		private String projectName;
		private String teamName;
		private String description;
		private List<String> mainfunction; 
		private List<String> category; 
		private String notionUrl; 
		private Duration duration; 
		private List<String> industry;
		private String image;
		
		@Getter
		@ToString
		@Builder
		public static class Duration {
			private String start;
			private String end;
		}
	}
}
