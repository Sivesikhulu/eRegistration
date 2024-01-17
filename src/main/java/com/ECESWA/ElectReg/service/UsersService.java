package com.ECESWA.ElectReg.service;

import com.ECESWA.ElectReg.entity.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {


    List<Users> findAll();

    Users findById(String theId);

    void save(Users theUsers);

    void deleteById(String theId);

    void saveUsersFromExcel(MultipartFile file);

    public List<Users> findByCertificateLevel(String level);

    //Users findByUsername(String theUsername);
}