package com.ECESWA.ElectReg.dao;

import com.ECESWA.ElectReg.entity.EGCSERegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EGCSERegistrationRepository extends JpaRepository<EGCSERegistration, Integer> {


    public List<EGCSERegistration> findAllByOrderBySurname();

    List<EGCSERegistration> findByCentreOrderBySurname(int theCentre);
}
