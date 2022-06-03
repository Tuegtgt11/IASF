package com.example.t2010ahellospring.controller;

import com.example.t2010ahellospring.entity.Student;
import com.example.t2010ahellospring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
*  Http://localhost:8080/api/v1/students  | GET     | return list student
*  Http://localhost:8080/api/v1/students  | POST    | create new student
*  Http://localhost:8080/api/v1/students  | DELETE  | remove student
*  Http://localhost:8080/api/v1/students  | GET     | find student by id
*  Http://localhost:8080/api/v1/students  | PUT     | update student
* */

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getList() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Student> optionalStudent = studentService.findById(id);
        if (!optionalStudent.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalStudent.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> create(@RequestBody Student product) {
        return ResponseEntity.ok(studentService.save(product));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> optionalStudent = studentService.findById(id);
        if (!optionalStudent.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Student existStudent = optionalStudent.get();
        // map object
        existStudent.setFullName(student.getFullName());
        existStudent.setPhone(student.getPhone());
        existStudent.setEmail(student.getEmail());
        existStudent.setAddress(student.getAddress());
        existStudent.setNote(student.getNote());
        return ResponseEntity.ok(studentService.save(existStudent));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!studentService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

