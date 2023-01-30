package com.ECESWA.eRegistration.controller;

import com.ECESWA.eRegistration.entity.Users;
import com.ECESWA.eRegistration.repository.UsersRepository;
import com.ECESWA.eRegistration.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    public UsersController(UsersService usersService) {

    }
    @GetMapping("/users")
    public String listUsers(Model model){
        return "users";
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<Users>> getUsers()
    {
        return new ResponseEntity<List<Users>>(usersRepository.findAll(), HttpStatus.OK);
    }
    /*@PostMapping("/")
    public String validateLoginInfo(Model model, @Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        model.addAttribute("users", Users.getUserName());
        return "home";
    }*/
   /* @RequestMapping("/modelStudents")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentsList");
        return modelAndView;
    }*/
}
