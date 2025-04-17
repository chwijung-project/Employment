package com.chwimong.project.education.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chwimong.project.education.persistence.mongo.entity.ProjectEntity;

@Repository
public interface ProjectEntityRepository extends MongoRepository<ProjectEntity, String> {

}
