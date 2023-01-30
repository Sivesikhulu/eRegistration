package com.ECESWA.eRegistration.service.Impl;

import com.ECESWA.eRegistration.entity.BannedUsers;
import com.ECESWA.eRegistration.repository.BannedUsersRepository;
import com.ECESWA.eRegistration.service.BannedUsersService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BannedUsersServiceImpl implements BannedUsersService {
    private BannedUsersRepository bannedUsersRepository;

    public BannedUsersServiceImpl(BannedUsersRepository bannedUsersRepository) {
        this.bannedUsersRepository = bannedUsersRepository;
    }

    @Override
    public List<BannedUsers> getAllBannedUsers(){

        return bannedUsersRepository.findAll();
    }

    @Override
    public BannedUsers getBannedUsersById(Integer UserID) {
        return bannedUsersRepository.findById(UserID).get();
    }

    @Override
    public BannedUsers editBannedUsers(BannedUsers bannedUsers) {
        return bannedUsersRepository.save(bannedUsers);
    }
    public void deleteBannedUsers(BannedUsers bannedUsers){
        bannedUsersRepository.delete(bannedUsers);
    }
}
