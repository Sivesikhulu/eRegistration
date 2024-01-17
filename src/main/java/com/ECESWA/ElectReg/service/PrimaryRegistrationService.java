package com.ECESWA.ElectReg.service;

import com.ECESWA.ElectReg.entity.PrimaryRegistration;
import com.ECESWA.ElectReg.entity.Users;

import java.util.List;

public interface PrimaryRegistrationService  {
    List<PrimaryRegistration> findAll();

    //List<PrimaryRegistration> findByCentreIsNull(List<Users> allUsers);

    PrimaryRegistration findById(int theId);
    void save (PrimaryRegistration thePrimaryRegistration);
    void deleteById (int theId);

    List<PrimaryRegistration> findByCentreOrderBySurname(int theCentre);

    void deleteByCentre(int theCentre);

    //public List<PrimaryRegistration> findByCertificateLevel(String epc);
}
