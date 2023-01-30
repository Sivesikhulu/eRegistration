package com.ECESWA.eRegistration.service.Impl;

import com.ECESWA.eRegistration.entity.Subject;
import com.ECESWA.eRegistration.repository.SubjectRepository;
import com.ECESWA.eRegistration.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAllSubjects(){

        return subjectRepository.findAll();
    }
}
