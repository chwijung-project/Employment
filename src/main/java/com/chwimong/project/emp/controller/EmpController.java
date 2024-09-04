package com.chwimong.project.emp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chwimong.project.emp.entity.Emp;
import com.chwimong.project.emp.service.EmpService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/v1")
public class EmpController {

	private static final Logger log = LoggerFactory.getLogger(EmpController.class);
	private final EmpService empService;
	
	public EmpController(EmpService empService) {
		this.empService = empService;
		
	};
	
	@Operation(summary = "채용정보 조회 테스트", description = "채용정보 조회가 처리됩니다.", tags = { "EmpController" })
	@GetMapping("/empList")
	public List<Emp> getAllEmployment() {
		
		log.info("[EmpController] getAllEmployment start ===========]");
		
		List<Emp> response = empService.getAllEmployment();
		
		log.info("[EmpController] getAllEmployment end ===========]");
		
		return response;
		
	}
}
