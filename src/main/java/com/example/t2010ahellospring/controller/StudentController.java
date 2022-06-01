package com.example.t2010ahellospring.controller;

import com.example.t2010ahellospring.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/*
*  Http://localhost:8080/api/v1/students  | GET     | return list student
*  Http://localhost:8080/api/v1/students  | POST    | create new student
*  Http://localhost:8080/api/v1/students  | DELETE  | remove student
*  Http://localhost:8080/api/v1/students  | GET     | find student by id
*  Http://localhost:8080/api/v1/students  | PUT     | update student
* */


@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    static List<Student> list = new ArrayList<>();
    {
        list.add(Student.builder().rollNumber("10001a").fullName("Vu Duc Tue").build());
        list.add(Student.builder().rollNumber("10002a").fullName("NDT BADBOY").build());
        list.add(Student.builder().rollNumber("10003a").fullName("Robert Vu").build());
        list.add(Student.builder().rollNumber("10004a").fullName("Jakk Vu").build());
    }

    //CRUD
    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll(){
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, path ="{id}")
    public Student findById(@PathVariable String id) {
        int foundIndex = -1;
        for (int i = 0; i <= list.size(); i++) {
            if (list.get(i).getRollNumber().equals(id)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return null;
        }
        return list.get(foundIndex);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Student save(@RequestBody Student student) {
        list.add(student);
        return student;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public Student delete(@PathVariable String id) {
        int foundIndex = -1;
        for (int i = 0; i <= list.size(); i++) {
            if (list.remove(i).getRollNumber().equals(id)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return null;
        }
        return list.remove(foundIndex);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Student edit(@PathVariable String id,@RequestBody Student student) {
        int foundIndex = -1;
        for (int i = 0; i <= list.size(); i++) {
            if (list.get(i).getRollNumber().equals(id)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return null;
        }
        Student existingStudent = list.get(foundIndex);
        student.setFullName(existingStudent.getFullName());
        student.setAddress(existingStudent.getAddress());
        student.setEmail(existingStudent.getEmail());
        student.setPhone(existingStudent.getPhone());
        student.setDob(existingStudent.getDob());

        return student;
    }
}
