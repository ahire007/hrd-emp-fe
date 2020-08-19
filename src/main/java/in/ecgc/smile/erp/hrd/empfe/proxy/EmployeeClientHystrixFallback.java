package in.ecgc.smile.erp.hrd.empfe.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ecgc.smile.erp.hrd.empfe.dto.Employee;
@EnableFeignClients(basePackageClasses = EmployeeClient.class)
@ComponentScan(basePackageClasses = EmployeeClient.class)
@Component
public class EmployeeClientHystrixFallback implements EmployeeClient {


	private EmployeeClient emp;
	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeClientHystrixFallback.class);
	@Autowired
	public EmployeeClientHystrixFallback(EmployeeClient emp) {
		
		this.emp = emp;
	}
	
	@Override
	@HystrixCommand(fallbackMethod = "getAlltheEmployees",commandProperties = {
	        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000"),
	        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
	        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "300000")})
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return emp.getAllEmployees();
	}

	@Override
	@HystrixCommand(fallbackMethod ="addtheEmployee")
	public boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@HystrixCommand(fallbackMethod = "deletetheEmployee")
	public boolean deleteEmployee(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@HystrixCommand(fallbackMethod = "gettheEmployeeByEmpId")
	public Employee getEmployeeByEmpId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@HystrixCommand(fallbackMethod = "istheEmployeeExist")
	public boolean isEmployeeExist(String empId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Employee> getAlltheEmployees() {
		// TODO Auto-generated method stub
		LOGGER.error("Get List of Employee Service is down:{}");
		return emp.getAllEmployees();
	}

	
	public boolean addtheEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean deletetheEmployee(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Employee gettheEmployeeByEmpId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean istheEmployeeExist(String empId) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
