package com.chwimong.project.emp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chwimong.project.emp.entity.Emp;

@Repository
public interface EmpRepo extends MongoRepository<Emp, String> {

}
