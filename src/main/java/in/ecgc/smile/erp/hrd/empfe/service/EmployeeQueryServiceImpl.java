package in.ecgc.smile.erp.hrd.empfe.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import in.ecgc.smile.erp.hrd.empfe.dto.Employee;
import in.ecgc.smile.erp.hrd.empfe.exception.ErrorResponse;
import in.ecgc.smile.erp.hrd.empfe.proxy.EmployeeClient;


/**
*Employee Query Service Implementation 
*used for calling BE Microservice
*
*@version 1.2 15-May-20
*@Author Architecture Team C-DAC Mumbai
*
**/
@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService{
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeQueryServiceImpl.class);

	@Autowired
	private EmployeeClient employeeClient;
	
	@Override
	@HystrixCommand
	public List<Employee> getAllEmployees() {
		try {
		return employeeClient.getAllEmployees();
		}catch (ResponseStatusException ex) {	//Exception can be handled here or in Controller
			log.error("Got error"+ex.getReason());
			try {
				//Extract ErrorResponse from Exception
				ErrorResponse errorResponse = new ObjectMapper().readValue(ex.getReason(), ErrorResponse.class);
				System.out.println(errorResponse);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public Employee getEmployeeByEmpId(String empId) {
		try {
			
			return employeeClient.getEmployeeByEmpId(empId);	//Call FeignClient Proxy
			
		} catch (ResponseStatusException ex) {	//Exception can be handled here or in Controller
			log.error("Got error"+ex.getReason());
			try {
				//Extract ErrorResponse from Exception
				ErrorResponse errorResponse = new ObjectMapper().readValue(ex.getReason(), ErrorResponse.class);
				
				System.out.println(errorResponse);
				
				//throw ex;	//Throw if need to be handled in Controller again
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean isEmployeeExist(String empId) {
		try {
			
			return employeeClient.isEmployeeExist(empId);	//Call FeignClient Proxy
			
		} catch (ResponseStatusException ex) {	//Exception can be handled here or in Controller
			log.error("Got error"+ex.getReason());
			try {
				//Extract ErrorResponse from Exception
				ErrorResponse errorResponse = new ObjectMapper().readValue(ex.getReason(), ErrorResponse.class);
				
				System.out.println(errorResponse);
				
				//throw ex;	//Throw if need to be handled in Controller again
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
}
