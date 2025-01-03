package in.shiva.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import in.shiva.model.Employee;
import in.shiva.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		List<Employee> empList = employeeService.getAllmployee();
		model.addAttribute("employees", empList);
		return "employees";
	}

	@GetMapping("/employee/new")
	public String getEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute(employee);
		return "emp-reg-form";
	}

	@PostMapping("/add-employee")
	public ModelAndView addEmployee(Employee employee) {
		ModelAndView mav = new ModelAndView();
		Boolean isSaved = employeeService.saveEmployee(employee);
		if (isSaved) {
			mav.addObject("msg", "Employee Record saved successfully !");
		} else {
			mav.addObject("msg", "Employee Record faild to save !");
		}
		mav.setViewName("emp-reg-form");
		return mav;
	}

	@PostMapping("/employee/delete/{empId}")
	public ModelAndView deleteEmployee(@PathVariable Integer empId) {
		ModelAndView mav = new ModelAndView();
		employeeService.deleteEmployeeById(empId);
		List<Employee> empList = employeeService.getAllmployee();
		mav.addObject("employees", empList);
		mav.setViewName("employees");
		return mav;
	}

	@GetMapping("/employee/edit/{empId}")
	public String showEmployeeEditForm(@PathVariable("empId") Integer empId, Model model) {
		Employee employee = employeeService.getEmployeeById(empId);
		model.addAttribute("employee", employee);
		return "edit-employee";
	}

	@PostMapping("/employee/{empId}")
	public String updateEmployee(@PathVariable("empId") Integer empId, @ModelAttribute("employee") Employee employee,
			Model model) {
		employee.setEmpId(empId);
		employeeService.updateEmployee(employee);
		model.addAttribute("msg", "Employee record updated successfully !");
		return "redirect:/employees";
	}

}
