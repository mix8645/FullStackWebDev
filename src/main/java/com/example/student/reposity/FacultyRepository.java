package com.example.student.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entity.FacultyEntity;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Integer>{

} 
