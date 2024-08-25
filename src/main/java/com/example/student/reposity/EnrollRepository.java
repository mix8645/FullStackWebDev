package com.example.student.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entity.EnrollEntity;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Integer>{

} 
