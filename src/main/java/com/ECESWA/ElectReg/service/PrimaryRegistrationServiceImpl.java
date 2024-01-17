package com.ECESWA.ElectReg.service;

import com.ECESWA.ElectReg.dao.PrimaryRegistrationRepository;
import com.ECESWA.ElectReg.entity.PrimaryRegistration;
import com.ECESWA.ElectReg.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PrimaryRegistrationServiceImpl implements  PrimaryRegistrationService{

    private PrimaryRegistrationRepository primaryRegistrationRepository;
    @Autowired
    public PrimaryRegistrationServiceImpl(PrimaryRegistrationRepository thePrimaryRegistrationRepository){
        primaryRegistrationRepository=thePrimaryRegistrationRepository;
    }

    @Override
    public List<PrimaryRegistration> findAll() {
        return primaryRegistrationRepository.findAllByOrderBySurname();
    }

 /* @Override
    public List<PrimaryRegistration> findByCentreIsNull(List<String> allUsers) {
        return null;
    }*/

    /*@Query(value = "SELECT * FROM primaryRegistration p WHERE NOT EXISTS (SELECT * FROM users u WHERE u.centre = p.centre AND u.certificateLevel = 'EPC')", nativeQuery = true)
    @Override
    public List<PrimaryRegistration> findByCentreIsNull(List<Users> allUsers) {
        return primaryRegistrationRepository.findByCentreIsNull();
    }*/


    @Override
    public PrimaryRegistration findById(int theId) {
        Optional <PrimaryRegistration> result=primaryRegistrationRepository.findById(theId);
        PrimaryRegistration thePrimaryRegistration=null;

        if (result.isPresent()) {
            thePrimaryRegistration = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find candidate - " + theId);
        }

        return thePrimaryRegistration;
    }

    @Override
    public void save(PrimaryRegistration thePrimaryRegistration) {
        primaryRegistrationRepository.save(thePrimaryRegistration);
    }

    @Override
    public void deleteById(int theId) {
        primaryRegistrationRepository.deleteById(theId);
    }

    @Override
    public List<PrimaryRegistration> findByCentreOrderBySurname(int theCentre) {
        return primaryRegistrationRepository.findByCentreOrderBySurname(theCentre);
    }

    @Override
    public void deleteByCentre(int theCentre) {
        primaryRegistrationRepository.deleteByCentre(theCentre);
    }


}
