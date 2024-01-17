package com.ECESWA.ElectReg.controller;

import com.ECESWA.ElectReg.entity.Users;
import com.ECESWA.ElectReg.service.UsersService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//import static org.springframework.aop.scope.ScopedProxyBeanRegistrationAotProcessor.logger;

@Controller
public class LoginController {

    private UsersService usersService;


    public LoginController (UsersService theUsersService){
        usersService=theUsersService;
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login-page";
    }
    /*@GetMapping("/register")
    public String showRegisterPage(){
        return "register-page";
    }*/

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }





    @GetMapping("/register")
	public String showRegisterPage(Model theModel){
		Users theUsers =new Users();
		theModel.addAttribute("webUser", theUsers);
		return "register-page";
	}

    @PostMapping("/processRegistration")
    public String processRegistration(
            @Valid @ModelAttribute("webUser") Users theUser, BindingResult theBindingResult,
            HttpSession session, Model theModel){
        String userName=theUser.getId();
        //logger.info("Processing registration for "+userName);

        //form validation
        if(theBindingResult.hasErrors()){
            return "register";
        }
        //checking DB if user already exists
        Users existing=usersService.findById(userName);
        if (existing!=null){
            theModel.addAttribute("webUser", new Users());
                theModel.addAttribute("registrationError", "Username already exists.");
                return "register";
        }
        //creating account and storing details
        usersService.save(theUser);

        session.setAttribute("user",theUser);
        return "login-page";

    }

    /*
        @PostMapping("/register")
	    public String registerUsers(@ModelAttribute("webUser") Users theUsers){
		    //save the User
		    UsersService.save(theUsers);
		    //use a redirect to prevent duplicate submissions

		    Users existing=usersService.findById(userName);
            if (existing!=null){
            theModel.addAttribute("webUser", new Users());
                theModel.addAttribute("registrationError", "Username already exists.");
                return "register";
        }

		    return "redirect:/2/list";
	}
         */
    @PostMapping("/registerUser")
    public String registerUsers(@ModelAttribute("webUser") Users theUsers){
        //save the User
        usersService.save(theUsers);
        //use a redirect to prevent duplicate submissions
        return "redirect:/login";
    }

}
