package com.chwimong.project.employment.persisntence.mongo.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        final Query mongoQuery = Query.query(createSearchCriteria(query)).with(pageable);
        
        return new PageImpl<>(
            mongoTemplate.find(mongoQuery, EmploymentEntity.class),
            pageable,
            mongoTemplate.count(mongoQuery.skip(-1).limit(-1), EmploymentEntity.class)
        );
    }

    private Criteria createSearchCriteria(EmploymentFindQuery query) {
        Criteria criteria = new Criteria();
        
        if (query.getClosed() != null) {
            criteria = query.getClosed()
                ? Criteria.where("closed").ne("")
                : Criteria.where("closed").is("");
        }
        
        // 추가될 파라미터만 명시
        
        return criteria;
    }
}







