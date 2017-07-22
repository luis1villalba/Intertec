/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webpage.dto;

import java.util.List;

/**
 *
 * @author luis.perez
 */
public class RespuestaDTO {
    private String codigoRespuestaOperacion; //INDICA SI LA TRANSACCCION SE LLEVO A CABO CON EXITO
    private Boolean valid;              //INDICA SI EL USUARIO ES VALIDO O NO
    private Boolean restricted;         //INDICA QUE EL USUARIO ESTA RESTRINGIDO 
    private Boolean userNameToAddExist; //INDICA QUE EL USERNAME A AGREGAR YA EXISTE EN EL ARCHIVO
    private List<String> userNames;     //INDICA LA LISTA DE USERNAMES PARA LAS SUGERENCIAS

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }    

    public Boolean getRestricted() {
        return restricted;
    }

    public void setRestricted(Boolean restricted) {
        this.restricted = restricted;
    }    

    public String getCodigoRespuestaOperacion() {
        return codigoRespuestaOperacion;
    }

    public void setCodigoRespuestaOperacion(String codigoRespuestaOperacion) {
        this.codigoRespuestaOperacion = codigoRespuestaOperacion;
    }

    public Boolean getUserNameToAddExist() {
        return userNameToAddExist;
    }

    public void setUserNameToAddExist(Boolean userNameToAddExist) {
        this.userNameToAddExist = userNameToAddExist;
    }
}
