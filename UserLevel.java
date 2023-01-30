package com.ECESWA.eRegistration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
@Entity
@Table(name="userLevel")
public class UserLevel implements Serializable {
    @Id
    private int UserLevelCode;
    private String UserType;

    public UserLevel() {
    }

    public UserLevel(int userLevelCode, String userType) {
        this.UserLevelCode = userLevelCode;
        this.UserType = userType;
    }

    public int getUserLevelCode() {
        return UserLevelCode;
    }

    public String getUserType() {
        return UserType;
    }
}
