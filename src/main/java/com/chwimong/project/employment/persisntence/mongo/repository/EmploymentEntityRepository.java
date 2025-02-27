package com.chwimong.project.employment.persisntence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.EmploymentGroupedQuery;

@Repository
public interface EmploymentEntityRepository extends MongoRepository<EmploymentEntity, String>, EmploymentRepositoryCustom {

	List<EmploymentEntity> findByJobtitleFilterEquals(String string);

	@Aggregation(pipeline = {
        "{ $group: { _id: { crawlingDate: '$crawlingDate', filteredJobtitle: '$filteredJobtitle' }, count: { $sum: 1 } } }",
        "{ $project: { _id: 0, crawlingDate: '$_id.crawlingDate', filteredJobtitle: '$_id.filteredJobtitle', count: 1, month: { $toInt: { $substr: [ '$_id.crawlingDate', 5, 2 ] } } } }",
        "{ $sort: { crawlingDate: 1 } }"
    })
    List<EmploymentGroupedQuery> findGroupedEmploymentData();
}
