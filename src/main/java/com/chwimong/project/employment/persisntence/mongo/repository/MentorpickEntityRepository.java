package com.chwimong.project.employment.persisntence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chwimong.project.employment.persisntence.mongo.entity.MentorpickEntity;


@Repository
public interface MentorpickEntityRepository extends MongoRepository<MentorpickEntity, String>{
    List<MentorpickEntity> findAll();
}
