/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webservices.model;

import com.interctec.java.webservices.data.ControlDataArchivo;
import java.util.List;

/**
 *
 * @author luis.perez
 */
public class UsersModel {

    public UsersModel() {
        //SE INICIALIZAN LAS LISTAS 
        //LA DATA PROVIENE DE UN ARCHIVO DE RECURSOS
        //ESTA IMPLEMENTACION PUEDE SE CAMBIADA PARA BUSCAR LA DATA DE UNA BASE DE DATOS
        //SIN NECESIDAD DE CAMBIAR EL RESTO DE LA IMPLEMENTACION
        userNamesSaved      = new ControlDataArchivo().consultarArchivo("UsersNamesDataSaved.txt");
        userNamesRestricted = new ControlDataArchivo().consultarArchivo("UsersNamesRestricted.txt");
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
    
    public boolean agregarUserNameRestricted(String userNameRestricted){
        return new ControlDataArchivo().agregarUserNameRestricted(userNameRestricted);
    }
}
