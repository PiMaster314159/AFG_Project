package com.example.afgproject;

import java.util.ArrayList;

public class OrganizationActivity {
    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private String dataImage;
    private String zipCode;
    private String key;
    private String time;

    public OrganizationActivity() {
    }
    public String getDataTitle(){
        return dataTitle;
    }

    public String getDataDesc(){
        return dataDesc;
    }

    public String getDataLang(){
        return dataLang;
    }

    public String getDataImage(){
        return dataImage;
    }

    public String getTime() {return time;}

    public String getZipCode() {return zipCode;}

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<String> getInterests(){
        return new ArrayList<>();
    }

    public ArrayList<String> getSkills(){
        return new ArrayList<>();
    }

    public OrganizationActivity(String dataTitle, String dataDesc, String dataLang, String dataImage, String zipCode, String time){
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
        this.zipCode = zipCode;
        this.time = time;
    }

    public void setUpHolder(OrganizationActivity organizationActivity) {
    }
}
