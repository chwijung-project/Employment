package com.chwimong.project.education.persistence.mongo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity { 

    @Id
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
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Duration {
        private String start;
        private String end;
    }
}
