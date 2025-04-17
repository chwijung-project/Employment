package com.chwimong.project.education.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Commercial {
	private final String id;
	private final String provider;
	private final String title;
	private final String summary;
	private final String category;
	private final String type;
	private final String logo;
	private final String url;
}
