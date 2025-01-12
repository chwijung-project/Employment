package com.chwimong.project.employment.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.chwimong.project.employment.persisntence.mongo.entity.EmploymentEntity;
import com.chwimong.project.employment.persisntence.mongo.repository.FilterRepository;
import com.chwimong.project.employment.usecase.FilterUseCase;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Slf4j
@Service
public class FilterService implements FilterUseCase {

	private final FilterRepository filterRepository;
	private final WebClient webClient;

    @Autowired
    public FilterService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
        this.webClient = WebClient.builder()
            .baseUrl("http://15.165.76.130:8000")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
    
    @Override
    public FilterResult findUnFilteredEmployments() {
	   List<EmploymentEntity> unfiltered = 
		   filterRepository.findByJobtitleFilterEquals("");

	   int totalCount = unfiltered.size();
	   
	   if (totalCount == 0) {
	       return new FilterResult(totalCount, 0, true);
	   }
	   
       List<UnFilteredEmploymentsResult> results = unfiltered.stream()
           .map(this::convertToUnFilteredResult)
           .collect(Collectors.toList());
       
       boolean isSuccess = sendToFilterSystem(results);
       
       return new FilterResult(totalCount, isSuccess ? totalCount : 0, isSuccess);
	   
	}

	private UnFilteredEmploymentsResult convertToUnFilteredResult(EmploymentEntity entity) {
	   return UnFilteredEmploymentsResult.builder()
	       .id(entity.getId())
	       .main(entity.getMain())
	       .require(entity.getRequire())
	       .thanks(entity.getThanks())
	       .build();
	}

	private boolean sendToFilterSystem(List<UnFilteredEmploymentsResult> results) {
		
	   try {
           webClient.post()
               .uri("/api/v1/receive-jobs")
               .bodyValue(results)
               .retrieve()
               .bodyToMono(Void.class)
               .timeout(Duration.ofSeconds(5))
               .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
            		   .filter(e -> !(e instanceof TimeoutException)))
               .doOnSuccess(v -> log.info("전송 성공: {} 건", results.size()))
               .doOnError(e -> log.error("전송 실패: {}", e.getMessage()))
               .doOnError(e -> log.error("전송 실패 상세: {}", e))
               .onErrorResume(e -> {
                   log.error("HTTP Status: {}", 
                       e instanceof WebClientResponseException ? 
                       ((WebClientResponseException) e).getStatusCode() : "Unknown");
                   return Mono.error(e);
               })
               .block();
           
           return true;
           
       } catch (Exception e) {
    	   log.error("전송 실패: {}", e.getMessage());
    	   return false;
       }
	}
}
