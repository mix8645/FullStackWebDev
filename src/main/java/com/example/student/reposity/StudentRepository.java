package com.example.student.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

} 
