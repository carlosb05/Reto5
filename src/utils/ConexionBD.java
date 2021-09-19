/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.json.simple.*;
import org.json.simple.parser.*;

//permite la conexiòn con el gestor de la base datos
public class ConexionBD {
   
    
    public static Connection getConnection() {
        JSONParser parser = new JSONParser();
        Connection conn = null;
        // conectar
        try {
            String credentials_path = System.getProperty("user.dir") + "/src/utils/credencialesBD.json";
            JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(credentials_path));
            //se realiza la lectura del arhivo JSON
            String host     = (String)jsonObject.get("db_ip");
            String port     = (String)jsonObject.get("dp_port");
            String username = (String)jsonObject.get("db_user");
            String password = (String)jsonObject.get("db_pssword");
            //Se define la Url,servidor, puerto y nombre la base de datos
            String dbURL = "jdbc:mysql://"+host+":"+port+"/restaurante" ;
            
            conn = DriverManager.getConnection(dbURL, username, password);
            
        } 
        catch( SQLException | FileNotFoundException ex ) {
            ex.printStackTrace();
        } 
        catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        
        return conn;
    }
    
    public static void main(String[] args){
        getConnection();
    }

}
