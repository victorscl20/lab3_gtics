package com.example.lab3_gtic.repository;

import com.example.lab3_gtic.entity.Employee;
import com.example.lab3_gtic.entity.HistoryDto;
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

    @Query(value ="select distinct(concat(e.first_name,\" \",e.last_name)) as Nombre_completo, j.job_title as Puesto, \n" +
            " d.department_name as Departamento,\n" +
            " concat(jf.first_name,\" \",jf.last_name) as Jefe,\n" +
            " jh.start_date as Fecha_inicio,\n" +
            " jh.end_date as Fecha_fin\n" +
            " from employees e\n" +
            "left join jobs j on (e.job_id = j.job_id)\n" +
            "left join departments d on (e.department_id=d.department_id)\n" +
            "left join employees jf on (e.manager_id=jf.employee_id)\n" +
            "left join job_history jh on (jh.employee_id=e.employee_id)\n" +
            "group by e.employee_id\n" +
            "order by jh.start_date;",nativeQuery = true)
    List<HistoryDto> obtenerHistory();
}
