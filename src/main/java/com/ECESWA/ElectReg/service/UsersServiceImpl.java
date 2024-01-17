package com.ECESWA.ElectReg.service;

import com.ECESWA.ElectReg.dao.UsersRepository;
import com.ECESWA.ElectReg.entity.Users;
import com.ECESWA.ElectReg.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {
    private UsersRepository usersRepository;
    @Autowired
    public UsersServiceImpl(UsersRepository theUsersRepository){
        usersRepository=theUsersRepository;
    }
    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(String theId) {
        Optional<Users> result=usersRepository.findById(theId);
        Users theUsers=null;
        if (result.isPresent()){
            theUsers=result.get();
        }
        else {
            throw new RuntimeException("Did not find the user - "+theId);
        }
        return theUsers;
    }


    @Override
    public void save(Users theUsers) {
        usersRepository.save(theUsers);
    }

    @Override
    public void deleteById(String theId) {
        usersRepository.deleteById(theId);
    }

    @Override
    public void saveUsersFromExcel(MultipartFile file) {
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<Users> users=ExcelUploadService.getUsersDataFromExcel(file.getInputStream());
                this.usersRepository.saveAll(users);
            } catch (IOException e) {
                throw new IllegalArgumentException("Please upload a valid Excel file");
            }
        }
    }



    public List<Users> getUsers(){
        return usersRepository.findAll();
    }
    @Override
    public List<Users> findByCertificateLevel(String level) {
        return usersRepository.findByCertificateLevel(level);
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Users> users= usersRepository.findById(userName);
        users.orElseThrow(() -> new UsernameNotFoundException("User: "+userName+" does not exist"));
        return users.map(MyUserDetails::new).get();
    }
}
