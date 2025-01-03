package in.shiva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shiva.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
