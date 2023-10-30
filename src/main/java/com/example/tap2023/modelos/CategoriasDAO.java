package com.example.tap2023.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CategoriasDAO {
    private int idCategoria;
    private String nomCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public void insertar(){
        try{
        String query = "INSERT INTO tbcategorias"+"(nomCategoria) VALUES('"+this.nomCategoria+"')";
        Statement stmt = Conexion.conexcion.createStatement();
        stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void actualizar (){
        try{
            String query = "UPDATE tbcategorias SET nomCategoria = '"+ this.nomCategoria+"' " +"WHERE idCategoria = "+ this.idCategoria;
            Statement stmt = Conexion.conexcion.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void eliminar(){
        try {
            String query = "DELETE FROM tbCategorias WHERE idCategoria =" + this.idCategoria;
            Statement stmt = Conexion.conexcion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();;
        }
    }
    public ObservableList<CategoriasDAO> listarCategorias(){
        ObservableList <CategoriasDAO> listCat = FXCollections.observableArrayList();
        // array normal es la cantidad de objeto reservada es estatica se le indica cantidad  y el array list es indefinidas
        CategoriasDAO objC;
        try {
            String query = "SELECT * FROM tbcategorias";
            Statement stmt = Conexion.conexcion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()){
                objC = new CategoriasDAO();
                objC.idCategoria = res.getInt("idCategoria");
                objC.nomCategoria = res.getString("nomCategoria");
                listCat.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();;
        }
        return listCat;
    }
}