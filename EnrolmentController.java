package com.ECESWA.eRegistration.controller;

import com.ECESWA.eRegistration.entity.Enrolment;
import com.ECESWA.eRegistration.repository.EnrolmentRepository;
import com.ECESWA.eRegistration.service.EnrolmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrolmentController {
    @Autowired
    private EnrolmentRepository enrolmentRepository;
    @Autowired
    private EnrolmentService enrolmentService;

    public EnrolmentController(EnrolmentService enrolmentService) {
    }
    @GetMapping("/enrolment")
    public String listEnrolments(Model model){
        return "Enrolment";
    }

    @GetMapping("/getEnrolments")
    public ResponseEntity<List<Enrolment>> getEnrolments()
    {
        return new ResponseEntity<List<Enrolment>>(enrolmentRepository.findAll(), HttpStatus.OK);

    }

    @PostMapping("/addEnrolment")
    public ResponseEntity<Enrolment> createEnrolment(@RequestBody Enrolment enrolment)
    {
        ObjectMapper om = new ObjectMapper();

        try
        {
            System.out.println(om.writeValueAsString(enrolment));
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<Enrolment>(enrolmentRepository.save(enrolment), HttpStatus.CREATED);
    }
    @GetMapping("enrolment/new")
    public String createEnrolment(Model model){
        Enrolment enrolment=new Enrolment();
        model.addAttribute("enrolment", enrolment);
        return "Enrolled";
    }
    @PutMapping("/updateEnrolment")
    public Enrolment updateEnrolment(@RequestBody Enrolment enrolment){
        Enrolment updateResponse= enrolmentService.editEnrolment(enrolment);
        return updateResponse;
    }
    @RequestMapping(value="/deleteEnrolment", method=RequestMethod.DELETE)
    @ResponseBody
    public String deleteEnrolment(@RequestBody Enrolment enrolment){
        enrolmentService.deleteEnrolment(enrolment);
        return "Enrolment successfully deleted";
    }
}
