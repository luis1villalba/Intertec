/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webpage.daoimpl;

import com.interctec.java.webpage.dao.UserDAO;
import com.interctec.java.webpage.dto.RespuestaDTO;
import com.interctec.java.webpage.ws.services.UserServicesWs_Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author luis.perez
 */
@Stateless(mappedName = "UserDAOImpl")
public class UserDAOImpl implements UserDAO {

    private URL rutaWsdl = null;

    @Override
    public RespuestaDTO validarUserName(String userName) {
        RespuestaDTO respuesta = new RespuestaDTO();

        try {
            rutaWsdl = new URL("http://localhost:8080/WebServices/UserServicesWs?wsdl");
        } catch (MalformedURLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        com.interctec.java.webpage.ws.services.UserServicesWs_Service service = new UserServicesWs_Service(rutaWsdl);

        try { // Call Web Service Operation
            com.interctec.java.webpage.ws.services.UserServicesWs port = service.getUserServicesWsPort();
            com.interctec.java.webpage.ws.services.RespuestaDTO result = port.validarUserName(userName);

            // SE SETEA LA RESPUESTA A OBJETOS DE LA CAPA WEB
            respuesta.setUserNames(new ArrayList<String>());
            respuesta.setValid(result.isValid());
            respuesta.setRestricted(result.isRestricted());
            for (String userNameResult : result.getUserNames()) {
                respuesta.getUserNames().add(userNameResult);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
