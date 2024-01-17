package com.ECESWA.ElectReg.service;

import com.ECESWA.ElectReg.entity.PrimaryRegistration;
import com.ECESWA.ElectReg.entity.Users;
import jakarta.persistence.Index;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.*;

public class ExcelUploadService {
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
    public static List<Users>getUsersDataFromExcel(InputStream inputStream){
        List<Users>users=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet=workbook.getSheet("users");

            int rowIndex=0;

            for(Row row: sheet){
                if(rowIndex==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator=row.iterator();
                int cellIndex=0;
                Users user=new Users();
                while(cellIterator.hasNext()){
                    Cell cell= cellIterator.next();
                    switch(cellIndex){
                        case 0 ->user.setId(cell.getStringCellValue());
                        case 1 ->user.setPw(cell.getStringCellValue());
                        case 2 ->user.setCentre((int) cell.getNumericCellValue());
                        case 3 ->user.setCentreName(cell.getStringCellValue());
                        case 4 ->user.setRegion(cell.getStringCellValue());
                        case 5 ->user.setMobile((int) cell.getNumericCellValue());
                        case 6 ->user.setMobile2((int) cell.getNumericCellValue());
                        case 7 ->user.setCertificateLevel(cell.getStringCellValue());
                        default->{

                        }
                    }
                    cellIndex++;
                }
                users.add(user);
            }

        }catch (IOException e){
            e.getStackTrace();
        }
        return users;
    }


    /*public static List<PrimaryRegistration>getPrimaryRegistrationCandidatesDataFromExcel(InputStream inputStream){
        List<PrimaryRegistration>candidates=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet=workbook.getSheet("candidates");

            int rowIndex=9;

            for(Row row: sheet){
                if(rowIndex==9){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator=row.iterator();
                int cellIndex=0;
                PrimaryRegistration candidate=new PrimaryRegistration();
                while(cellIterator.hasNext()){
                    Cell cell= cellIterator.next();
                    switch(cellIndex){
                        case 0 ->candidate.setCentre((int) cell.getNumericCellValue());
                        case 2 ->candidate.setSurname(cell.getStringCellValue());
                        case 3 ->candidate.setNames(cell.getStringCellValue());
                        //case 4 ->candidate.setOvcStatus(cell.getStringCellValue());
                        case 5 ->candidate.setGender(cell.getStringCellValue());
                        case 6 ->candidate.setDateOfBirth((Date) cell.getDateCellValue());
                        case 7 ->candidate.setNationalId(cell.getStringCellValue());
                        case 8 ->candidate.setForeignId(cell.getStringCellValue());
                        case 9,10 ->candidate.setSubjects(Collections.singletonList(cell.getStringCellValue()));
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
        return candidates;
    }*/


}
