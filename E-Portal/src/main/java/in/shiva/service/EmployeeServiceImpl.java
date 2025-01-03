package in.shiva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shiva.model.Employee;
import in.shiva.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmailService mailService ;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllmployee() {
		List<Employee> empList = employeeRepository.findAll();
		return empList;
	}

	@Override
	public boolean saveEmployee(Employee employee) {
		Employee isSaved = employeeRepository.save(employee);
		if(isSaved.getEmpId()!=null) {
			String subject="Employee Registration";
			String body="Hello "+isSaved.getEmpName().toUpperCase()+" , "+ "Your EMS registration is successful !"+
			
					"""
					\nThanks For joining us!
					
					Regards
					Shivanshu Shukla
					EMS founder
					""";
			String to = isSaved.getEmpEmail();
			mailService.simpleMailSender(subject, body, to);
		}
		return isSaved.getEmpId()!=null;
		
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		Employee existedEmployee = employeeRepository.findById(id).get();
		return existedEmployee;
	}

	@Override
	public void deleteEmployeeById(Integer id) {
		employeeRepository.deleteById(id);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}
