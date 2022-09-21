package com.example.lab3_gtic.repository;

import com.example.lab3_gtic.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
