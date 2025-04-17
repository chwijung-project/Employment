package com.chwimong.project.employment.persisntence.mongo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "mentorpick")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorpickEntity {

	@Id
    private String id;
	private String role;
    private String jobtitle;
    private List<String> keywords;
    
}
