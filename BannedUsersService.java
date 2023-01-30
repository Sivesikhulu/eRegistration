package com.ECESWA.eRegistration.service;

import com.ECESWA.eRegistration.entity.BannedUsers;

import java.util.List;

public interface BannedUsersService {

    List<BannedUsers> getAllBannedUsers();

    BannedUsers getBannedUsersById(Integer UserID);
    BannedUsers editBannedUsers(BannedUsers bannedUsers);

    public void deleteBannedUsers(BannedUsers bannedUsers);

}
