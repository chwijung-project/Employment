package com.chwimong.project.education.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Project { 
	private final String id;
    private final String projectName;
    private final String teamName;
    private final String description;
    private final List<String> mainfunction;
    private final List<String> category;
    private final String notionUrl;
    private final Duration duration;
    private final List<String> industry;
    private final String image;
	
    @Getter
    @ToString
    @Builder
    public static class Duration {
        private final String start;
        private final String end;
    }
}
