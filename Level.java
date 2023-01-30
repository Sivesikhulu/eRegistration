package com.ECESWA.eRegistration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="level")
public class Level implements Serializable {
    @Id
    private  int levelCode;
    private  String levelName;

    public Level(int levelCode, String levelName) {
        this.levelCode = levelCode;
        this.levelName = levelName;
    }

    public Level() {
    }

    public int getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(int levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
