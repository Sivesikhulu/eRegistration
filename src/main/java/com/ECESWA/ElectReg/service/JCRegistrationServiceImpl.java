package com.ECESWA.ElectReg.service;

import com.ECESWA.ElectReg.dao.JCRegistrationRepository;
import com.ECESWA.ElectReg.entity.JCRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class JCRegistrationServiceImpl implements JCRegistrationService{


    private JCRegistrationRepository jCRegistrationRepository;
    @Autowired
    public JCRegistrationServiceImpl(JCRegistrationRepository theJCRegistrationRepository){
        jCRegistrationRepository=theJCRegistrationRepository;
    }

    @Override
    public List<JCRegistration> findAll() {
        return jCRegistrationRepository.findAllByOrderBySurname();
    }

    @Override
    public JCRegistration findById(int theId) {
        Optional<JCRegistration> result=jCRegistrationRepository.findById(theId);
        JCRegistration theJCRegistration=null;

        if (result.isPresent()) {
            theJCRegistration = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find candidate - " + theId);
        }

        return theJCRegistration;
    }

    @Override
    public void save(JCRegistration theJCRegistration) {
        jCRegistrationRepository.save(theJCRegistration);
    }

    @Override
    public void deleteById(int theId) {
        jCRegistrationRepository.deleteById(theId);
    }

    @Override
    public List<JCRegistration> findByCentreOrderBySurname(int theCentre) {
        return jCRegistrationRepository.findByCentreOrderBySurname(theCentre);
    }

}
