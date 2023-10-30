package com.example.tap2023.vistas;

import com.example.tap2023.modelos.CategoriasDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CategoriaForm extends Stage {
    private Scene escena;
    private HBox hb;
    private TextField txtNameCat;
    private Button btnGuardar, btnSelec;
    private CategoriasDAO objCatDAO;
    private int Nocant = 0;
    TableView<CategoriasDAO> tbvCategorias;

    public CategoriaForm(TableView<CategoriasDAO> tbvCat, CategoriasDAO objCatDAO) {
        this.tbvCategorias = tbvCat;
        this.objCatDAO = objCatDAO == null ? new CategoriasDAO() : objCatDAO;
        CrearUI();
        escena = new Scene(hb);
        this.setTitle("Gestion de Categorias");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI() {
        txtNameCat = new TextField();
        txtNameCat.setText(objCatDAO.getNomCategoria());
        txtNameCat.setPromptText("Nombre de la categoria");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> saveCat());
        hb = new HBox(txtNameCat, btnGuardar);
        hb.setSpacing(10);
        hb.setPadding(new Insets(10));
    }

    private void saveCat() {
        objCatDAO.setNomCategoria(txtNameCat.getText());
        if (objCatDAO.getIdCategoria() > 0) {
            objCatDAO.actualizar();
        } else {
            objCatDAO.insertar();
        }
        tbvCategorias.setItems(objCatDAO.listarCategorias());
        tbvCategorias.refresh();
        this.close();
    }
}
