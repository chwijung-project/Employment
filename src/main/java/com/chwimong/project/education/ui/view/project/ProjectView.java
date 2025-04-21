package com.chwimong.project.education.ui.view.project;

import java.util.List;

import com.chwimong.project.education.usecase.ProjectFindUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ProjectView {
   private String id;
   private String projectName;
   private String teamName;
   private String description;
   private List<String> mainfunction;
   private List<String> category;
   private String notionUrl;
   private Duration duration;
   private List<String> industry;
   private String imange;
   
   public ProjectView(ProjectFindUseCase.FindProjectResult result) {
       this.id = result.getId();
       this.projectName = result.getProjectName();
       this.teamName = result.getTeamName();
       this.description = result.getDescription();
       this.mainfunction = result.getMainfunction();
       this.category = result.getCategory();
       this.notionUrl = result.getNotionUrl();
       this.duration = Duration.builder()
           .start(result.getDuration().getStart())
           .end(result.getDuration().getEnd())
           .build();
       this.industry = result.getIndustry();
       this.imange = result.getImage();
   }
   
   @Getter
   @ToString
   @Builder
   public static class Duration {
       private String start;
       private String end;
   }
}
