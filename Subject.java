package com.ECESWA.eRegistration.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    @Id
    private int subjectCode;
    private String subjectName;
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="levelCode", referencedColumnName="levelCode", insertable =  false, updatable = false)
    private Level levelCode;

    public Subject() {
    }

    public Subject(int subjectCode, String subjectName, Level levelCode) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.levelCode = levelCode;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Level getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(Level levelCode) {
        this.levelCode = levelCode;
    }
}
