package com.ECESWA.ElectReg.service;

import com.ECESWA.ElectReg.dao.EGCSERegistrationRepository;
import com.ECESWA.ElectReg.entity.EGCSERegistration;
import com.ECESWA.ElectReg.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
public class EGCSERegistrationServiceImpl implements EGCSERegistrationService{
    private EGCSERegistrationRepository egcseRegistrationRepository;
    @Autowired
    public EGCSERegistrationServiceImpl(EGCSERegistrationRepository theEgcseRegistrationRepository) {
        this.egcseRegistrationRepository = theEgcseRegistrationRepository;
    }

    @Override
    public List<EGCSERegistration> findAll() {
        return egcseRegistrationRepository.findAllByOrderBySurname();
    }

    @Override
    public EGCSERegistration findById(int theId) {

        Optional<EGCSERegistration> result=egcseRegistrationRepository.findById(theId);
        EGCSERegistration theEgcseRegistration=null;

        if (result.isPresent()) {
            theEgcseRegistration = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find candidate - " + theId);
        }

        return theEgcseRegistration;


    }

    @Override
    public void save(EGCSERegistration theEgcseRegistration) {
        egcseRegistrationRepository.save(theEgcseRegistration);
    }

    @Override
    public void deleteById(int theId) {
        egcseRegistrationRepository.deleteById(theId);
    }

    @Override
    public List<EGCSERegistration> findByCentreOrderBySurname(int theCentre) {
        return egcseRegistrationRepository.findByCentreOrderBySurname(theCentre);
    }


}
