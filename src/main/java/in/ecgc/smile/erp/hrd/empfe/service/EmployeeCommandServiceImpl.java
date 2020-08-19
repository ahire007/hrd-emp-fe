package in.ecgc.smile.erp.hrd.empfe.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.hrd.empfe.dto.Employee;
import in.ecgc.smile.erp.hrd.empfe.exception.ErrorResponse;
import in.ecgc.smile.erp.hrd.empfe.proxy.EmployeeClient;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeCommandServiceImpl.class);

	@Autowired
	private EmployeeClient employeeClient;

	@Override
	public boolean addEmployee(Employee employee) {
		try {
			System.out.println("add called");
			return employeeClient.addEmployee(employee);
			}catch (ResponseStatusException ex) {	//Exception can be handled here or in Controller
				LOGGER.error("Got error:{}",ex.getReason());
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

	@Override
	public boolean deleteEmployeeByEmpId(String id) {
		try {
			return employeeClient.deleteEmployee(id);
			}catch (ResponseStatusException ex) {	//Exception can be handled here or in Controller
				LOGGER.error("Got error:{}",ex.getReason());
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
