package com.ECESWA.eRegistration.controller;

import com.ECESWA.eRegistration.entity.Subject;
import com.ECESWA.eRegistration.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {

    }
    @GetMapping("/subject")
    public String listSubject(Model model){
        return "subject";
    }

    @GetMapping("/getSubjects")
    public ResponseEntity<List<Subject>> getSubject()
    {
        return new ResponseEntity<List<Subject>>(subjectRepository.findAll(), HttpStatus.OK);
    }
}
