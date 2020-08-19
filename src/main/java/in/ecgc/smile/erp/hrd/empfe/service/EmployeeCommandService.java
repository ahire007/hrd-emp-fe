package in.ecgc.smile.erp.hrd.empfe.service;

import in.ecgc.smile.erp.hrd.empfe.dto.Employee;

public interface EmployeeCommandService {
	
	public boolean addEmployee(Employee employee);

	public boolean deleteEmployeeByEmpId(String id);
}
