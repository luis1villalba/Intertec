/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webpage.dao;

import com.interctec.java.webpage.dto.RespuestaDTO;

/**
 *
 * @author luis.perez
 */
public interface UserDAO {
    public RespuestaDTO validarUserName(String userName);
    public RespuestaDTO agregarUsuarioRestringido(String userName);
}
