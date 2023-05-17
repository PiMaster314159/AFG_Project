package com.example.afgproject;

import java.util.ArrayList;

/**
 * Wrapping class for activities/opportunities created by organizations. Communicates with FireBase to receive data
 */
public class ActivityData {
    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private String dataImage;
    private String zipCode;
    private String key;
    private String time;
    private ArrayList<String> skills;
    private ArrayList<String> interests;

    /**
     * Empty constructor for firebase communication
     */
    public ActivityData() {
    }

    /**
     * @return Activity name/title
     */
    public String getDataTitle(){
        return dataTitle;
    }

    /**
     * @return Description of the activity
     */
    public String getDataDesc(){
        return dataDesc;
    }

    /**
     * @return Date of the activity
     */
    public String getDataLang(){
        return dataLang;
    }

    /**
     * @return String resource of the image associated with the opportunity
     */
    public String getDataImage(){
        return dataImage;
    }

    /**
     * @return Time(s) at which the activity occurs
     */
    public String getTime() {return time;}

    /**
     * @return Zip code location of the activity
     */
    public String getZipCode() {return zipCode;}

    /**
     * @return FireBase key id of the activity
     */
    public String getKey() {
        return key;
    }

    /**
     * Used to set object firebase key value
     * @param key - Firebase key id
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return Activity recommended skills
     */
    public ArrayList<String> getSkills(){
        return skills;
    }

    /**
     * @return Activity recommended interests
     */
    public ArrayList<String> getInterests(){
        return interests;
    }

    /**
     * Constructor assigning class variables with data found inside the firebase documents
     * @param dataTitle -Activity title
     * @param dataDesc - Activity description
     * @param dataLang - Activity data
     * @param dataImage - Activity image
     * @param zipCode - Activity zip code
     * @param time - Activity time
     */
    public ActivityData(String dataTitle, String dataDesc, String dataLang, String dataImage, String zipCode, String time){
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
        this.zipCode = zipCode;
        this.time = time;
        this.skills = new ArrayList<>();
        this.interests = new ArrayList<>();
    }
}
