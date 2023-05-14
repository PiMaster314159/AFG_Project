package com.example.afgproject;

import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Organization {

    //Data
    private String orgName;
    private String orgEmail;
    private String headEmail;
    private int orgPhone;
    private int headPhone;
    private int orgZip;

    //Constructors

    /**
     * Default constructor. Sets string variables to null and int variables to -1.
     */
    public Organization() {
        orgName = "";
        orgEmail = "";
        headEmail = "";
        orgPhone = -1;
        headPhone = -1;
        orgZip = -1;
    }

    /**
     * constructor for organization profile that sets the organization's name, emails, phones, and zip code using input values.
     *
     * @param name   the name of the organization
     * @param oEmail the email of the organization
     * @param hEmail the email of the head of the organization
     * @param oPhone the phone number of the organization
     * @param hPhone the phone number of the head of the organization
     * @param zip    the sip code of the organization
     */
    public Organization(String name, String oEmail, String hEmail, int oPhone, int hPhone, int zip) {
        orgName = name;
        orgEmail = oEmail;
        headEmail = hEmail;
        orgPhone = oPhone;
        headPhone = hPhone;
        orgZip = zip;
    }
    // /fb op constructors


    //methods

//fb op


    //methods
//setters

    /**
     * sets orgName to input name
     *
     * @param newName the name orgName is changed to
     */
    public void setOrgName(String newName) {
        orgName = newName;
    }

    /**
     * sets orgEmail to input email
     *
     * @param newEmail the name orgEmail is changed to
     */
    public void setOrgEmail(String newEmail) {
        orgEmail = newEmail;
    }

    /**
     * sets headEmail to the inputted new Email
     *
     * @param newEmail the email headEmail is changed to
     */
    public void setheadEmail(String newEmail) {
        headEmail = newEmail;
    }

    /**
     * sets organization's phone to a new phone
     *
     * @param newPhone the phone number orgPhone is changed to
     */
    public void setOrgPhone(int newPhone) {
        orgPhone = newPhone;
    }

    /**
     * sets headPhone to a new phone number
     *
     * @param newPhone the new phone headPhone is changed to
     */
    public void setHeadPhone(int newPhone) {
        headPhone = newPhone;
    }

    /**
     * changes the organization's zip code to a new zip code
     *
     * @param newZip the sip code the orgZip is changed to
     */
    public void setOrgZip(int newZip) {
        orgZip = newZip;
    }
    // /setters

    //getters

    /**
     * gets the organization's name
     *
     * @return the organization's name
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * gets the organization's email
     *
     * @return the organization's email
     */
    public String getOrgEmail() {
        return orgEmail;
    }

    /**
     * gets the head of the organization's email
     *
     * @return the head of the organization's email
     */
    public String getheadEmail() {
        return headEmail;
    }

    /**
     * gets the phone number of the organization
     *
     * @return the phone number of the organization
     */
    public int getOrgPhone() {
        return orgPhone;
    }

    /**
     * gets the number of the organization head
     *
     * @return the number of the organization head
     */
    public int getHeadPhone() {
        return headPhone;
    }

    /**
     * gets the zip code of the organization
     *
     * @return the zip code of the organization
     */
    public int getOrgZip() {
        return orgZip;
    }
// /getters

    //other methods
    public void addOrgToFb() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("message");
    }
}
