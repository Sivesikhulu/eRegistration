package com.ECESWA.ElectReg.controller;

import com.ECESWA.ElectReg.dao.JCRegistrationRepository;
import com.ECESWA.ElectReg.entity.JCRegistration;
import com.ECESWA.ElectReg.security.MyUserDetails;
import com.ECESWA.ElectReg.service.JCRegistrationService;
import org.apache.poi.ss.usermodel.Cell;
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
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping()
public class JCRegistrationController {
    @Autowired
    private JCRegistrationService jCRegistrationService;
     @Autowired
     private JCRegistrationRepository jcRegistrationRepo;

    public JCRegistrationController (JCRegistrationService theJCRegistrationService){
        jCRegistrationService=theJCRegistrationService;
    }
    @GetMapping("/3/list")
    public String listCandidates(Model theModel){
        MyUserDetails principal=(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<JCRegistration> theCandidates=jCRegistrationService.findByCentreOrderBySurname(principal.getCentreNumber());
        theModel.addAttribute("candidates", theCandidates);
        return "candidates/list-candidates-3";
    }


    @GetMapping("/3/addCandidateForm")
	public String addCandidateForm(Model theModel){
		JCRegistration theCandidate =new JCRegistration();
		theModel.addAttribute("candidate", theCandidate);
		return "candidates/candidate-form-3";
	}

    @PostMapping("/3/save")
	public String saveCandidate(@ModelAttribute("candidate") JCRegistration theJCRegistration){
		//save the candidate
		jCRegistrationService.save(theJCRegistration);
		//use a redirect to prevent duplicate submissions
		return "redirect:/3/list";
	}

    @GetMapping("/3/updateCandidateForm")
	public String updateCandidateForm(@RequestParam("candidateId") int theId, Model theModel){
		//Get the candidate from the service
		JCRegistration theCandidate=jCRegistrationService.findById(theId);
		//Set candidate in the model to prepopulate the form
		theModel.addAttribute("candidate", theCandidate);
		//send over to the form
		return "candidates/candidate-form-3";
	}
    @GetMapping("/3/delete")
    public String delete (@RequestParam("candidateId") int theId){
        jCRegistrationService.deleteById(theId);
        return "redirect:/3/list";
    }


     @PostMapping("/3/upload")
    public String getJCRegistrationCandidatesDataFromExcel(@RequestParam("excelFile") MultipartFile file) throws IOException {
         File uploadFile=new File((file.getOriginalFilename()));
         FileInputStream fis=new FileInputStream(uploadFile);
         MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         FileWriter theWriter= new FileWriter(principal.getAuthorities()+" "+principal.getCentreNumber()+"txt");

         List<JCRegistration>candidates=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet=workbook.getSheet("candidates");

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd");
            int rowIndex=9;

            for(Row row: sheet){
                if(rowIndex==9){
                    rowIndex++;
                    continue;
                }
                Collection<String> subjectList=new LinkedList<String>();
                Iterator<Cell> cellIterator=row.iterator();
                int cellIndex=0;
                JCRegistration candidate=new JCRegistration();
                while(cellIterator.hasNext()){
                    Cell cell= cellIterator.next();
                    switch(cellIndex){
                        case 0 ->candidate.setCentre((int) cell.getNumericCellValue());
                        case 2 ->candidate.setSurname(cell.getStringCellValue());
                        case 3 ->candidate.setNames(cell.getStringCellValue());
                        case 4 -> {
                            if (cell.getStringCellValue()!="Y" ||cell.getStringCellValue()!="N"){
                                theWriter.write("Enter correct OVC status for "+candidate.getSurname()+" "+candidate.getNames()+". Either 'Y' or 'N'");
                                candidate.setOvcStatus(cell.getStringCellValue());
                            }else candidate.setOvcStatus(cell.getStringCellValue());
                        }
                        case 5 ->candidate.setGender(cell.getStringCellValue());
                        case 6 ->{
                            String formattedDate=sdf.format(cell.getDateCellValue());
                            candidate.setDateOfBirth(formattedDate);
                        }
                        case 8 ->{
                            if (cell.getStringCellValue().length() !=13){
                                theWriter.write("The PIN is incorrect for "+candidate.getSurname()+" "+candidate.getNames());
                                candidate.setNationalId(cell.getStringCellValue());
                            }else candidate.setNationalId(cell.getStringCellValue());
                        }
                        case 9 ->candidate.setForeignId(cell.getStringCellValue());
                        case 10->{
                            int subject= (int) cell.getNumericCellValue();
                            subjectList.add(String.valueOf(subject));
                            //candidate.setSubjects(Collections.singletonList(String.valueOf((int)cell.getNumericCellValue())));
                        }
                        case 11 -> {
                            int subject= (int) cell.getNumericCellValue();
                            subjectList.add(String.valueOf(subject));
                            candidate.setSubjects(subjectList);
                        }
                        case 12 -> {
                            int subject= (int) cell.getNumericCellValue();
                            subjectList.add(String.valueOf(subject));
                            candidate.setSubjects(subjectList);
                        }
                        case 13 -> {
                            int subject= (int) cell.getNumericCellValue();
                            subjectList.add(String.valueOf(subject));
                            candidate.setSubjects(subjectList);
                        }
                        case 14 -> {
                            int subject= (int) cell.getNumericCellValue();
                            subjectList.add(String.valueOf(subject));
                            candidate.setSubjects(subjectList);
                        }
                        case 15 -> {
                            int subject= (int) cell.getNumericCellValue();
                            subjectList.add(String.valueOf(subject));
                            candidate.setSubjects(subjectList);
                        }
                        case 16 -> {
                            int subject= (int) cell.getNumericCellValue();
                            subjectList.add(String.valueOf(subject));
                            candidate.setSubjects(subjectList);
                        }
                        case 17 -> {
                            int subject= (int) cell.getNumericCellValue();
                            subjectList.add(String.valueOf(subject));
                            candidate.setSubjects(subjectList);
                        }
                        case 18 -> {
                            int subject= (int) cell.getNumericCellValue();
                            subjectList.add(String.valueOf(subject));
                            candidate.setSubjects(subjectList);

                        }
                        default->{
                        }
                    }
                    cellIndex++;
                }
                candidates.add(candidate);
            }

            jcRegistrationRepo.saveAll(candidates);

        }catch (Exception e){
            e.getStackTrace();
        }
         fis.close();
         return "redirect:/3/list";
    }

}
