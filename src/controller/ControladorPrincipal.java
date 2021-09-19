/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import acces.ProteinaDAO;
import view.JFramePrincipal;
import model.ProteinaModel;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Gotoxy
 */
public class ControladorPrincipal {
    JFramePrincipal jFramePrincipal;
    Connection conn;

    public ControladorPrincipal(JFramePrincipal jFramePrincipal) {
        this.jFramePrincipal = jFramePrincipal;
        conn = utils.ConexionBD.getConnection();
    }
// se recibe la acciòn del boton crear desde la vista y se actualiza la vista desde el controlador
    public void btnCrear() {
        try{
        ProteinaModel proteina = new ProteinaModel();
        proteina.setProt_id(Integer.parseInt(jFramePrincipal.getTxtId().getText()));
        proteina.setProt_nombre(jFramePrincipal.getTxtNombre().getText());
        proteina.setProt_porcentaje_proteina(Integer.parseInt(jFramePrincipal.getTxtPorcProteina().getText()));
        proteina.setProt_procentaje_grasa(Integer.parseInt(jFramePrincipal.getTxtPorcGrasa().getText()));
        
        
            ProteinaDAO.agregarProteina(conn, proteina);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ingrese los datos correspondientes para crear la nueva proteína");
        }
        }
// se recibe la acciòn del boton actualizar desde la vista y se actualiza la vista desde el controlador
    public void btnActualizar() {
        try{
        ProteinaModel proteina = new ProteinaModel();
        proteina.setProt_id(Integer.parseInt(jFramePrincipal.getTxtId().getText()));
        proteina.setProt_nombre(jFramePrincipal.getTxtNombre().getText());
        proteina.setProt_porcentaje_proteina(Integer.parseInt(jFramePrincipal.getTxtPorcProteina().getText()));
        proteina.setProt_procentaje_grasa(Integer.parseInt(jFramePrincipal.getTxtPorcGrasa().getText()));
        
        
            ProteinaDAO.actualizarProteina(conn, proteina);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Busque primero la proteína a actualizar o ingrese los datos de la proteína a actualizar");
        }
    }
// se recibe la acciòn del boton buscar desde la vista y se actualiza la vista desde el controlador
    public void btnBuscar() {
        try{
            int id = (Integer.parseInt(jFramePrincipal.getTxtId().getText()));
            ProteinaModel proteina = ProteinaDAO.obtenerProteina(conn, id);
            jFramePrincipal.getTxtNombre().setText(proteina.getProt_nombre().toString());
            jFramePrincipal.getTxtPorcProteina().setText(proteina.getProt_porcentaje_proteina().toString());
            jFramePrincipal.getTxtPorcGrasa().setText(proteina.getProt_procentaje_grasa().toString());
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ingrese el Id de proteína a buscar");
        }
    }
// se recibe la acciòn del boton eliminar desde la vista y se actualiza la vista desde el controlador
    public void btnEliminar() {
        try{
            int id = (Integer.parseInt(jFramePrincipal.getTxtId().getText()));
            ProteinaDAO.eliminarProteina(conn, id);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ingrese Id de proteína a eliminar");
        }
    }

    public TableModel btnConsultarProteinas() {
        //Se setean titulos de la tabla y se cargan los registro de la base de datos
        String [] titulos ={"Id", "Nombre", "Porcentaje proteina", "Porcentaje Grasa"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        ArrayList<ProteinaModel> proteinas= ProteinaDAO.obtenerProteinas(conn);
        for(ProteinaModel pro: proteinas){
            String[] registro = new String[4];
            registro[0] = String.valueOf(pro.getProt_id());
            registro[1] = pro.getProt_nombre();
            registro[2] = String.valueOf(pro.getProt_porcentaje_proteina());
            registro[3] = String.valueOf(pro.getProt_procentaje_grasa());
            modelo.addRow(registro);
        }
        return modelo;
    }
    }