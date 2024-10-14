package com.chwimong.project.employment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Employment {
    private final String id;
    private final String company;
    private final String startDate;
    private final String closed;
    private final String job;
    private final String give;
    private final String endDate;
    private final String main;
    private final String thanks;
    private final String url;
    private final String occasional;
    private final String recruit;
    private final String logo;
    private final String require;
    private final String intro;
    private final String common;
    private final String region;
    private final String canApply;
}
