package in.shiva.service;

import java.util.List;

import in.shiva.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllmployee();

	public boolean saveEmployee(Employee employee);

	public Employee getEmployeeById(Integer id);

	public void deleteEmployeeById(Integer id);

	public void updateEmployee(Employee employee);

}
