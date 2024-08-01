package com.jwtauthdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.jwtauthdemo.Model.Employee;

@Service
public class EmployeeService {
	
	private List<Employee> emplist = new ArrayList<>();
	
	public EmployeeService() {
		emplist.add(new Employee(UUID.randomUUID().toString(),"rushikesh@gmail.com","rushi"));
		emplist.add(new Employee(UUID.randomUUID().toString(),"rakesh@gmail.com","rushikesh"));

		
		
	}
	
	public List<Employee> getEmployeesList(){
		return emplist;
	}

}
