package com.ECESWA.ElectReg.dao;

import com.ECESWA.ElectReg.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {
    @Override
    Optional<Users> findById(String s);


    List<Users> findByCertificateLevel(String level);
}
