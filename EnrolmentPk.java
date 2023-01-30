package com.ECESWA.eRegistration.entityPk;

import com.ECESWA.eRegistration.entity.Student;

import java.util.Objects;

public class EnrolmentPk {

    private int SZID;
    private String foreignID;
    private int subjectCode;
    private int enrolmentYear;

    public EnrolmentPk() {
    }

    public EnrolmentPk(int SZID, String foreignID, int subjectCode, int enrolmentYear) {
        this.SZID = SZID;
        this.foreignID = foreignID;
        this.subjectCode = subjectCode;
        this.enrolmentYear = enrolmentYear;
    }

    public int getSZID() {
        return SZID;
    }

    public void setSZID(int SZID) {
        this.SZID = SZID;
    }

    public String getForeignID() {
        return foreignID;
    }

    public void setForeignID(String foreignID) {
        this.foreignID = foreignID;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getEnrolmentYear() {
        return enrolmentYear;
    }

    public void setEnrolmentYear(int enrolmentYear) {
        this.enrolmentYear = enrolmentYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrolmentPk enrolmentPk)) return false;
        return SZID == enrolmentPk.SZID && foreignID == enrolmentPk.foreignID && subjectCode == enrolmentPk.subjectCode && enrolmentYear == enrolmentPk.enrolmentYear;
    }
    @Override
    public int hashCode() {
        return Objects.hash(SZID, enrolmentYear, foreignID, subjectCode);
    }
}
