/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//Se crean los atributos correspondientes a la tabla proteina
public class ProteinaModel {
    private Integer prot_id, prot_porcentaje_proteina, prot_procentaje_grasa;
    private String prot_nombre;
// se crea el contructor de la clase ProteinaModel
    public ProteinaModel(int prot_id, String prot_nombre, int prot_porcentaje_proteina, int prot_procentaje_grasa ) {
        this.prot_id = prot_id;
        this.prot_porcentaje_proteina = prot_porcentaje_proteina;
        this.prot_procentaje_grasa = prot_procentaje_grasa;
        this.prot_nombre = prot_nombre;
    }

    public ProteinaModel() {
        
    }
// Se generan getter y setters
    public Integer getProt_id() {
        return prot_id;
    }

    public void setProt_id(Integer prot_id) {
        this.prot_id = prot_id;
    }

    public Integer getProt_porcentaje_proteina() {
        return prot_porcentaje_proteina;
    }

    public void setProt_porcentaje_proteina(Integer prot_porcentaje_proteina) {
        this.prot_porcentaje_proteina = prot_porcentaje_proteina;
    }

    public Integer getProt_procentaje_grasa() {
        return prot_procentaje_grasa;
    }

    public void setProt_procentaje_grasa(Integer prot_procentaje_grasa) {
        this.prot_procentaje_grasa = prot_procentaje_grasa;
    }

    public String getProt_nombre() {
        return prot_nombre;
    }

    public void setProt_nombre(String prot_nombre) {
        this.prot_nombre = prot_nombre;
    }

    
}
