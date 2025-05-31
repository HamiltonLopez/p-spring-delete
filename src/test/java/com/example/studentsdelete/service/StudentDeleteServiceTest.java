package com.example.studentsdelete.service;

import com.example.studentsdelete.model.Student;
import com.example.studentsdelete.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentDeleteServiceTest {

    @Mock
    private StudentRepository repository;

    private StudentDeleteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new StudentDeleteService(repository);
    }

    @Test
    void deleteStudent_Success() {
        // Arrange
        Long id = 1L;
        Student student = new Student("Test Name", "test@email.com", 20);
        when(repository.findById(id)).thenReturn(Optional.of(student));
        doNothing().when(repository).deleteById(id);

        // Act
        boolean result = service.deleteStudent(id);

        // Assert
        assertTrue(result);
        verify(repository).findById(id);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteStudent_NotFound() {
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean result = service.deleteStudent(id);

        // Assert
        assertFalse(result);
        verify(repository).findById(id);
        verify(repository, never()).deleteById(anyLong());
    }
} 