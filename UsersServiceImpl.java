package com.ECESWA.eRegistration.service.Impl;

import com.ECESWA.eRegistration.entity.Users;
import com.ECESWA.eRegistration.repository.UsersRepository;
import com.ECESWA.eRegistration.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> getAllUsers(){

        return usersRepository.findAll();
    }

}
