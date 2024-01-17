package com.ECESWA.ElectReg.dao;

import com.ECESWA.ElectReg.entity.JCRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JCRegistrationRepository extends JpaRepository<JCRegistration, Integer> {

    public List<JCRegistration> findAllByOrderBySurname();

    List<JCRegistration> findByCentreOrderBySurname(int theCentre);
}
