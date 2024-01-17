package com.ECESWA.ElectReg.service;
import com.ECESWA.ElectReg.entity.JCRegistration;

import java.util.List;

public interface JCRegistrationService {

    List<JCRegistration> findAll();
    JCRegistration findById(int theId);
    void save (JCRegistration theJCRegistration);
    void deleteById (int theId);

    List<JCRegistration> findByCentreOrderBySurname(int theCentre);
}
