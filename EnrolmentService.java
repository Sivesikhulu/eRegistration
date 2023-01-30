package com.ECESWA.eRegistration.service;

import com.ECESWA.eRegistration.entity.Enrolment;

import java.util.List;

public interface EnrolmentService {
    List<Enrolment> getAllEnrolments();

    Enrolment getEnrolmentById(Integer enrolmentID);
    Enrolment editEnrolment(Enrolment enrolment);

    public void deleteEnrolment(Enrolment enrolment);
}
