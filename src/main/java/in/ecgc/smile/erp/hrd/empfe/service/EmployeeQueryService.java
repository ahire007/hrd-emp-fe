package in.ecgc.smile.erp.hrd.empfe.service;

import java.util.List;

import in.ecgc.smile.erp.hrd.empfe.dto.Employee;



public interface EmployeeQueryService {

	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeByEmpId(String empId);
	
	public boolean isEmployeeExist(String empId);
}
