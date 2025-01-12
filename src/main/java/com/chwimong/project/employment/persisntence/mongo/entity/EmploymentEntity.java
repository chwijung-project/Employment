package com.chwimong.project.employment.persisntence.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "employment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentEntity {

	@Id
    private String id;
	private String recruit;
    private String company;
    private String region;
    private String tag;
    private String intro;
    private String job;
    private String main;
    private String require;
    private String thanks;
    private String give;
    private String url;
    private String common;
    private String occasional;
    private String startDate;
    private String endDate;
    private String canApply;
    private String closed;
    private String crawlingDate;
    private String crawlingTxt;
    private String crawlingSite;
    private String logo;
    @Field("jobtitle_filter")
    private String jobtitleFilter;
}
