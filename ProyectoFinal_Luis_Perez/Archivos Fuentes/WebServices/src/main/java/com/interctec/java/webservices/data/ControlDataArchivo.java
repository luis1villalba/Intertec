/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interctec.java.webservices.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis.perez
 */
public class ControlDataArchivo {

    public List<String> consultarArchivo(String resources) {
        //SE CAPTURA EL ARCHIVO DE RECURSOS CON LA INFORMACION 
        //DE LOS DE LOS USER NAMES SALVADOS 
        InputStream input = null;
        List<String> userNamesSaved = new ArrayList<String>();
        try {
            String urlArchivo = this.getClass().getClassLoader().getResource(resources).getPath();
            input = new FileInputStream(new File(urlArchivo));
            //input = this.getClass().getClassLoader().getResourceAsStream(resources);
            System.out.println("url archivo consulta: "+urlArchivo);
            BufferedReader r = new BufferedReader(new InputStreamReader(input));
            // reads each line
            String linea;
            while ((linea = r.readLine()) != null) {
                userNamesSaved.add(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(ControlDataArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(ControlDataArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return userNamesSaved;
    }

    public boolean agregarUserNameRestricted(String userNameRestricted) {
        boolean respuesta = true;
        BufferedWriter bw = null;
        try {
            String urlArchivo = this.getClass().getClassLoader().getResource("UsersNamesRestricted.txt").getPath();
            bw = new BufferedWriter(new FileWriter(urlArchivo, true));
            bw.newLine();
            bw.append(userNameRestricted);
            bw.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlDataArchivo.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = false;
        } catch (IOException ex) {
            Logger.getLogger(ControlDataArchivo.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = false;
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(ControlDataArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;
    }
}
