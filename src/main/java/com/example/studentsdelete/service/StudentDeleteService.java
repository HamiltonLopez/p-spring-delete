package com.example.studentsdelete.service;

import com.example.studentsdelete.model.Student;
import com.example.studentsdelete.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class StudentDeleteService {

    private final StudentRepository repository;

    public StudentDeleteService(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public boolean deleteStudent(Long id) {
        Optional<Student> student = repository.findById(id);
        if (student.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
} 