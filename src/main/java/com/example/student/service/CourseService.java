package com.example.student.service;

import com.example.student.entity.CourseEntity;
import com.example.student.reposity.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Method to retrieve all course entities
    public List<CourseEntity> getCourseAll() {
        return courseRepository.findAll();
    }

    // Method to retrieve a course entity by its ID
    public CourseEntity getCourseById(Integer courseId) {
        Optional<CourseEntity> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            return course.get();
        }
        return null;
    }

    // Method to save a course entity
    public CourseEntity saveCourse(CourseEntity courseEntity) {
        CourseEntity course = courseRepository.save(courseEntity);
        return course;
    }

    // Method to delete a course entity by its ID
    public void deleteCourseById(Integer courseId) {
        courseRepository.deleteById(courseId);
    }
}
