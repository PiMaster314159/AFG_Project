package com.example.afgproject;

import java.util.ArrayList;

public class OrgActivity {
    private Integer zipCode;
    private String name;
    private String description;
    private ArrayList<String> fields;
    private ArrayList<String> skills;
    private String timeStart;
    private String timeEnd;

    public OrgActivity(){

    }

    public OrgActivity(Integer zipCode, String name, String description, ArrayList<String> fields, ArrayList<String> skills, String timeStart, String timeEnd) {
        this.zipCode = zipCode;
        this.name = name;
        this.description = description;
        this.fields = fields;
        this.skills = skills;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getFields() {
        return fields;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }
}
