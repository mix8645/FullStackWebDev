package com.example.student.service;

import com.example.student.entity.FacultyEntity;
import com.example.student.reposity.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    // Method to retrieve all faculty entities
    public List<FacultyEntity> getFacultyAll() {
        return facultyRepository.findAll();
    }

    // Method to retrieve a faculty entity by its ID
    public FacultyEntity getFacultyById(Integer facultyId) {
        Optional<FacultyEntity> faculty = facultyRepository.findById(facultyId);
        if (faculty.isPresent()) {
            return faculty.get();
        }
        return null;
    }

    // Method to save a faculty entity
    public FacultyEntity saveFaculty(FacultyEntity facultyEntity) {
        FacultyEntity faculty = facultyRepository.save(facultyEntity);
        return faculty;
    }

    // Method to delete a faculty entity by its ID
    public void deleteFacultyById(Integer facultyId) {
        facultyRepository.deleteById(facultyId);
    }
}
