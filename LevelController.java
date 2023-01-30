package com.ECESWA.eRegistration.controller;

import com.ECESWA.eRegistration.entity.Level;
import com.ECESWA.eRegistration.repository.LevelRepository;
import com.ECESWA.eRegistration.service.LevelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LevelController {
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private LevelService levelService;

    public LevelController(LevelService levelService) {
    }
    @GetMapping("/level")
    public String listLevels(Model model){

        return "levels";
    }

    @GetMapping("/getLevel")
    public ResponseEntity<List<Level>> getLevel()
    {
        return new ResponseEntity<List<Level>>(levelRepository.findAll(), HttpStatus.OK);

    }

    @PostMapping("/addLevel")
    public ResponseEntity<Level> createLevel(@RequestBody Level level)
    {
        ObjectMapper om = new ObjectMapper();

        try
        {
            System.out.println(om.writeValueAsString(level));
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<Level>(levelRepository.save(level), HttpStatus.CREATED);
    }
    @GetMapping("level/new")
    public String createLevel(Model model){

        Level level=new Level();
        model.addAttribute("level", level);
        return "Creating Level";
    }
    @PutMapping("/updateLevel")
    public Level updateLevel(@RequestBody Level level){
        Level updateResponse= levelService.editLevel(level);
        return updateResponse;
    }
    @RequestMapping(value="/deleteLevel", method=RequestMethod.DELETE)
    @ResponseBody
    public String deleteLevel(@RequestBody Level level){
        levelService.deleteLevel(level);
        return "Level successfully deleted";
    }
}
