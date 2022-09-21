package com.example.lab3_gtic.repository;

import com.example.lab3_gtic.entity.Job;
import com.example.lab3_gtic.entity.SalarioDto;
import com.example.lab3_gtic.entity.SalarioxDptoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Double> {

    @Query(value = "select j.job_title as 'jobtitle', MAX(e.salary) as 'max', MIN(e.salary) as 'min', TRUNCATE(sum(e.salary),2) as 'suma', TRUNCATE(avg(e.salary),2) as 'promedio' from hr.employees e \n" +
            "inner join jobs j on (j.job_id = e.job_id)\n" +
            "group by  j.job_id;",
            nativeQuery = true)
    List<SalarioDto> obtenerMySalarioDto();

    @Query(value = "select de.department_name  as 'departamento', count(e.department_id) as 'empleados', \n" +
            "TRUNCATE(sum(e.salary),2) as 'suma', TRUNCATE(avg(e.salary),2) as 'promedio' from hr.employees e \n" +
            "inner join jobs j on (j.job_id = e.job_id)\n" +
            "inner join job_history jh on (jh.department_id = e.department_id)\n" +
            "left join departments de on (jh.department_id =de.department_id)\n" +
            "group by  e.department_id\n" +
            "ORDER BY count(e.department_id) DESC;",
            nativeQuery = true)
    List<SalarioxDptoDto> obtenerMySalarioxDptoDto();
}