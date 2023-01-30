package com.ECESWA.eRegistration.entity;

import com.ECESWA.eRegistration.entityPk.CentrePk;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Centre")
@IdClass(CentrePk.class)
public class Centre implements Serializable {

    @Id
    private int centreNo;
    @Id
    private int levelCode;
    private String centreName;
    private String region;
    private int mobile;
    private int altMobile;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName="userId")
    private Users userId;
    private Timestamp timestamp;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "centre")
    private Set<Student> students=new HashSet<>();

    public Centre() {
    }

    public Centre(int centreNo, int levelCode, String centreName, String region, int mobile, int altMobile, Users userId, Timestamp timestamp) {
        this.centreNo = centreNo;
        this.levelCode = levelCode;
        this.centreName = centreName;
        this.region = region;
        this.mobile = mobile;
        this.altMobile = altMobile;
        this.userId = userId;
        this.timestamp = timestamp;
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

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public int getAltMobile() {
        return altMobile;
    }

    public void setAltMobile(int altMobile) {
        this.altMobile = altMobile;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
