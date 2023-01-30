package com.ECESWA.eRegistration.service.Impl;

import com.ECESWA.eRegistration.entity.UserLevel;
import com.ECESWA.eRegistration.repository.UserLevelRepository;
import com.ECESWA.eRegistration.service.UserLevelService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserLevelServiceImpl implements UserLevelService {
        private UserLevelRepository userLevelRepository;

        public UserLevelServiceImpl(UserLevelRepository userLevelRepository) {
            this.userLevelRepository = userLevelRepository;
        }

        @Override
        public List<UserLevel> getAllUserLevels(){

            return userLevelRepository.findAll();
        }

        @Override
        public UserLevel getUserLevelById(Integer userLevelCode) {
            return userLevelRepository.findById(userLevelCode).get();
        }

        @Override
        public UserLevel editUserLevel(UserLevel userLevel) {
            return userLevelRepository.save(userLevel);
        }
        public void deleteUserLevel(UserLevel userLevel){
            userLevelRepository.delete(userLevel);
        }
}
