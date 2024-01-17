//package com.ECESWA.ElectReg.security;
//
//import com.ECESWA.ElectReg.dao.UsersRepository;
//import com.ECESWA.ElectReg.entity.Users;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//    @Autowired
//    UsersRepository usersRepository;
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<Users> users= usersRepository.findById(userName);
//        users.orElseThrow(() -> new UsernameNotFoundException("User: "+userName+" does not exist"));
//        return users.map(MyUserDetails::new).get();
//    }
//}
