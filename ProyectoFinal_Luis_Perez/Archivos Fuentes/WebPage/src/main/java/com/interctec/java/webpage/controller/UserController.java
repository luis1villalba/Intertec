/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webpage.controller;

import com.interctec.java.webpage.constants.Constants;
import com.interctec.java.webpage.dao.UserDAO;
import com.interctec.java.webpage.dto.RespuestaDTO;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author luis.perez
 */
@ManagedBean(name = "UserController")
@SessionScoped
public class UserController {

    private String usuario;
    private RespuestaDTO respuestaDTO;
    private boolean mostrarAlertaGenerica = Boolean.FALSE;
    private String mensajeAlertaGenerica;
    private ResourceBundle i18n = ResourceBundle.getBundle("i18n");
    private final int MIN_LENGHT_USER_NAME = 6;

    @EJB
    private UserDAO userDAO;

    public void validarUsuarioAction() {
        setMostrarAlertaGenerica(Boolean.TRUE);
        if (usuario.length() < MIN_LENGHT_USER_NAME) {
            setMensajeAlertaGenerica(i18n.getString("common.message.nombre.usuario.minlenght"));
        } else {
            respuestaDTO = userDAO.validarUserName(usuario);

            //EL USUARIO ES VALIDO
            if (respuestaDTO.getValid()) {
                setMensajeAlertaGenerica(i18n.getString("common.message.nombre.usuario.valido"));
                //EL USUARIO ESTA RESTRINGIDO    
            } else if (respuestaDTO.getRestricted()) {
                setMensajeAlertaGenerica(i18n.getString("common.message.nombre.usuario.no.restringido"));
                //EL USUARIO ES INVLAIDO PERO NO ESTA RESTRINGIDO    
            } else {
                setMensajeAlertaGenerica(i18n.getString("common.message.nombre.usuario.en.uso"));
            }
        }
    }

    public void agregarUsuarioRestringidoAction() {
        setMostrarAlertaGenerica(Boolean.TRUE);
        if (usuario.length() < MIN_LENGHT_USER_NAME) {
            setMensajeAlertaGenerica(i18n.getString("common.message.nombre.usuario.minlenght"));
        } else {
            respuestaDTO = userDAO.agregarUsuarioRestringido(usuario);
            if (respuestaDTO.getCodigoRespuestaOperacion().equals(Constants.CODIGO_RESPUESTA_EXITOSO)) {
                if (respuestaDTO.getUserNameToAddExist()) {
                    setMensajeAlertaGenerica(i18n.getString("common.message.nombre.usuario.ya.agregado.en.lista.restringidos"));
                } else {
                    setMensajeAlertaGenerica(i18n.getString("common.message.nombre.usuario.agregado.con.exito"));
                }
            } else {
                setMensajeAlertaGenerica(i18n.getString("common.message.nombre.usuario.no.pudo.ser.agregado"));
            }
        }
    }

    public void doCerrarDialogoGenericoCommandButtonAction() {
        setMostrarAlertaGenerica(Boolean.FALSE);
        setMensajeAlertaGenerica("");
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

    public boolean isMostrarAlertaGenerica() {
        return mostrarAlertaGenerica;
    }

    public void setMostrarAlertaGenerica(boolean mostrarAlertaGenerica) {
        this.mostrarAlertaGenerica = mostrarAlertaGenerica;
    }

    public String getMensajeAlertaGenerica() {
        return mensajeAlertaGenerica;
    }

    public void setMensajeAlertaGenerica(String mensajeAlertaGenerica) {
        this.mensajeAlertaGenerica = mensajeAlertaGenerica;
    }
}
