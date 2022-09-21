package com.example.lab3_gtic.repository;

import com.example.lab3_gtic.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
