package com.ECESWA.ElectReg.controller;

import com.ECESWA.ElectReg.dao.PrimaryRegistrationRepository;
import com.ECESWA.ElectReg.dao.UsersRepository;
import com.ECESWA.ElectReg.entity.PrimaryRegistration;
import com.ECESWA.ElectReg.entity.Users;
import com.ECESWA.ElectReg.security.MyUserDetails;
import com.ECESWA.ElectReg.service.PrimaryRegistrationService;
import com.ECESWA.ElectReg.service.UsersService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping()
public class PrimaryRegistrationController {
    @Autowired
    private PrimaryRegistrationService primaryRegistrationService;
    @Autowired
    private PrimaryRegistrationRepository primaryRegistrationRepo;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;

    public PrimaryRegistrationController (PrimaryRegistrationService thePrimaryRegistrationService){
        primaryRegistrationService=thePrimaryRegistrationService;
    }
    @GetMapping("/2/list")
    public String listCandidates(Model theModel){
        MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PrimaryRegistration> theCandidates=primaryRegistrationService.findByCentreOrderBySurname(principal.getCentreNumber());
        theModel.addAttribute("candidates", theCandidates);
        return "candidates/list-candidates-2";
    }


    /*@GetMapping("/admin/unregisteredCentresEPC")
    public String findUnregisteredCentresEPC(Model theModel){
        List<PrimaryRegistration> thePrimaryRegistration=primaryRegistrationService.findByCentreIsNull(usersRepository.findAll());
        theModel.addAttribute("centres", thePrimaryRegistration);
        return "admins/unregisteredEPC";
    }*/
    @GetMapping("/admin/unregistered")
    public String listUnregistered(Model theModel){
        List listOne= usersService.findByCertificateLevel("ROLE_EPC");
        List listTwo=primaryRegistrationService.findAll();
        List distinctListOne= (List) listOne.stream().distinct().collect(Collectors.toList());
        List distinctListTwo= (List) listTwo.stream().distinct().collect(Collectors.toList());
        List<String> differences= (List<String>) distinctListOne.stream().filter(element->!distinctListTwo.contains(element))
                        .collect(Collectors.toList());
        //differences.removeAll(listOne);
        theModel.addAttribute("unregisteredCentresEPC", distinctListOne);
        return "admins/unregisteredEPC";
    }

    @PostMapping("/2/upload")
    public String getPrimaryRegistrationCandidatesDataFromExcel(@RequestParam("excelFile") MultipartFile file) throws IOException {
        File uploadFile=new File(file.getOriginalFilename());
        FileInputStream fis=new FileInputStream(uploadFile);
        //fos.close();
        /*String header= """
                Error Report for Centres Registration""";
        MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try
                (FileWriter theWriter= new FileWriter(principal.getAuthorities()+" "+principal.getCentreNumber()+".txt")){
            theWriter.write(header);
            theWriter.write(System.lineSeparator());*/


        /*Path path= Path.of(principal.getAuthorities()+" "+principal.getCentreNumber()+".txt");
        try{
            Files.writeString(path, header);
            } catch(IOException e){
            e.printStackTrace();
        }*/

        List<PrimaryRegistration>candidates=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet=workbook.getSheet("candidates");

            int rowIndex=10;

            for(Row row: sheet){
                if(rowIndex==10){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator=row.iterator();
                int cellIndex=0;
                PrimaryRegistration candidate=new PrimaryRegistration();
                while(cellIterator.hasNext()){
                    //List<String> tempSubjects =null;
                    Cell cell= cellIterator.next();
                    ArrayList<String> tempSubjects = new ArrayList<String>();
                    switch(cellIndex){

                        case 0 ->candidate.setCentre((int) cell.getNumericCellValue());
                        case 2 ->candidate.setSurname(cell.getStringCellValue());
                        case 3 ->candidate.setNames(cell.getStringCellValue());
                        case 5 ->candidate.setGender(cell.getStringCellValue());
                        case 6 ->candidate.setDateOfBirth(cell.getDateCellValue());
                        case 7 -> {
                            if (cell.getStringCellValue().length() !=13){
                                /*theWriter.write("The PIN is incorrect for "+candidate.getSurname()+" "+candidate.getNames());
                                theWriter.write(System.lineSeparator());*/
                                candidate.setNationalId(cell.getStringCellValue());
                            }else candidate.setNationalId(cell.getStringCellValue());
                        }
                        case 8 ->candidate.setForeignId(cell.getStringCellValue());
                        case 9->tempSubjects.add(cell.getStringCellValue());
                        case 10->tempSubjects.add(cell.getStringCellValue());
                        case 11->tempSubjects.add(cell.getStringCellValue());
                        case 12->tempSubjects.add(cell.getStringCellValue());
                        case 13->tempSubjects.add(cell.getStringCellValue());
                        case 14->tempSubjects.add(cell.getStringCellValue());
                        case 15->tempSubjects.add(cell.getStringCellValue());
                        case 16->tempSubjects.add(cell.getStringCellValue());
                        case 17->{
                            tempSubjects.add(cell.getStringCellValue());
                            candidate.setSubjects(tempSubjects);}
                        //candidate.setSubjects(Collections.singletonList(String.valueOf((int) cell.getNumericCellValue())));
                        default->{

                        }

                    }
                    cellIndex++;
                }
                candidates.add(candidate);
            }
            primaryRegistrationRepo.saveAll(candidates);
            /*theWriter.close();*/
        }catch (Exception e){
            e.getStackTrace();
        }


        /*}catch(IOException ex){
            ex.printStackTrace();
        }*/
        fis.close();
        //inputStream.close();
        return "redirect:/2/list";
    }





    @GetMapping("/2/addCandidateForm")
	public String addCandidateForm(Model theModel){
		PrimaryRegistration theCandidate =new PrimaryRegistration();
		theModel.addAttribute("candidate", theCandidate);
		return "candidates/candidate-form-2";
	}

    @PostMapping("/2/save")
	public String saveCandidate(@ModelAttribute("candidate") PrimaryRegistration thePrimaryRegistration){
		//save the candidate
		primaryRegistrationService.save(thePrimaryRegistration);
		//use a redirect to prevent duplicate submissions
		return "redirect:/2/list";
	}

    @GetMapping("/2/updateCandidateForm")
	public String updateCandidateForm(@RequestParam("candidateId") int theId, Model theModel){
		//Get the candidate from the service
		PrimaryRegistration theCandidate=primaryRegistrationService.findById(theId);
		//Set candidate in the model to prepopulate the form
		theModel.addAttribute("candidate", theCandidate);
		//send over to the form
		return "candidates/candidate-form-2";
	}

    @GetMapping("/2/delete")
    public String delete (@RequestParam("candidateId") int theId){
        primaryRegistrationService.deleteById(theId);
        return "redirect:/2/list";
    }

    @GetMapping("/2/deleteAll")
    public String deleteAll (@RequestParam("centre") int theCentre){
        MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        primaryRegistrationService.deleteByCentre(principal.getCentreNumber());
        return "redirect:/2/list";
    }





}
