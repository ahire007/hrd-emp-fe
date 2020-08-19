package in.ecgc.smile.erp.hrd.empfe.controller;

import java.util.Locale;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import in.ecgc.smile.erp.hrd.empfe.dto.Employee;
import in.ecgc.smile.erp.hrd.empfe.service.EmployeeCommandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/**
 *Employee FE Controller class
 *
 *@version 1.2 15-May-20
 *@Author Architecture Team C-DAC Mumbai
 **/
@Controller
@RequestMapping("/")
public class HrdEmpFeCommandController {

	private static final Logger LOGGER=LoggerFactory.getLogger(HrdEmpFeCommandController.class);

	@Autowired
	private EmployeeCommandService empCommandService;
	
	String err=null;
   
	/**
	   * Save Employee Data into Employee Master
	   * @param employee   EmployeeInput employee-object
	   * @return 
	*/
	@PostMapping(value = "/employee")
	public String saveEmployeeData(@ModelAttribute("employee") @Valid Employee employee,BindingResult errors, Locale locale,Model model){
		LOGGER.info("Save Employee:{}",employee);
		
		if(errors.hasErrors()) {
			model.addAttribute("employee", employee);
			return "view/employeeProfileCreation";
		}
		boolean result=empCommandService.addEmployee(employee);
        System.out.println(result);
        if(result) {
        	return "redirect:/employees";
        }else {
        	err="Not Added";
        	model.addAttribute("employee", employee);
    		model.addAttribute("err", err);
    		return "view/employeeProfileCreation";
        }
	}
	
	/**
	 * Method for delete employee
	 * @PathVariable empid
	 * @return integer
	 * */
	@GetMapping(value = "/employee/{id}")
	public String deleteEmployeeData(@PathVariable("id") String empId,Locale locale,Model model) {
		LOGGER.debug("Deleting Employee by Emp Id:{}",empId);
		boolean result =empCommandService.deleteEmployeeByEmpId(empId);
		if(result) {
			err="Successfully deleted";
			model.addAttribute("err", err);
			return "redirect:/employees";	
		}
		err="Failed to delete Employee";
		return "redirect:/hrd";
	}
	

	
}














 
  
 