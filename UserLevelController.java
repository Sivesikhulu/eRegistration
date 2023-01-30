package com.ECESWA.eRegistration.controller;

import com.ECESWA.eRegistration.entity.UserLevel;
import com.ECESWA.eRegistration.repository.UserLevelRepository;
import com.ECESWA.eRegistration.service.UserLevelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserLevelController {
    @Autowired
    private UserLevelRepository userLevelRepository;
    @Autowired
    private UserLevelService userLevelService;

    public UserLevelController(UserLevelService userLevelService) {
    }
    @GetMapping("/userLevel")
    public String listUserLevels(Model model){
        return "userLevels";
    }

    @GetMapping("/getUserlevels")
    public ResponseEntity<List<UserLevel>> getUserLevels()
    {
        return new ResponseEntity<List<UserLevel>>(userLevelRepository.findAll(), HttpStatus.OK);

    }

    @PostMapping("/addUserLevel")
    public ResponseEntity<UserLevel> createUserlevel(@RequestBody UserLevel userLevel)
    {
        ObjectMapper om = new ObjectMapper();

        try
        {
            System.out.println(om.writeValueAsString(userLevel));
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<UserLevel>(userLevelRepository.save(userLevel), HttpStatus.CREATED);
    }
    @GetMapping("userLevel/new")
    public String createUserLevel(Model model){
        UserLevel userLevel=new UserLevel();
        model.addAttribute("userLevel", userLevel);
        return "create_userLevel";
    }
    @PutMapping("/updateUserLevel")
    public UserLevel updateUserLevel(@RequestBody UserLevel userLevel){
        UserLevel updateResponse= userLevelService.editUserLevel(userLevel);
        return updateResponse;
    }
    @RequestMapping(value="/deleteUserLevel", method=RequestMethod.DELETE)
    @ResponseBody
    public String deleteUserlevel(@RequestBody UserLevel userLevel){
        userLevelService.deleteUserLevel(userLevel);
        return "Candidate successfully deleted";
    }
}
