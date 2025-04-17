package com.chwimong.project.employment.usecase;
import jakarta.annotation.Generated;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;


public interface MentorpickFindUseCase {

    List<FindMentorpickResult> getMentorpick();

    @Getter
    @ToString
    @Builder
    class FindMentorpickResult{
        private String id;
        private String role;
        private String jobtitle;
        private List<String> keywords;
    }
}
