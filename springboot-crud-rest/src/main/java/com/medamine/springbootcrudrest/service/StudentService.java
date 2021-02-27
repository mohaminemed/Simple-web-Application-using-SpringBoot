package com.medamine.springbootcrudrest.service;

import com.medamine.springbootcrudrest.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Optional<Student> findById(long idStudent);
    void save(Student student);
    void deleteById(Long idStudent);
}
