/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webservices.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis.perez
 */
public class UsersModel {

    public UsersModel() {
        userNamesSaved = new ArrayList<>();
        userNamesSaved.add("carlos");
        userNamesSaved.add("juaan");
        userNamesSaved.add("pedro");
        userNamesSaved.add("maria");
        userNamesSaved.add("juana");
        userNamesSaved.add("rosario");
        userNamesSaved.add("enerto");
        userNamesSaved.add("cesar");
        userNamesSaved.add("chala");
        userNamesSaved.add("smith");
        userNamesSaved.add("charles");
        
        userNamesRestricted = new ArrayList();
        userNamesRestricted.add("cannabis");
        userNamesRestricted.add("abuse");
        userNamesRestricted.add("crack");
        userNamesRestricted.add("damn");
        userNamesRestricted.add("drunk");
        userNamesRestricted.add("grass");
    }
    
    private List<String> userNamesSaved;
    private List<String> userNamesRestricted;

    public List<String> getUserNamesSaved() {
        return userNamesSaved;
    }

    public void setUserNamesSaved(List<String> userNamesSaved) {
        this.userNamesSaved = userNamesSaved;
    }

    public List<String> getUserNamesRestricted() {
        return userNamesRestricted;
    }

    public void setUserNamesRestricted(List<String> userNamesRestricted) {
        this.userNamesRestricted = userNamesRestricted;
    }
}
