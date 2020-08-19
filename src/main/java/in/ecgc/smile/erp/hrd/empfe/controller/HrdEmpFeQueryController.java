package in.ecgc.smile.erp.hrd.empfe.controller;

import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;

import in.ecgc.smile.erp.hrd.empfe.dto.Employee;
import in.ecgc.smile.erp.hrd.empfe.proxy.EmployeeClientHystrixFallback;
import in.ecgc.smile.erp.hrd.empfe.service.EmployeeQueryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *Employee FE Controller class
 *
 *@version 1.2 15-May-20
 *@Author Architecture Team C-DAC Mumbai
 **/
@Controller
@RequestMapping("/")
public class HrdEmpFeQueryController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(HrdEmpFeQueryController.class);

	
	@Autowired
	private EmployeeQueryService empQueryService;
	@Autowired
	private EmployeeClientHystrixFallback emp;
	String err=null;

	@GetMapping(value= {"/","Dashboard"})
	public String dashboard() {
		LOGGER.info("--Dashboard--");
		return "view/dashboard";
	}
	 /**
     * Returns Employees
	 * @param <T>
     *
     * @param EmpID			Employee Employee Id
     * @param FirstName     Employee First Name
     * @param Designation	Employee Designation
     * @param Employee_type Employee Type
     * @return
	 * @throws JsonProcessingException 
     */
	@GetMapping(value = "/employees")
	//@HystrixCommand(fallbackMethod ="fallbackempException")
	public String getAllEmployees(Model model,Locale locale) {
		LOGGER.debug("-->listOfEmployees");
		List<Employee> listOfEmployees=emp.getAllEmployees();
		LOGGER.info("-->listOfEmployees:{}",listOfEmployees);
		model.addAttribute("listEmployee",listOfEmployees);
		return "view/allEmployees";
	}
	
	@GetMapping(value = "/employee/create")
	public String createEmployee(Locale locale, Model model) {		
		LOGGER.info("--Employee Creation--");
		model.addAttribute("employee", new Employee());
		return "view/employeeProfileCreation";
	}
	
	@GetMapping(value = "/employee-exist/{id}")
	@ResponseBody
	public boolean isEemployeeExst(@PathVariable("id") String empId,Locale locale, Model model) {		
		LOGGER.debug("--Is Employee Exist by ID:{}",empId);
		return empQueryService.isEmployeeExist(empId)?true:false;
	}
	
}














 
  
 