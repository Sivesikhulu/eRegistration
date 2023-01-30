package com.ECESWA.eRegistration.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentID;
    private String studentLastName;
    private String studentFirstName;
    private String studentOtherNames;
    private String ovcStatus;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="centreNo", referencedColumnName="centreNo", nullable = false),
            @JoinColumn(name="levelCode", referencedColumnName="levelCode", nullable = false)})
    private Centre centre;
    private String dateOfBirth;
    private String gender;
    @Column(unique=true)
    private int SZID;
    @Column(unique=true)
    private String foreignId;
    private int candidateType;

    public Student() {
    }

    public Student(String studentLastName, String studentFirstName, String studentOtherNames, String ovcStatus, Centre centre, String dateOfBirth, String gender, int SZID, String foreignId, int candidateType) {
        this.studentLastName = studentLastName;
        this.studentFirstName = studentFirstName;
        this.studentOtherNames = studentOtherNames;
        this.ovcStatus = ovcStatus;
        this.centre = centre;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.SZID = SZID;
        this.foreignId = foreignId;
        this.candidateType = candidateType;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentOtherNames() {
        return studentOtherNames;
    }

    public void setStudentOtherNames(String studentOtherNames) {
        this.studentOtherNames = studentOtherNames;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSZID() {
        return SZID;
    }

    public void setSZID(int SZID) {
        this.SZID = SZID;
    }

    public String getOvcStatus() {
        return ovcStatus;
    }

    public void setOvcStatus(String ovcStatus) {
        this.ovcStatus = ovcStatus;
    }

    public int getCandidateType() {
        return candidateType;
    }

    public void setCandidateType(int candidateType) {
        this.candidateType = candidateType;
    }
}
