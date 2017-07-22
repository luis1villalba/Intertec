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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author luis.perez
 */
@Stateless(mappedName = "UserDAOImpl")
public class UserDAOImpl implements UserDAO {
    private ResourceBundle i18n = ResourceBundle.getBundle("conexiones");
    
    //SE CAPTURA LA RUTA DEL WSDL DEL ARCHIVO DE PROPIEDADES
    private String URL_WSDL = i18n.getString("ruta.wsdl") + "UserServicesWs?wsdl";
    
    @Override
    public RespuestaDTO validarUserName(String userName) {
        RespuestaDTO respuesta = new RespuestaDTO();
        try {
            com.interctec.java.webpage.ws.services.UserServicesWs_Service service = new UserServicesWs_Service(new URL(URL_WSDL));

            try { // Call Web Service Operation
                com.interctec.java.webpage.ws.services.UserServicesWs port = service.getUserServicesWsPort();
                com.interctec.java.webpage.ws.services.RespuestaDTO result = port.validarUserName(userName);

                // SE SETEA LA RESPUESTA A OBJETOS DE LA CAPA WEB
                respuesta.setUserNames(new ArrayList<String>());
                respuesta.setValid(result.isValid());
                respuesta.setRestricted(result.isRestricted());
                respuesta.setCodigoRespuestaOperacion(result.getCodigoRespuestaOperacion());
                for (String userNameResult : result.getUserNames()) {
                    respuesta.getUserNames().add(userNameResult);
                }
            } catch (Exception ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO agregarUsuarioRestringido(String userName) {
        RespuestaDTO respuesta = new RespuestaDTO();
        try {
            com.interctec.java.webpage.ws.services.UserServicesWs_Service service = new UserServicesWs_Service(new URL(URL_WSDL));

            try { // Call Web Service Operation
                com.interctec.java.webpage.ws.services.UserServicesWs port = service.getUserServicesWsPort();
                com.interctec.java.webpage.ws.services.RespuestaDTO result = port.agregarUserNameRestricted(userName);
                // SE SETEA LA RESPUESTA A OBJETOS DE LA CAPA WEB
                //PARA ESTE CASO SOLO ME INTERESA SABRE SI FUE EXITOSO O NO LA OPERACION
                //TAMBIEN SI EL USUARIO YA EXISTIA Y NO SE AGREGO
                respuesta.setCodigoRespuestaOperacion(result.getCodigoRespuestaOperacion());
                respuesta.setUserNameToAddExist(result.isUserNameToAddExist());
            } catch (Exception ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
