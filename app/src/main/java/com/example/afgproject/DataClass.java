package com.example.afgproject;

import android.widget.EditText;

public class DataClass {

    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private String dataImage;
    private String zipCode;
    private String key;
    private String time;

    public DataClass() {
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

    public DataClass(String dataTitle, String dataDesc, String dataLang, String dataImage, String zipCode, String time){
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
        this.zipCode = zipCode;
        this.time = time;
    }

}
