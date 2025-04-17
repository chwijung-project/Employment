package com.chwimong.project.education.persistence.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "commercial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommercialEntity {

	@Id
	private String id;
	private String provider;
	private String title;
	private String summary;
	private String category;
	private String type;
	private String logo;
	private String url;
}
