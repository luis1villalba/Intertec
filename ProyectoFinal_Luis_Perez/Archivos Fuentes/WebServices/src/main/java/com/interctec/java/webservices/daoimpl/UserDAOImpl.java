/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webservices.daoimpl;

import com.interctec.java.webservices.constants.Constants;
import com.interctec.java.webservices.dao.UserDAO;
import com.interctec.java.webservices.dto.RespuestaDTO;
import com.interctec.java.webservices.model.UsersModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author luis.perez
 */
//@Stateless(name =  "UserDAO")
public class UserDAOImpl implements UserDAO {

    private List<String> userNamesSaved;
    private List<String> userNamesRestricted;
    UsersModel usersModel;

    @Override
    public RespuestaDTO validarUserName(String userName) {
        usersModel = new UsersModel();
        userNamesSaved = usersModel.getUserNamesSaved();
        userNamesRestricted = usersModel.getUserNamesRestricted();

        RespuestaDTO respuesta = new RespuestaDTO();
        //SE UTILIZA LA POTENCIA DE JAVA 8 PARA HACER LA BUSQUEDA EN LA LISTA
        boolean resultNameRestricted = userNamesRestricted.stream().filter(p -> p.equals(userName)).findFirst().isPresent();
        boolean resultNameSaved = userNamesSaved.stream().filter(p -> p.equals(userName)).findFirst().isPresent();

        //EL USUARIO SE ENCUENTRA ENTRE LOS RESTRINGIDOS
        if (resultNameRestricted) {
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

    @Override
    public RespuestaDTO agregarUserNameRestricted(String userName) {
        usersModel = new UsersModel();
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        //SE UTILIZA LA POTENCIA DE JAVA 8 PARA HACER LA BUSQUEDA EN LA LISTA
        //SE VALIDA SI EL USUARIO A AGREGAR YA EXISTE EN LA LISTA DE USUARIOS RESTRICTED
        boolean resultNameRestricted = userNamesRestricted.stream().filter(p -> p.equals(userName)).findFirst().isPresent();

        String codigoRespuestaOperacion;
        //SI NO ESTA PRESENTE SE AGREGA AL ARCHIVO
        if (!resultNameRestricted) {
            codigoRespuestaOperacion = usersModel.agregarUserNameRestricted(userName) == true ? Constants.CODIGO_RESPUESTA_EXITOSO : Constants.CODIGO_RESPUESTA_NO_EXITOSO;
            //EL ARCHIVO A AGREGAR NO EXISTIA PREVIAMENTE
            respuestaDTO.setUserNameToAddExist(Boolean.FALSE);
        } else {
            //EL ARCHIVO A AGREGAR YA EXISTIA EN EL ARCHIVO
            respuestaDTO.setUserNameToAddExist(Boolean.TRUE);
            codigoRespuestaOperacion = Constants.CODIGO_RESPUESTA_EXITOSO;
        }

        respuestaDTO.setCodigoRespuestaOperacion(codigoRespuestaOperacion);
        return respuestaDTO;
    }

    private List<String> crearSugerencia(String userName) {
        List<String> userNamesSugeridos = new ArrayList<>();
        Random rnd = new Random();

        boolean resultNameRestricted = false;
        int i = 0;
        do {
            String sugerencia = "";
            //SE GENERAN NUMEROS ALEATORIOS
            int numero = (int) (rnd.nextDouble() * 999 + 1);
            
            if(i <= 3 ){
                sugerencia = userName + numero;
            }
            else if(i <= 6){
                sugerencia = numero + userName;
            }
            else if(i <= 9){
                sugerencia = userName + userName + numero;
            } 
            else if(i <= 13){
                sugerencia = numero + userName + userName;
            }
            
            String sugerencia2 = sugerencia;
            //SE CREA LA SUGERENCIA Y SE VALIDA QUE LA MISMA NO SE ENCUENTRE EN LA LISTA DE RESTICTIVOS
            //TAMBIEN SE VALIDA QUE NO SE ENCUENTRE YA EN LA LISTA QUE SE ESTA CREANDO
            resultNameRestricted = userNamesRestricted.stream().filter(p -> p.equals(sugerencia2)).findFirst().isPresent();
            if (!resultNameRestricted && !userNamesSugeridos.contains(sugerencia)) {
                userNamesSugeridos.add(sugerencia);
                i++;
            } 
        } while (i < 14 && !resultNameRestricted);
        
        return userNamesSugeridos;
    }
}
