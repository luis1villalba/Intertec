/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webservices.services;

import com.interctec.java.webservices.dao.UserDAO;
import com.interctec.java.webservices.daoimpl.UserDAOImpl;
import com.interctec.java.webservices.dto.RespuestaDTO;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author luis.perez
 */
@WebService(serviceName = "UserServicesWs")
public class UserServicesWs {

    //@EJB ES POSBILE TAMBIEN UTIALIZAR LA INYECION DEL BEAN PARA PROCESOS MAS COMPLEJOS
    private UserDAO userDAO = new UserDAOImpl();
    
    /**
     * Web service operation
     * @param userName
     * @return RespuestaDTO
     */
    @WebMethod(operationName = "validarUserName")
    public RespuestaDTO validarUserName(String userName) {
        return userDAO.validarUserName(userName);
    }
    
    /**
     * Web service operation
     * @param userName
     * @return RespuestaDTO
     */
    @WebMethod(operationName = "agregarUserNameRestricted")
    public RespuestaDTO agregarUserNameRestricted(String userName) {
        return userDAO.agregarUserNameRestricted(userName);
    }
}
