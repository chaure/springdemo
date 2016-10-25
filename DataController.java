package com.beingjavaguys.controller;



import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beingjavaguys.domain.Employee;
import com.beingjavaguys.services.DataService;

@Controller
public class DataController {
	
	@Autowired
	DataService dataService;
	
	@RequestMapping("form")
	
	public ModelAndView getForm(@ModelAttribute Employee employee) {
		// System.out.println("Your logic executed successfully....");
		return new ModelAndView("form");
	}
	
	@RequestMapping("register")
	public ModelAndView registerUser(@ModelAttribute Employee employee) {
		System.out.println(employee.getFirstName());
		if(employee.getFirstName().isEmpty()||employee.getEmail().isEmpty()||employee.getLastName().isEmpty()||employee.getPhone().isEmpty())
		{
			System.out.println("please enter all details");
			//return new ModelAndView("form");
			ModelAndView model = new ModelAndView("form");
			model.addObject("msg", "please fill all the detail");
			
			return model;
		}
		else
		{
		dataService.insertRow(employee);
		return new ModelAndView("redirect:list");
		
	}
	}
	
	@RequestMapping("list")
	public ModelAndView getList() {
		List<?> employeeList = dataService.getList();
		return new ModelAndView("list","employeeList",employeeList);
	}
	
	@RequestMapping("delete")
	public ModelAndView deleteUser(@RequestParam int id) {
		dataService.deleteRow(id);
		return new ModelAndView("redirect:list");
	}
	
	@RequestMapping("edit")
	public ModelAndView editUser(@RequestParam int id,@ModelAttribute Employee employee) {
		Employee employeeObject = dataService.getRowById(id);
		return new ModelAndView("edit","employeeObject",employeeObject);
	}
	
	@RequestMapping("update")
	public ModelAndView updateUser(@ModelAttribute Employee employee) {
		
		
		System.out.println("id at controller"+employee.getId());
		System.out.println("name at contrler"+employee.getFirstName());
		dataService.updateRow(employee);
		return new ModelAndView("redirect:list");
	}

}
