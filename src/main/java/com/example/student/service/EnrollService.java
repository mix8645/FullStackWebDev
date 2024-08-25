package com.example.student.service;

import com.example.student.entity.EnrollEntity;
import com.example.student.reposity.EnrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollService {

    @Autowired
    private EnrollRepository enrollRepository;

    // Method to retrieve all enroll entities
    public List<EnrollEntity> getEnrollAll() {
        return enrollRepository.findAll();
    }

    // Method to retrieve a enroll entity by its ID
    public EnrollEntity getEnrollById(Integer enrollId) {
        Optional<EnrollEntity> enroll = enrollRepository.findById(enrollId);
        if (enroll.isPresent()) {
            return enroll.get();
        }
        return null;
    }

    // Method to save a enroll entity
    public EnrollEntity saveEnroll(EnrollEntity enrollEntity) {
        EnrollEntity enroll = enrollRepository.save(enrollEntity);
        return enroll;
    }

    // Method to delete a enroll entity by its ID
    public void deleteEnrollById(Integer enrollId) {
        enrollRepository.deleteById(enrollId);
    }
}
