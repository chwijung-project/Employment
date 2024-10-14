package com.chwimong.project.employment.persisntence.mongo.repository;

import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentEntityRepository extends MongoRepository<EmploymentEntity, String>, EmploymentRepositoryCustom {

}
