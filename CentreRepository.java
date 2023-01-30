package com.ECESWA.eRegistration.repository;

import com.ECESWA.eRegistration.entity.Centre;
import com.ECESWA.eRegistration.entityPk.CentrePk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CentreRepository extends JpaRepository<Centre, CentrePk> {

}
