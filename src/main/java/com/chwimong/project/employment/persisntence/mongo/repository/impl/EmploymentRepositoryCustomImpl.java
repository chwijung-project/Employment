package com.chwimong.project.employment.persisntence.mongo.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import com.chwimong.project.employment.persisntence.mongo.repository.EmploymentRepositoryCustom;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.EmploymentFindQuery;

public class EmploymentRepositoryCustomImpl implements EmploymentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public EmploymentRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<EmploymentEntity> getEmployments(Pageable pageable, EmploymentFindQuery query) {
        Query mongoQuery = new Query().with(pageable);
        
        if ("true".equals(query.getClosed())) {
            mongoQuery.addCriteria(new Criteria().orOperator(
                Criteria.where("closed").isNull(),
                Criteria.where("closed").is("")
            ));
        }

        List<EmploymentEntity> content = mongoTemplate.find(mongoQuery, EmploymentEntity.class);

        return PageableExecutionUtils.getPage(content, pageable,
                () -> mongoTemplate.count(Query.of(mongoQuery).limit(-1).skip(-1), EmploymentEntity.class));
    }
}