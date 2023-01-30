package com.ECESWA.eRegistration.service;

import com.ECESWA.eRegistration.entity.Level;

import java.util.List;

public interface LevelService {

    List<Level> getAllLevels();

    Level getLevelById(Integer levelCode);
    Level editLevel(Level level);

    public void deleteLevel(Level level);
}
