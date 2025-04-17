package com.chwimong.project.education.ui.view.project;

import java.util.List;
import java.util.stream.Collectors;

import com.chwimong.project.education.usecase.ProjectFindUseCase.FindProjectResult;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProjectListView {

	@JsonValue
	private List<ProjectView> projectViewList;
	
	public ProjectListView(List<FindProjectResult> results) {
		this.projectViewList = results.stream()
			.map(ProjectView::new)
			.collect(Collectors.toList());
	}
}
