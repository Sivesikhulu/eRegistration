package com.ECESWA.eRegistration.entityPk;
import java.io.Serializable;
import java.util.Objects;

public class CentrePk implements Serializable {

    private int centreNo;
    private int levelCode;

    public CentrePk() {
    }

    public CentrePk(int centreNo, int levelCode) {
        this.centreNo = centreNo;
        this.levelCode = levelCode;
    }

    public int getCentreNo() {
        return centreNo;
    }

    public void setCentreNo(int centreNo) {
        this.centreNo = centreNo;
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
        if (!(o instanceof CentrePk centrePk)) return false;
        return centreNo == centrePk.centreNo && levelCode == centrePk.levelCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(centreNo, levelCode);
    }
}
