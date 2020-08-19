
package in.ecgc.smile.erp.hrd.empfe.proxy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import feign.hystrix.FallbackFactory;
import in.ecgc.smile.erp.hrd.empfe.dto.Employee;


/**
 *Employee client fallback
 *
 *@version 1.2 15-May-20
 *@Author Architecture Team C-DAC Mumbai
 *
 **/


@Component
public class EmployeeClientFallback implements FallbackFactory<EmployeeClient> {

	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeClientFallback.class);

		@Override 
		public EmployeeClient create(Throwable cause) { 
			
		return new EmployeeClient() {

			@Override
		   
			public List<Employee> getAllEmployees() {
				LOGGER.error("Get List of Employee Service is down:{}",cause.getMessage());
				return null;
			}
			
			
			@Override
			public boolean addEmployee(Employee employee) {
				LOGGER.error("Add Employee Service is down:{}",cause.getMessage());
				return false;
			}

			@Override
			public boolean deleteEmployee(String id) {
				LOGGER.error("Delete Employee Service is down:{}",cause.getMessage());
				return false;
			}

			@Override
			public Employee getEmployeeByEmpId(String id) {
				LOGGER.error("Get Employee By EmpId Service is down:{}",cause.getMessage());
				return null;
			}

			@Override
			public boolean isEmployeeExist(String empId) {
				LOGGER.error("Is Employee Exist Service is down:{}",cause.getMessage());
				return false;
			}

		};

	}

}
