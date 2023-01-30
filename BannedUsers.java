package com.ECESWA.eRegistration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="bannedUsers")
public class BannedUsers implements Serializable {
    @Id
    private int UserID;
    private String Username;
    private String UserPassword;
    private Date Timestamp;

    public BannedUsers() {
    }

    public BannedUsers(int userID, String username, String userPassword, Date timestamp) {
        UserID = userID;
        Username = username;
        UserPassword = userPassword;
        Timestamp = timestamp;
    }

    public int getUserID() {
        return UserID;
    }

    public String getUsername() {
        return Username;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public Date getTimestamp() {
        return Timestamp;
    }
}
