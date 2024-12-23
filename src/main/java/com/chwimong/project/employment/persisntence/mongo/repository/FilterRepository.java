package com.chwimong.project.employment.persisntence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;

@Repository
public interface FilterRepository extends MongoRepository<EmploymentEntity, String> {

	List<EmploymentEntity> findByJobtitleFilterEquals(String string);
}
