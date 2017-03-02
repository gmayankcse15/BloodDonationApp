package com.example.username.blooddonationapp;

import android.widget.Toast;

import static android.text.TextUtils.isEmpty;
import static java.sql.Types.NULL;

/**
 * Created by mayank on 26/2/17.
 */

public class userdata {
    public String name ;
    public String gender ;
    public String address ;
    public String phone;
    public String group ;
    public String check;

    public userdata() {

    }


    public userdata(String name, String gender, String address, String phone , String group)
    {
        this.name = name ;
        this.gender = gender ;
        this.address = address ;
        this.phone = phone ;
        this.group = group ;

    }



    public String getname() { return name; }

    public void setname(String text) {
        this.name = name;
    }

    public String getgender() {
        return gender;
    }

    public void setgender(String text) {
        this.gender = gender;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String text) {
        this.address = address;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String text) {
        this.phone = phone;
    }

    public String getgroup() {
        return group;
    }

    public void setgroup(String text) {
        this.group = group;
    }




}
