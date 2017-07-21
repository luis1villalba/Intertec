/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webpage.controller;

import com.interctec.java.webpage.dao.UserDAO;
import com.interctec.java.webpage.dto.RespuestaDTO;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped; 

/**
 *
 * @author luis.perez
 */
@ManagedBean(name = "UserController")
@ViewScoped
public class UserController {
    private String usuario;
    private String usuarioRestringido;
    private String usuarioValido;
    private RespuestaDTO respuestaDTO;
    
    @EJB
    private UserDAO userDAO;
    
    public void validarUsuarioAction(){
        respuestaDTO = userDAO.validarUserName(usuario);
        if(respuestaDTO.getRestricted()){
            usuarioRestringido = "Este usuario esta restringido. No puede ser usado";
        }else {
            usuarioRestringido = "";
        }
        
        if(respuestaDTO.getValid()){
            usuarioValido = "Usuario Válido";
        }else {
            usuarioValido = "Usuario Inválido";
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public RespuestaDTO getRespuestaDTO() {
        return respuestaDTO;
    }

    public void setRespuestaDTO(RespuestaDTO respuestaDTO) {
        this.respuestaDTO = respuestaDTO;
    }

    public String getUsuarioRestringido() {
        return usuarioRestringido;
    }

    public void setUsuarioRestringido(String usuarioRestringido) {
        this.usuarioRestringido = usuarioRestringido;
    }

    public String getUsuarioValido() {
        return usuarioValido;
    }

    public void setUsuarioValido(String usuarioValido) {
        this.usuarioValido = usuarioValido;
    }
}
