package com.example.tap2023.components;

import com.example.tap2023.modelos.CategoriasDAO;
import com.example.tap2023.vistas.CategoriaForm;
import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCell extends TableCell<CategoriasDAO,String> {
    private Button botoncel;
    private int opc;
    private CategoriasDAO objCat;
    private TableView<CategoriasDAO> tbvCategorias;
    public ButtonCell(int opc){
        this.opc = opc;
        String txtBtn = this.opc == 1 ? "Editar": "Eliminar";
        botoncel = new Button(txtBtn);
        botoncel.setOnAction(event -> accionButton());
    }
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            this.setGraphic(botoncel);
        }
    }
    private void accionButton(){
        tbvCategorias = ButtonCell.this.getTableView();
        objCat = tbvCategorias.getItems().get(ButtonCell.this.getIndex());
        if (this.opc == 1){
            new CategoriaForm(tbvCategorias, objCat);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(".:Topicos Avanzados de Programacion:.");
            alert.setHeaderText("Confirmación del sistema");
            alert.setContentText("¿Deseas eliminar la categoria?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            objCat.eliminar();
            tbvCategorias.setItems(objCat.listarCategorias());
            tbvCategorias.refresh();
            }
        }
    }
}