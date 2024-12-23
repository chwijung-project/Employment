package com.chwimong.project.employment.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chwimong.project.employment.exception.MessageType;
import com.chwimong.project.employment.ui.view.ApiResponseView;
import com.chwimong.project.employment.ui.view.FilterResponse;
import com.chwimong.project.employment.usecase.FilterUseCase;
import com.chwimong.project.employment.usecase.FilterUseCase.FilterResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v2/filterSystem")
public class FilterController {
   private final FilterUseCase filterUseCase;
   
   @Autowired
   public FilterController(FilterUseCase filterUseCase) {
       this.filterUseCase = filterUseCase;
   }
   
   @GetMapping("/jobs")
   public ResponseEntity<ApiResponseView<FilterResponse>> requestUnFilteredRenew() {
       try {
           FilterResult result = filterUseCase.findUnFilteredEmployments();
           
           return ResponseEntity.ok(ApiResponseView.of(
    		   result.isSuccess() ? MessageType.OK : MessageType.INTERNAL_SERVER_ERROR,
               new FilterResponse(
                   result.getTotalCount(),
                   result.getSuccessCount()
               )
           ));
           
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(ApiResponseView.of(
                   MessageType.INTERNAL_SERVER_ERROR,
                   new FilterResponse(0, 0)
               ));
       }
   }
}
