package com.ECESWA.eRegistration.repository;

import com.ECESWA.eRegistration.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {


}
