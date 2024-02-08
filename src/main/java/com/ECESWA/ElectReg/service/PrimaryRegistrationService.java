package com.ECESWA.ElectReg.service;

import com.ECESWA.ElectReg.entity.PrimaryRegistration;
import com.ECESWA.ElectReg.entity.Users;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
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
