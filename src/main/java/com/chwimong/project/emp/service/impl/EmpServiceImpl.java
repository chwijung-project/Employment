package com.chwimong.project.emp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chwimong.project.emp.entity.Emp;
import com.chwimong.project.emp.repository.EmpRepo;
import com.chwimong.project.emp.service.EmpService;

@Service(value = "empService")
public class EmpServiceImpl implements EmpService {
	
	private static final Logger log = LoggerFactory.getLogger(EmpServiceImpl.class);
	private final EmpRepo empRepo;
	
	public EmpServiceImpl(EmpRepo empRepo) {
		this.empRepo = empRepo;
	}
	
	@Override
	public List<Emp> getAllEmployment() {
		
		log.info("[EmpServiceImpl] getAllEmployment start ===========]");
		
		List<Emp> employmentList = empRepo.findAll();
		
		log.info("[EmpServiceImpl] getAllEmployment end ===========]");
		
		return employmentList;
	}

}
