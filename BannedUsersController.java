package com.ECESWA.eRegistration.controller;

import com.ECESWA.eRegistration.entity.BannedUsers;
import com.ECESWA.eRegistration.repository.BannedUsersRepository;
import com.ECESWA.eRegistration.service.BannedUsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class BannedUsersController {
    @Autowired
    private BannedUsersRepository bannedUsersRepository;
    @Autowired
    private BannedUsersService bannedUsersService;

    public BannedUsersController(BannedUsersService bannedUsersService) {
    }
    @GetMapping("/bannedUsers")
    public String listBannedUsers(Model model){
        //model.addAttribute("student", studentService.getAllStudents());
        return "bannedUsers";
    }

    @GetMapping("/getBannedUsers")
    public ResponseEntity<List<BannedUsers>> getBannedUsers()
    {
        return new ResponseEntity<List<BannedUsers>>(bannedUsersRepository.findAll(), HttpStatus.OK);

    }

    @PostMapping("/addBannedUsers")
    public ResponseEntity<BannedUsers> createBannedUser(@RequestBody BannedUsers bannedUsers)
    {
        ObjectMapper om = new ObjectMapper();

        try
        {
            System.out.println(om.writeValueAsString(bannedUsers));
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<BannedUsers>(bannedUsersRepository.save(bannedUsers), HttpStatus.CREATED);
    }
    @GetMapping("bannedUsers/new")
    public String createBannedUsers(Model model){
        //creating empty student to hold form data
        BannedUsers bannedUsers=new BannedUsers();
        model.addAttribute("bannedUsers", bannedUsers);
        return "Removing Users";
    }
    @PutMapping("/updateBannedUsers")
    public BannedUsers updateBannedUsers(@RequestBody BannedUsers bannedUsers){
        BannedUsers updateResponse= bannedUsersService.editBannedUsers(bannedUsers);
        return updateResponse;
    }
    @RequestMapping(value="/deleteBannedUsers", method=RequestMethod.DELETE)
    @ResponseBody
    public String deleteBannedUsers(@RequestBody BannedUsers bannedUsers){
        bannedUsersService.deleteBannedUsers(bannedUsers);
        return "User successfully deleted";
    }
}
