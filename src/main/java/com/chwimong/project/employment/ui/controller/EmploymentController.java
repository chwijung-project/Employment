package com.chwimong.project.employment.ui.controller;


import com.chwimong.project.employment.ui.view.EmploymentListView;
import com.chwimong.project.employment.ui.view.EmploymentView;
import com.chwimong.project.employment.usecase.EmploymentFindUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/employments")
public class EmploymentController {
    private final EmploymentFindUseCase employmentFindUseCase;

    @Autowired
    public EmploymentController(EmploymentFindUseCase employmentFindUseCase) {
        this.employmentFindUseCase = employmentFindUseCase;
    }

    @GetMapping("")
    public ResponseEntity<EmploymentListView> getEmployments() {
        //TODO: input param needs
        //TODO: service call

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmploymentView> getEmployment(@PathVariable String id) {

        return ResponseEntity.ok().build();
    }
}
