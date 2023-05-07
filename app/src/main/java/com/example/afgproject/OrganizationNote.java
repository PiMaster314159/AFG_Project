package com.example.afgproject;

import java.util.ArrayList;

public class OrganizationNote {
    private String name;
    private String description;
    private String image;
    private ArrayList<String> fields;
    private String contactInfo;

    public OrganizationNote() {
        //empty constructor needed
    }

    public OrganizationNote(String name, String description, String image, ArrayList<String> fields, String contactInfo) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.fields = fields;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<String> getFields() {
        return fields;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}