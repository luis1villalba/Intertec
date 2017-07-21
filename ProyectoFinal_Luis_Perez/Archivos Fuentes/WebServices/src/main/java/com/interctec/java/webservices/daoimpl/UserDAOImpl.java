/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webservices.daoimpl;

import com.interctec.java.webservices.dao.UserDAO;
import com.interctec.java.webservices.dto.RespuestaDTO;
import com.interctec.java.webservices.model.UsersModel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author luis.perez
 */
@Stateless(name =  "UserDAO")
public class UserDAOImpl implements UserDAO {

    private List<String> userNamesSaved;
    private List<String> userNamesRestricted;

    public UserDAOImpl() {
        //SE INICIALIZA LA LISTA CON LA DATA DEL MODELO
        //ESTE MODELO PODRIA SER CAMBIADO POR UNA CONEXION A BD
        userNamesSaved      = new UsersModel().getUserNamesSaved();
        userNamesRestricted = new UsersModel().getUserNamesRestricted();
    }

    @Override
    public RespuestaDTO validarUserName(String userName) {
        RespuestaDTO respuesta = new RespuestaDTO();
        //SE UTILIZA LA POTENCIA DE JAVA 8 PARA HACER LA BUSQUEDA EN LA LISTA
        boolean resultNameRestricted = userNamesRestricted.stream().filter(p -> p.equals(userName)).findFirst().isPresent();
        boolean resultNameSaved       = userNamesSaved.stream().filter(p -> p.equals(userName)).findFirst().isPresent();
        
        //EL USUARIO SE ENCUENTRA ENTRE LOS RESTRINGIDOS
        if(resultNameRestricted){
            respuesta.setValid(Boolean.FALSE);
            respuesta.setRestricted(Boolean.TRUE);
            return respuesta;
        }

        //FUE ENCONTRADO EL VALOR EN LA LISTA
        respuesta.setRestricted(Boolean.FALSE);
        respuesta.setUserNames(new ArrayList<String>());
        if (resultNameSaved) {
            respuesta.setValid(Boolean.FALSE);
            //SE CREAN LAS SUGERENCIAS EN EL CASO QUE EXISTA
            respuesta.setUserNames(crearSugerencia(userName));
        } else {
            respuesta.setValid(Boolean.TRUE);
        }
        return respuesta;
    }

    private List<String> crearSugerencia(String userName) {
        List<String> userNamesSugeridos = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            userNamesSugeridos.add(userName + i);
        }

        for (int i = 0; i < 4; i++) {
            userNamesSugeridos.add(i + userName);
        }

        for (int i = 0; i < 4; i++) {
            userNamesSugeridos.add(userName + userName + i);
        }

        for (int i = 0; i < 4; i++) {
            userNamesSugeridos.add(i + userName + userName);
        }
        return userNamesSugeridos;
    }
}
