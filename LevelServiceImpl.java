package com.ECESWA.eRegistration.service.Impl;

import com.ECESWA.eRegistration.entity.Level;
import com.ECESWA.eRegistration.repository.LevelRepository;
import com.ECESWA.eRegistration.service.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LevelServiceImpl implements LevelService {

    private LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public List<Level> getAllLevels(){

        return levelRepository.findAll();
    }

    @Override
    public Level getLevelById(Integer levelCode) {
        return levelRepository.findById(levelCode).get();
    }

    @Override
    public Level editLevel(Level level) {
        return levelRepository.save(level);
    }
    public void deleteLevel(Level level){
        levelRepository.delete(level);
    }
}
