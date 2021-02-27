package com.medamine.springbootcrudrest.controller;

import com.medamine.springbootcrudrest.model.Student;
import com.medamine.springbootcrudrest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("students/{idStudent}")
    public ResponseEntity findById(@PathVariable Long idStudent) {
        Optional<Student> studentOptional = studentService.findById(idStudent);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return ResponseEntity.ok(student);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("students")
    public ResponseEntity<Void> add(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("students/{idStudent}")
    public ResponseEntity<Void> update(@PathVariable long idStudent, @RequestBody Student student) {
        Optional<Student> studentOptional = studentService.findById(idStudent);
        if (studentOptional.isPresent()) {
            Student std = studentOptional.get();
            student.setId(std.getId());
            studentService.save(student);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("students/{idStudent}")
    public ResponseEntity<Void> deleteById(@PathVariable long idStudent) {
        Optional<Student> studentOptional = studentService.findById(idStudent);
        if (studentOptional.isPresent()) {
            studentService.deleteById(idStudent);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
