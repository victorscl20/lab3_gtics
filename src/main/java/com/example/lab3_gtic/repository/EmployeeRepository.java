package com.example.lab3_gtic.repository;

import com.example.lab3_gtic.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByFirstName(String employeeFirstName);
    List<Employee> findAllByLastName(String employeeLastName);

    @Query(value="select e.first_name, e.last_name, h.start_date, h.end_date,job_title from employees e, jobs j, job_history h where e.job_id=j.job_id and h.job_id=j.job_id and e.salary>1500;", nativeQuery = true)
    Employee listaReportesEmployee();

    @Query(value="select e.first_name, e.last_name, j.job_title, d.department_name, e.hire_date \n" +
            "from employees e, jobs j, departments d \n" +
            "where e.department_id = d.department_id \n" +
            "\t\tand e.job_id = j.job_id \n" +
            "order by e.first_name ASC;", nativeQuery = true)
    List<Employee> historialEmployees();
}
