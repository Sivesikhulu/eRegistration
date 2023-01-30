package com.ECESWA.eRegistration.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "enrolment")
public class Enrolment implements Serializable {
    @Id
    private int EnrolmentID;
    private int EnrolmentYear;

    @JoinColumn(name = "levelCode")
    private Level LevelCode;

    @JoinColumn(name = "centreNo")
    private Centre CentreNo;
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "studentID")
    private Student StudentID;

    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "SZID")
    private Student SZID;

    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "foreignID")
    private Student ForeignID;

    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "subjectCode")
    private Student SubjectCode;

    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "userID")
    private Users UserID;
    private Date Timestamp;

    public Enrolment() {
    }

    public Enrolment(int enrolmentID, int enrolmentYear, Level levelCode, Centre centreNo, Student studentID, Student SZID, Student foreignID, Student subjectCode, Users userID, Date timestamp) {
        EnrolmentID = enrolmentID;
        EnrolmentYear = enrolmentYear;
        LevelCode = levelCode;
        CentreNo = centreNo;
        StudentID = studentID;
        this.SZID = SZID;
        ForeignID = foreignID;
        SubjectCode = subjectCode;
        UserID = userID;
        Timestamp = timestamp;
    }

    public int getEnrolmentID() {
        return EnrolmentID;
    }

    public void setEnrolmentID(int enrolmentID) {
        EnrolmentID = enrolmentID;
    }

    public int getEnrolmentYear() {
        return EnrolmentYear;
    }

    public void setEnrolmentYear(int enrolmentYear) {
        EnrolmentYear = enrolmentYear;
    }

    public Level getLevelCode() {
        return LevelCode;
    }

    public void setLevelCode(Level levelCode) {
        LevelCode = levelCode;
    }

    public Centre getCentreNo() {
        return CentreNo;
    }

    public void setCentreNo(Centre centreNo) {
        CentreNo = centreNo;
    }

    public Student getStudentID() {
        return StudentID;
    }

    public void setStudentID(Student studentID) {
        StudentID = studentID;
    }

    public Student getSZID() {
        return SZID;
    }

    public void setSZID(Student SZID) {
        this.SZID = SZID;
    }

    public Student getForeignID() {
        return ForeignID;
    }

    public void setForeignID(Student foreignID) {
        ForeignID = foreignID;
    }

    public Student getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(Student subjectCode) {
        SubjectCode = subjectCode;
    }

    public Users getUserID() {
        return UserID;
    }

    public void setUserID(Users userID) {
        UserID = userID;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }
}
