package com.ECESWA.ElectReg.entity;

import com.ECESWA.ElectReg.security.MyUserDetails;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="JCRegistration")
public class JCRegistration {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="centre")
    private int centre;
    @Column(name="surname")
    private String surname;
    @Column(name="names")
    private String names;
    @Column(name="gender")
    private String gender;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @Column(name="dateofbirth")
    private String dateOfBirth;

    @Column(name="nationalid")
    private String nationalId;
    @Column(name="foreignid")
    private String foreignId;
    @Column(name="subjects")
    private Collection<String> subjects;
    @Column(name="certificatelevel")//, nullable = false, unique = true)
    private int certificateLevel;
    @Column(name="year")
    private int year;
    @Column(name="ovcstatus")
    private String ovcStatus;
    @Column(name="noofsubjects")
    private String numberOfSubjects;

    public JCRegistration() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCentre() {
        MyUserDetails principal=(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        centre =principal.getCentreNumber();
        return centre;
    }

    public JCRegistration(int id, int centre, String surname, String names, String gender, String dateOfBirth, String nationalId, String foreignId, Collection<String> subjects, int certificateLevel, int year, String ovcStatus, String numberOfSubjects) {
        this.id = id;
        this.centre = centre;
        this.surname = surname;
        this.names = names;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.nationalId = nationalId;
        this.foreignId = foreignId;
        this.subjects = subjects;
        this.certificateLevel = certificateLevel;
        this.year = year;
        this.ovcStatus = ovcStatus;
        this.numberOfSubjects = numberOfSubjects;
    }

    public void setCentre(int centre) {
        this.centre = centre;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname.toUpperCase();
    }

    public String getNumberOfSubjects() {
        return numberOfSubjects;
    }

    public void setNumberOfSubjects(String numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names.toUpperCase();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId.toUpperCase();
    }

    public Collection<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<String> subjects) {
        this.subjects = subjects;
    }

    public int getCertificateLevel() {
        return certificateLevel;
    }

    public void setCertificateLevel(int certificateLevel) {
        this.certificateLevel = certificateLevel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOvcStatus() {
        return ovcStatus;
    }

    public void setOvcStatus(String ovcStatus) {
        this.ovcStatus = ovcStatus;
    }

    @Override
    public String toString() {
        return "JCRegistration{" +
                "id=" + id +
                ", centre=" + centre +
                ", surname='" + surname + '\'' +
                ", names='" + names + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", foreignId='" + foreignId + '\'' +
                ", subjects=" + subjects +
                ", certificateLevel=" + certificateLevel +
                ", year=" + year +
                ", ovcStatus='" + ovcStatus + '\'' +
                ", numberOfSubjects='" + numberOfSubjects + '\'' +
                '}';
    }
}
