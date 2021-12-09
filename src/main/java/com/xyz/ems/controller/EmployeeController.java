package com.xyz.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xyz.ems.entity.Employee;
import com.xyz.ems.exception.EmployeeNotFoundException;
import com.xyz.ems.service.IEmployeeService;

@Controller
@RequestMapping("/emp")
//@ResponseBody
public class EmployeeController {
    
	@Autowired
	private IEmployeeService empService;
	
	@GetMapping("/register" )
	public String register() {
		return "emp";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Employee emp , Model model) {
		Integer id = empService.save(emp);
		String message = "Register success with ID: ("+id+")";
		model.addAttribute("message", message);
		
		return "emp";
	}
	
	@GetMapping("/all")	
	public String getAllEmployees(Model model) {
		List<Employee> list = empService.getAllEmployees();
		model.addAttribute("list", list);
		
		return "EmployeeData";
	}

   /* @GetMapping("/edit")
	*public String getOneEmployee(@RequestParam(value = "id" ,required = true) Integer eid , Model model) {
	*	Employee e = empService.getOneEmployee(eid);
	*	model.addAttribute("e", e);
		
	*	return "EmployeeEdit";
	*}
    */
    
	@GetMapping("/edit")
	public String getOneEmployee(@RequestParam(value = "id", required = true) Integer id, Model model , RedirectAttributes attributes) {
		String page = null;
		try {
			Employee emp = empService.getOneEmployee(id);
			model.addAttribute("emp", emp);
			page = "EmployeeEdit";
		}
		catch(EmployeeNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
			
		}
		//return "page";
		return page;
	}
	
	
    
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee emp , RedirectAttributes attributes) {
    	empService.update(emp);
    	attributes.addAttribute("message", "Employee '"+emp.getId()+"' Updated");
    	return "redirect:all";
    	
    }
    
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam(value = "id" , required = true) Integer id, RedirectAttributes attributes) {
    	try {
    	empService.delete(id);
    	attributes.addAttribute("message" , "Employee deatails deleted with Id :" +id);
    	}
    	catch(EmployeeNotFoundException e) {
    		e.printStackTrace();
    		attributes.addAttribute("message" ,e.getMessage());
    	}
    	
    	return "redirect:all";
    }
    
} 
	

