package com.ECESWA.eRegistration.service;

import com.ECESWA.eRegistration.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(Integer studentID);

    Student editStudent(Student student);

    void deleteStudentById(Integer id);

    Student saveStudent(Student student);
}
