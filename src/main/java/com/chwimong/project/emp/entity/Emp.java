package com.chwimong.project.emp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "employment")
@Getter
@Setter
@Data
public class Emp {

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
    private String field21;
    private String field22;
	
    public Emp() {
		super();
	}

	public Emp(String id, String recruit, String company, String region, String tag, String intro, String job,
			String main, String require, String thanks, String give, String url, String common, String occasional,
			String startDate, String endDate, String canApply, String closed, String crawlingDate, String crawlingTxt,
			String crawlingSite, String logo, String field21, String field22) {
		super();
		this.id = id;
		this.recruit = recruit;
		this.company = company;
		this.region = region;
		this.tag = tag;
		this.intro = intro;
		this.job = job;
		this.main = main;
		this.require = require;
		this.thanks = thanks;
		this.give = give;
		this.url = url;
		this.common = common;
		this.occasional = occasional;
		this.startDate = startDate;
		this.endDate = endDate;
		this.canApply = canApply;
		this.closed = closed;
		this.crawlingDate = crawlingDate;
		this.crawlingTxt = crawlingTxt;
		this.crawlingSite = crawlingSite;
		this.logo = logo;
		this.field21 = field21;
		this.field22 = field22;
	}
	
	
}

