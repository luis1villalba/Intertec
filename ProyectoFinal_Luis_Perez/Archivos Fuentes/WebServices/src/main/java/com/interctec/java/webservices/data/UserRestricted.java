/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webservices.data;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author luis.perez
 */
@Stateless(name =  "UserRestricted")
@ApplicationScoped
public class UserRestricted {
    
    List<String> userNamesRestricted  = new ArrayList<>();

    public List<String> getUserNamesRestricted() {
        return userNamesRestricted;
    }

    public void setUserNamesRestricted(List<String> userNamesRestricted) {
        this.userNamesRestricted = userNamesRestricted;
    }    
}
