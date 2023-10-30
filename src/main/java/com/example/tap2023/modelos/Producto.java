package com.example.tap2023.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;

public class Producto{
    private int idProducto;
    private String nombre;
    private float precio;
    private Button[] btnPro;
    private String[] arrBtnT = {
            "Pastor.jpg","bistec.jpg","costilla.jpg","cabeza.jpg","tripa.jpg","adobados.jpg","tacoCho.jpg",
            "tortaPastor.jpg","torta.jpg","cocacolaPet.jpg","pepsiPET.jpg","cocacolaVid.jpg","Fanta.jpg","Sprite.jpg"};

    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public ObservableList<Producto> ListarTaqueria(){
        ObservableList <Producto> listT = FXCollections.observableArrayList();
        Producto objP;
        try {
            String query = "SELECT * FROM producto";
            Statement stmt = Conexion.conexcion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()){
                objP = new Producto();
                objP.idProducto = res.getInt("idProducto");
                objP.nombre = res.getString("nombre");
                objP.precio = res.getFloat("precio");
                listT.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();;
        }
        return listT;
    }
    public void catalogo(){
        int cont = 0;
        for (int i = 0; i < 1; i++) {
            ImageView imv;
            try {
                InputStream stream = new FileInputStream("src/main/java/com/example/tap2023/taqueria/"+ arrBtnT[cont]);
                Image imgP = new Image(stream);
                imv = new ImageView(imgP);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imv.setFitHeight(40);
            imv.setFitWidth(20);
            btnPro[i] = new Button();
            btnPro[i].setPrefSize(90, 140);
            btnPro[i].setGraphic(imv);
        }
    }

}
