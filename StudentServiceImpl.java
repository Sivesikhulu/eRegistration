package com.ECESWA.eRegistration.service.Impl;

import com.ECESWA.eRegistration.entity.Student;
import com.ECESWA.eRegistration.repository.StudentRepository;
import com.ECESWA.eRegistration.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents(){

        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer studentID) {
        return studentRepository.findById(studentID).get();
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }
    @Override
    public void deleteStudentById(Integer id){
        studentRepository.deleteById(id);
    }
    @Override
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
}
