package com.chwimong.project.employment.ui.view.client;

import java.util.List;

import com.chwimong.project.employment.persisntence.mongo.entity.MentorpickEntity;
import com.chwimong.project.employment.usecase.MentorpickFindUseCase.FindMentorpickResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MentorpickListView {
    private List<Mentorpick> mentorpickList;

    public MentorpickListView(List<FindMentorpickResult> results) {
        this.mentorpickList = results.stream()
            .map(this::convertToMentorpick)
            .toList();
    }

    private Mentorpick convertToMentorpick(FindMentorpickResult result) {
        return new Mentorpick(
            result.getRole(),
            result.getJobtitle(),
            result.getKeywords()
        );
    }
    @Getter
    @AllArgsConstructor
    public static class Mentorpick {
        private String role;
        private String jobtitle;
        private List<String> keywords;
    }

}