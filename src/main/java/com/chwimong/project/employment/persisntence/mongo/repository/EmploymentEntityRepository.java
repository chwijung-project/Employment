package com.chwimong.project.employment.persisntence.mongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.EmploymentGroupedQuery;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.EmploymentKeywordMapQuery;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase.EmploymentKeywordTrendQuery;

@Repository
public interface EmploymentEntityRepository extends MongoRepository<EmploymentEntity, String>, EmploymentRepositoryCustom {

	List<EmploymentEntity> findByFilteredJobtitleEquals(String string);

	List<EmploymentEntity> findByKeywordEquals(String keyword);

	@Aggregation(pipeline = {
        "{ $group: { _id: { crawlingDate: '$crawlingDate', filteredJobtitle: '$filteredJobtitle' }, count: { $sum: 1 } } }",
        "{ $project: { _id: 0, crawlingDate: '$_id.crawlingDate', filteredJobtitle: '$_id.filteredJobtitle', count: 1, month: { $toInt: { $substr: [ '$_id.crawlingDate', 5, 2 ] } } } }",
        "{ $sort: { crawlingDate: 1 } }"
    })
    List<EmploymentGroupedQuery> findGroupedEmploymentData();

    @Query(value = "{}", fields = "{ keyword: 1, _id: 0 }")
    List<EmploymentKeywordTrendQuery> findEmploymentSteadyKeywordTrend();

    @Query(value = "{ 'crawlingDate': { $gte: ?0 } }", fields = "{ keyword: 1, _id: 0 }")
    List<EmploymentKeywordTrendQuery> findEmploymentHotKeywordTrend(Date threeMonthsAgo);

    @Aggregation(pipeline = {
        "{ $match: { keyword: { $exists: true, $ne: [] } } }",
        "{ $unwind: '$keyword' }",
        "{ $group: { _id: '$filteredJobtitle', keywords: { $addToSet: '$keyword' } } }",
        "{ $project: { " +
            "_id: 0, " +
            "jobtitle: '$_id', " +
            "keywords: { $sortArray: { input: '$keywords', sortBy: 1 } } " +
        "} }"
    })
    List<EmploymentKeywordMapQuery> findEmploymentKeywordMapByJobtitle();



}
