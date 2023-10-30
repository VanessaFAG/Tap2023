package com.example.tap2023.vistas;

import com.example.tap2023.modelos.Producto;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Restaurante extends Stage {
    private VBox vBox, vbImg;
    private TableView<Producto> tbvP;
    private Producto ProDAO;
    public Restaurante(){
        CrearUI();
        Panel panel = new Panel("Taqueria Mishi-ote");
        panel.getStyleClass().add("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(vBox);
        panel.setBody(content);

        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        this.setTitle("BootstrapFX");
        this.setScene(scene);
        this.sizeToScene();
        this.show();
    }
    private void CrearUI(){
        ProDAO = new Producto();
        tbvP = new TableView<Producto>();
        CreateTable();
        vBox = new VBox(tbvP);

    }
    /*private void CreateTable(){
        TableColumn<CategoriasDAO,Integer> tbcIdCat = new TableColumn<CategoriasDAO,Integer>("ID");
        tbcIdCat.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        TableColumn<CategoriasDAO,String> tbcNomCat = new TableColumn<CategoriasDAO,String>("Categoria");
        tbcNomCat.setCellValueFactory(new PropertyValueFactory<>("nomCategoria"));

        TableColumn<CategoriasDAO,String> tbcEditar = new TableColumn<CategoriasDAO,String>("Ediar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
                    @Override
                    public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> param) {
                        return new ButtonCell(1);
                    }
                }
        );
        TableColumn<CategoriasDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
                    @Override
                    public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> param) {
                        return new ButtonCell(2);
                    }
                }
        );
        tbvCat.getColumns().addAll(tbcIdCat,tbcNomCat,tbcEditar,tbcEliminar);
        tbvCat.setItems(CatDAO.listarCategorias());
    }*/
    private void CreateTable(){
        TableColumn<Producto,Integer> tbcIdPro = new TableColumn<>("ID");
        tbcIdPro.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

        TableColumn<Producto,String> tbcNomP = new TableColumn<>("Producto");
        tbcNomP.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Producto,Float> tbcPrice = new TableColumn<>("Precio");
        tbcPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tbvP.getColumns().addAll(tbcIdPro,tbcNomP,tbcPrice);
        tbvP.setItems(ProDAO.ListarTaqueria());
    }
}