package com.ECESWA.ElectReg.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name="id")@NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String id;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Column(name="pw")
    private String pw;
    @Column(name="centre")
    private int centre;
    @Column(name="centrename")
    private String centreName;
    @Column(name="region")
    private String region;
    @Column(name="mobile")
    private int mobile;
    @Column(name="mobile2")
    private int mobile2;
    @Column(name="certificatelevel")
    private String certificateLevel;

    public Users() {
    }

    public Users(String id, String pw, int centre, String centreName, String region, int mobile, int mobile2, String certificateLevel) {
        this.id = id;
        this.pw = pw;
        this.centre = centre;
        this.centreName = centreName;
        this.region = region;
        this.mobile = mobile;
        this.mobile2 = mobile2;
        this.certificateLevel = certificateLevel;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getCentre() {
        return centre;
    }

    public void setCentre(int centre) {
        this.centre = centre;
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

    public int getMobile2() {
        return mobile2;
    }

    public void setMobile2(int mobile2) {
        this.mobile2 = mobile2;
    }

    public String getCertificateLevel() {
        return certificateLevel;
    }

    public void setCertificateLevel(String certificateLevel) {
        this.certificateLevel = certificateLevel;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", centre=" + centre +
                ", centreName='" + centreName + '\'' +
                ", region='" + region + '\'' +
                ", mobile=" + mobile +
                ", mobile2=" + mobile2 +
                ", certificateLevel=" + certificateLevel +
                '}';
    }
}
