package com.example.student.service;

import com.example.student.entity.StudentEntity;
import com.example.student.reposity.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Method to retrieve all student entities
    public List<StudentEntity> getStudentAll() {
        return studentRepository.findAll();
    }

    // Method to retrieve a student entity by its ID
    public StudentEntity getStudentById(Integer studentId) {
        Optional<StudentEntity> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            return student.get();
        }
        return null;
    }

    // Method to save a student entity
    public StudentEntity saveStudent(StudentEntity studentEntity) {
        StudentEntity student = studentRepository.save(studentEntity);
        return student;
    }

    // Method to delete a student entity by its ID
    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
