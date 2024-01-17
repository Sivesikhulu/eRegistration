package com.ECESWA.ElectReg.controller;

import com.ECESWA.ElectReg.entity.PrimaryRegistration;
import com.ECESWA.ElectReg.entity.Users;
import com.ECESWA.ElectReg.security.MyUserDetails;
import com.ECESWA.ElectReg.service.ExcelUploadService;
import com.ECESWA.ElectReg.service.PrimaryRegistrationService;
import com.ECESWA.ElectReg.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class UsersController {
    private UsersService usersService;


    public UsersController (UsersService theUsersService){
        usersService=theUsersService;
    }
    @PostMapping("/admin/uploadUsers")
    public ResponseEntity<?>uploadUsersData(@RequestParam("file")MultipartFile file){
        this.usersService.saveUsersFromExcel(file);
        //this.usersService.save(new Users());
        return ResponseEntity.ok(Map.of("Message", "Users data uploaded successfully onto Database"));
    }

    @GetMapping("/centreDetails")
	public String centreDetailsPage(Model theModel){
        MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users theUser=usersService.findById(principal.getUsername());
        //Users theUser =new Users();
		theModel.addAttribute("user", theUser);
		return "home";
	}

    /*
    @GetMapping("/updateCandidateForm")
	public String updateCandidateForm(@RequestParam("candidateId") int theId, Model theModel){
		//Get the candidate from the service
		EGCSERegistration theCandidate=egcseRegistrationService.findById(theId);
		//Set candidate in the model to prepopulate the form
		theModel.addAttribute("candidate", theCandidate);
		//send over to the form
		return "candidates/candidate-form-13";
	}
     */

    @GetMapping("/admin/landing")
    public String displayLanding(Model theModel){
        //theModel.addAttribute("webUsers", theUsers);
        return "adminPage";
    }

    @GetMapping("/admin/usersList")
    public String listUsers(Model theModel){
        List<Users> theUsers=usersService.findAll();
        theModel.addAttribute("webUsers", theUsers);
        return "list-users";
    }
    /*@GetMapping("/admin/unregisteredCentres")
    public String findUnregisteredCentres(Model theModel){
        List<Users> theUsers=usersService.findUnregisteredCentres();
        theModel.addAttribute("centres", theUsers);
        return "unregisteredEPC";
    }*/
    @GetMapping("/admin/centreList/2")
    public String listEPCCentres (Model theModel){
        List<Users> theCentres=usersService.findByCertificateLevel("ROLE_EPC");
        theModel.addAttribute("webUsers", theCentres);
        return "admins/list-centres";
    }

    @GetMapping("/admin/centreList/3")
    public String listJCCentres (Model theModel){
        List<Users> theCentres=usersService.findByCertificateLevel("ROLE_JC");
        theModel.addAttribute("webUsers", theCentres);
        return "admins/list-centres";
    }

    @GetMapping("/admin/centreList/13")
    public String listEGCSECentres (Model theModel){
        List<Users> theCentres=usersService.findByCertificateLevel("ROLE_EGCSE");
        theModel.addAttribute("webUsers", theCentres);
        return "admins/list-centres";
    }


    @GetMapping("/admin/updateCentreForm")
	public String updateCentreForm(@RequestParam("centreId") String theId, Model theModel){
		//Get the Centre from the service
		Users theUsers=usersService.findById(theId);
		//Set centre in the model to prepopulate the form
		theModel.addAttribute("webUsers", theUsers);
		//send over to the form
		return "admins/register-page";
	}


    @GetMapping("/admin/addUsersForm")
	public String addUsersForm(Model theModel){
		Users theUsers =new Users();
		theModel.addAttribute("webUsers", theUsers);
		return "admins/register-page";
	}



    @PostMapping("/admin/saveUser")
	public String saveUser(@ModelAttribute("webUsers") Users theUsers){
		//save the User
		usersService.save(theUsers);
		//use a redirect to prevent duplicate submissions
		return "redirect:/admin/landing";
	}

}
