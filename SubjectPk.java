package com.ECESWA.eRegistration.entityPk;

import java.io.Serializable;
import java.util.Objects;

public class SubjectPk implements Serializable {

    private int subjectCode;
    private int levelCode;

    public SubjectPk() {
    }

    public SubjectPk(int subjectCode, int levelCode) {
        this.subjectCode = subjectCode;
        this.levelCode = levelCode;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(int levelCode) {
        this.levelCode = levelCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectPk subjectPk)) return false;
        return subjectCode == subjectPk.subjectCode && levelCode == subjectPk.levelCode;
    }
    @Override
    public int hashCode() {
        return Objects.hash(subjectCode, levelCode);
    }
}
