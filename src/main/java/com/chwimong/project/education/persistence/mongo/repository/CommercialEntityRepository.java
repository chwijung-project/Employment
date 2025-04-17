package com.chwimong.project.education.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chwimong.project.education.persistence.mongo.entity.CommercialEntity;

@Repository
public interface CommercialEntityRepository extends MongoRepository<CommercialEntity, String> {

}
