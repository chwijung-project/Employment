package com.chwimong.project.employment.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chwimong.project.employment.ui.view.ApiResponseView;
import com.chwimong.project.employment.ui.view.client.MentorpickListView;
import com.chwimong.project.employment.usecase.MentorpickFindUseCase;

import io.swagger.v3.oas.annotations.Operation;
import com.chwimong.project.employment.exception.MessageType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/mentorpick")

@Tag(name = "멘토추천 API", description= "취업 능력 향상을 위한 멘토추천  API")
public class MentorpickController {
    private final MentorpickFindUseCase mentorpickFindUseCase;

    @Autowired
    public MentorpickController(MentorpickFindUseCase  mentorpickFindUseCase) {
        this.mentorpickFindUseCase = mentorpickFindUseCase;
    }

    @GetMapping("")
    @Operation(summary = "멘토가 추천하는 능력", description = "멘토가 추천하는 취업하려면 필요한 능력")
    public ResponseEntity<ApiResponseView<MentorpickListView>> getEmployments() {

        log.info("[MentorpickController] getMentorpick 멘토추천 능력 조회 요청");
        var result = mentorpickFindUseCase.getMentorpick();
        MentorpickListView view = new MentorpickListView(result.stream().toList());
        ApiResponseView<MentorpickListView> response = ApiResponseView.of(MessageType.OK, view);
        
        return ResponseEntity.ok(response);
    }
}
    		
    
