package com.ECESWA.eRegistration.controller;
import com.ECESWA.eRegistration.entity.Centre;
import com.ECESWA.eRegistration.entity.Student;
import com.ECESWA.eRegistration.repository.CentreRepository;
import com.ECESWA.eRegistration.repository.StudentRepository;
import com.ECESWA.eRegistration.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CentreRepository centreRepository;


    public StudentController(StudentService studentService) {
            }
    @GetMapping("/getStudents")
    public ResponseEntity<List<Student>> getStudents()
    {
        return new ResponseEntity<List<Student>>(studentRepository.findAll(), HttpStatus.OK);

    }

    @GetMapping("/testSession")
    public String testSession(HttpSession sess)
    {
        sess.invalidate();

        return sess.getAttribute("userid").toString();

    }

    @GetMapping("/getListStudents")
    public ModelAndView listStudents(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentsList");
        return modelAndView;
    }
    @GetMapping("/studentForm")
    public  ModelAndView createStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create_student");
        return modelAndView;
    }

    @PostMapping(path = "/addStudent", consumes = {"application/xml","application/json"})
    public ModelAndView createStudent(@RequestBody /*@ModelAttribute("student")*/ Student student){

        ObjectMapper om = new ObjectMapper();

        try
        {
            System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(student));
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        Student newStudent=studentService.saveStudent(student);
        //studentService.saveStudent(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/getListStudents");
        return modelAndView;
    }

    @PostMapping(value="/students")
    public ModelAndView saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/getListStudents");
        return modelAndView;
    }

    @GetMapping(value="/updateStudentForm/edit/{id}", consumes = {"application/xml","application/json"})
    public ModelAndView editStudentForm(@PathVariable Integer id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/edit_student");
        return modelAndView;
    }



    @PostMapping("/updateStudent/{id}")
    public ModelAndView updateStudent(@Validated @PathVariable Integer id,
                /*@ModelAttribute("student")*/@RequestBody Student student, Model model){

        Student existingStudent=studentService.getStudentById(id);
        existingStudent.setStudentID(id);
        existingStudent.setStudentFirstName(student.getStudentFirstName());
        existingStudent.setStudentLastName(student.getStudentLastName());
        existingStudent.setStudentOtherNames(student.getStudentOtherNames());
        existingStudent.setGender(student.getGender());
        existingStudent.setCandidateType(student.getCandidateType());
        existingStudent.setDateOfBirth(student.getDateOfBirth());
        existingStudent.setOvcStatus(student.getOvcStatus());
        existingStudent.setSZID(student.getSZID());
        existingStudent.setForeignId(student.getForeignId());

        //Saving the data back to DB
        studentService.editStudent(existingStudent);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/studentsList");
        return modelAndView;
    }

    @GetMapping("/deleteStudent/{id}")
    public ModelAndView deleteStudent(@PathVariable Integer id){
        studentService.deleteStudentById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/getListStudents");
        return modelAndView;
    }

}
