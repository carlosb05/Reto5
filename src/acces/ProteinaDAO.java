/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acces;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ProteinaModel;
import utils.ConexionBD;

/**
 *
 * @author Gotoxy
 */
public class ProteinaDAO {
    private Connection conn = null;
    
    public static ArrayList<ProteinaModel> obtenerProteinas(Connection conn){
        
        ArrayList<ProteinaModel> proteinas = new ArrayList();
        try{
            if(conn == null)
                conn = ConexionBD.getConnection();
            // permite hacer la consulta de todas las proteinas ingresadas en la base de datos
            String sql = "SELECT prot_id, prot_nombre, prot_porcentaje_proteina, prot_porcentaje_grasa FROM proteina;";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(sql);
            
            while (result.next()) {
                ProteinaModel proteina = new ProteinaModel(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4));
                proteinas.add( proteina );
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return proteinas;
    }

    public static ProteinaModel obtenerProteina(Connection conn, int prot_id){
        ProteinaModel proteina = null;
         try{
            if(conn == null)
                conn = ConexionBD.getConnection();
           // permite consultar la proteina correspondiente al ID ingresado como parametro 
            String sql = "SELECT prot_id, prot_nombre, prot_porcentaje_proteina, prot_porcentaje_grasa FROM proteina WHERE prot_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,prot_id);
            ResultSet result    = statement.executeQuery();
            
            result.next();
            String nombre = result.getString(2);
            int porProteina = result.getInt(3);
            int porGrasa = result.getInt(4);
            
            proteina = new ProteinaModel();
            proteina.setProt_nombre(nombre);
            proteina.setProt_porcentaje_proteina(porProteina);
            proteina.setProt_procentaje_grasa(porGrasa);
            
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return proteina;
        
    }
    public static void agregarProteina(Connection conn, ProteinaModel proteina){
        try{
            if(conn == null)
                conn = ConexionBD.getConnection();
            // permite insertar una proteina a la vez en la tabla
            String sql = "INSERT INTO proteina (prot_id, prot_nombre, prot_porcentaje_proteina, prot_porcentaje_grasa) VALUES(?,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,proteina.getProt_id());
            statement.setString(2, proteina.getProt_nombre());
            statement.setInt(3, proteina.getProt_porcentaje_proteina());
            statement.setInt(4, proteina.getProt_procentaje_grasa());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "¡El registro fue ingresado exitosamente!");
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    public static void actualizarProteina(Connection conn, ProteinaModel proteina){
        try{
            if(conn == null)
                conn = ConexionBD.getConnection();
            // permite actualizar los campos nombre, porc.proteina y porc.grasa correspodientes al ID ingresado
            String sql = "UPDATE proteina SET prot_nombre=? , prot_porcentaje_proteina=?, prot_porcentaje_grasa=? WHERE prot_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, proteina.getProt_nombre());
            statement.setInt(2, proteina.getProt_porcentaje_proteina());
            statement.setInt(3, proteina.getProt_procentaje_grasa());
            statement.setInt(4,proteina.getProt_id());
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate > 0) {
                JOptionPane.showMessageDialog(null, "¡El registro fue actualizado exitosamente!");
            }
            else{
                JOptionPane.showMessageDialog(null, "¡El registro a actualizar no existe!");
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    public static void eliminarProteina(Connection conn, int prot_id){
        try{
            if(conn == null)
                conn = ConexionBD.getConnection();
            // permite eliminar el registro de la proteina correspondiente al ID ingresado como parametro.
            String sql = "DELETE FROM proteina WHERE prot_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setInt(1, prot_id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "¡El registro fue eliminado exitosamente!");
            }
            else{
                JOptionPane.showMessageDialog(null, "¡El registro a eliminar no existe!");
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
}

