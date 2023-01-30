package com.ECESWA.eRegistration.controller;

import com.ECESWA.eRegistration.entity.Centre;
import com.ECESWA.eRegistration.entityPk.CentrePk;
import com.ECESWA.eRegistration.repository.CentreRepository;
import com.ECESWA.eRegistration.service.CentreService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class CentreController {
    @Autowired
    private CentreRepository centreRepository;
    @Autowired
    private CentreService centreService;

    public CentreController(CentreService centreService) {

    }
    @GetMapping("/centre")
    public String listCentres(Model model){
        return "centre";
    }

    /*@GetMapping("/getCentres")
    public ResponseEntity<List<Centre>> getCentre()
    {
        return new ResponseEntity<List<Centre>>(centreRepository.findAll(), HttpStatus.OK);
    }*/

    @GetMapping("/getCentres")
    public ModelAndView listCentre(Model model){
        model.addAttribute("centres", centreService.getAllCentres());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Landing");
        return modelAndView;
    }


    @GetMapping("/getCentre/{centreNo}/{levelCode}")
    public ResponseEntity<Optional<Centre>> getCentre(@PathVariable("centreNo") int centreNo, @PathVariable("levelCode") int levelCode){
        System.out.println("======="+centreNo+"========================================="+levelCode+"==================================");
        return new ResponseEntity<Optional<Centre>>(centreRepository.findById(new CentrePk(centreNo, levelCode)), HttpStatus.OK);
    }
}
