package com.ECESWA.eRegistration.service;

import com.ECESWA.eRegistration.entity.UserLevel;

import java.util.List;

public interface UserLevelService {
    List<UserLevel> getAllUserLevels();

    UserLevel getUserLevelById(Integer userLevelCode);
    UserLevel editUserLevel(UserLevel userLevel);

    public void deleteUserLevel(UserLevel userLevel);
}
