package com.ECESWA.ElectReg.service;

import com.ECESWA.ElectReg.entity.EGCSERegistration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public interface EGCSERegistrationService {


    List<EGCSERegistration> findAll();
    EGCSERegistration findById(int theId);
    void save (EGCSERegistration theEgcseRegistration);
    void deleteById (int theId);

    List<EGCSERegistration> findByCentreOrderBySurname(int theCentre);

}
