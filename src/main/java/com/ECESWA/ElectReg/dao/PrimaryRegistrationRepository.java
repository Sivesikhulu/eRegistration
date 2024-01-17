package com.ECESWA.ElectReg.dao;

import com.ECESWA.ElectReg.entity.PrimaryRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrimaryRegistrationRepository extends JpaRepository<PrimaryRegistration, Integer> {

    public List<PrimaryRegistration> findAllByOrderBySurname();

    public List<PrimaryRegistration> findByCentreOrderBySurname(int theCentre);

    public  List<PrimaryRegistration> findByCentreIsNull();
    public void deleteByCentre(int theCentre);

    //public List<PrimaryRegistration> findUnregisteredCentresEPC();
}
