package in.ecgc.smile.erp.hrd.empfe.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import in.ecgc.smile.erp.hrd.empfe.dto.Employee;

/**
 *Employee client interface
 *used for calling BE Microservice
 *
 *@version 1.2 15-May-20
 *@Author Architecture Team C-DAC Mumbai
 *
 **/
//@FeignClient(name = "hrd-emp-be",fallbackFactory = EmployeeClientFallback.class,configuration = FeignErrorDecoder.class)
@FeignClient(name = "hrd-emp-be")
//@RibbonClient(name="hrd-emp-be")
public interface EmployeeClient {
	
	//Get all Employee Data : From BE Service	
	@GetMapping("/api/employees")
	public  List<Employee> getAllEmployees();

	//Save Employee Data :through BE Service
	@PostMapping("/api/employee")
	public boolean addEmployee(Employee employee);
	
	//Delete Employee Data : through BE Service
	@DeleteMapping("/api/employee/{id}")
	public boolean deleteEmployee(@PathVariable("id") String id);
	
	//Get Employee By Emp Id:throgh BE Service 
	@GetMapping("/api/employee/{id}")
	public Employee getEmployeeByEmpId(@PathVariable("id") String id);
	
	@GetMapping("/api/employee/{id}")
	public boolean isEmployeeExist(@PathVariable("id") String empId);
}