package com.ECESWA.eRegistration.service.Impl;

import com.ECESWA.eRegistration.entity.Enrolment;
import com.ECESWA.eRegistration.repository.EnrolmentRepository;
import com.ECESWA.eRegistration.service.EnrolmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolmentServiceImpl implements EnrolmentService {
    private EnrolmentRepository enrolmentRepository;

    public EnrolmentServiceImpl(EnrolmentRepository enrolmentRepository) {
        this.enrolmentRepository = enrolmentRepository;
    }

    @Override
    public List<Enrolment> getAllEnrolments(){

        return enrolmentRepository.findAll();
    }

    @Override
    public Enrolment getEnrolmentById(Integer enrolmentID) {
        return enrolmentRepository.findById(enrolmentID).get();
    }

    @Override
    public Enrolment editEnrolment(Enrolment enrolment) {
        return enrolmentRepository.save(enrolment);
    }
    public void deleteEnrolment(Enrolment enrolment){
        enrolmentRepository.delete(enrolment);
    }
}
