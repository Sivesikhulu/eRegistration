package com.ECESWA.ElectReg.controller;

import com.ECESWA.ElectReg.entity.EGCSERegistration;
import com.ECESWA.ElectReg.entity.Users;
import com.ECESWA.ElectReg.security.MyUserDetails;
import com.ECESWA.ElectReg.service.EGCSERegistrationService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping()
public class EGCSERegistrationController {

    private EGCSERegistrationService egcseRegistrationService;

    public EGCSERegistrationController(EGCSERegistrationService theEgcseRegistrationService){
        egcseRegistrationService=theEgcseRegistrationService;
    }


/*    @PostMapping("upload")
    public String excelReader(@RequestParam("file") MultipartFile excel) {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);

            for(int i=9; i<sheet.getLastRowNum();i++) {
                XSSFRow row = sheet.getRow(i);
                for(int j=0;j<row.getPhysicalNumberOfCells();j++) {
                    System.out.print(row.getCell(j) +" ");
                }
                System.out.println("");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "candidates/list-candidates-13";
    }*/

    /*@PostMapping("/importFile")
    public ResponseEntity<EGCSERegistration> importCandidates(@RequestParam("file") MultipartFile importFile){
        EGCSERegistration egcseRegistration=egcseRegistrationService.importEGCSECandidates(importFile);
        return new ResponseEntity<>(egcseRegistration, HttpStatus.OK)
    }*/
    @GetMapping("/13/list")
    public String listCandidates(Model theModel){
        MyUserDetails principal=(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<EGCSERegistration> theCandidates=egcseRegistrationService.findByCentreOrderBySurname(principal.getCentreNumber());
        theModel.addAttribute("candidates", theCandidates);
        return "candidates/list-candidates-13";
    }

/*    public static void ReadExcel() throws IOException{
        File f = new File("1001.xlsx");
        FileInputStream fis = new FileInputStream(f);
        XSSFWorkbook excelWorkbook = new XSSFWorkbook(fis);
        XSSFSheet excelSheet=excelWorkbook.getSheetAt(0);
        int rows = excelSheet.getPhysicalNumberOfRows();
        int cols = excelSheet.getRow(0).getPhysicalNumberOfCells();
        String data[][]=new String[rows][cols];
        XSSFCell cell;
        for(int i= 0;i<rows;i++){
            for(int j=0;j<rows;j++){
                cell=excelSheet.getRow(i).getCell(j);
                String cellContents=cell.getStringCellValue();
                data[i][j]=cellContents;
            }
        }fis.close();
    }*/
    @GetMapping("/13/addCandidateForm")
	public String addCandidateForm(Model theModel){
		EGCSERegistration theCandidate =new EGCSERegistration();
		theModel.addAttribute("candidate", theCandidate);
		return "candidates/candidate-form-13";
	}

    @PostMapping("/13/save")
	public String saveCandidate(@ModelAttribute("candidate") EGCSERegistration theEgcseRegistration){
        //save the candidate
		egcseRegistrationService.save(theEgcseRegistration);
		//use a redirect to prevent duplicate submissions
		return "redirect:/13/list";
	}

    @GetMapping("/13/updateCandidateForm")
	public String updateCandidateForm(@RequestParam("candidateId") int theId, Model theModel){
		//Get the candidate from the service
		EGCSERegistration theCandidate=egcseRegistrationService.findById(theId);
		//Set candidate in the model to prepopulate the form
		theModel.addAttribute("candidate", theCandidate);
		//send over to the form
		return "candidates/candidate-form-13";
	}
    @GetMapping("/13/delete")
    public String delete (@RequestParam("candidateId") int theId){
        egcseRegistrationService.deleteById(theId);
        return "redirect:/13/list";
    }


     @PostMapping("/13/upload")
    public static List<EGCSERegistration>getEGCSERegistrationCandidatesDataFromExcel(@RequestParam("excelFile") MultipartFile file) throws IOException {
         File uploadFile=new File((file.getOriginalFilename()));
         FileInputStream fis=new FileInputStream(uploadFile);
         MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         FileWriter theWriter= new FileWriter(principal.getAuthorities()+" "+principal.getCentreNumber()+"txt");
        List<EGCSERegistration>candidates=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet=workbook.getSheet("candidates");

            int rowIndex=9;

            for(Row row: sheet){
                if(rowIndex==9){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator=row.iterator();
                int cellIndex=0;
                EGCSERegistration candidate=new EGCSERegistration();
                while(cellIterator.hasNext()){
                    Cell cell= cellIterator.next();
                    switch(cellIndex){
                        case 0 ->candidate.setCentre((int) cell.getNumericCellValue());
                        case 2 ->candidate.setSurname(cell.getStringCellValue());
                        case 3 ->candidate.setNames(cell.getStringCellValue());
                        case 4 -> {
                            if (cell.getStringCellValue()!="Y" ||cell.getStringCellValue()!="N"){
                                theWriter.write("Enter correct OVS status for "+candidate.getSurname()+" "+candidate.getNames()+". Either 'Y' or 'N'");
                                candidate.setOvcStatus(cell.getStringCellValue());
                            }else candidate.setOvcStatus(cell.getStringCellValue());
                        } 
                        case 5 ->candidate.setGender(cell.getStringCellValue());
                        case 6 ->candidate.setDateOfBirth((Date) cell.getDateCellValue());
                        case 7 ->{
                            if (cell.getStringCellValue().length() !=13){
                                theWriter.write("The PIN is incorrect for "+candidate.getSurname()+" "+candidate.getNames());
                                candidate.setNationalId(cell.getStringCellValue());
                            }else candidate.setNationalId(cell.getStringCellValue());
                        }
                        case 8 ->candidate.setForeignId(cell.getStringCellValue());
                        case 9->candidate.setSubjects(Collections.singletonList(String.valueOf((int)cell.getNumericCellValue())));
                        default->{

                        }
                    }
                    cellIndex++;
                }
                candidates.add(candidate);
            }

        }catch (IOException e){
            e.getStackTrace();
        }
         fis.close();//inputStream.close();
        return candidates;
    }


}
