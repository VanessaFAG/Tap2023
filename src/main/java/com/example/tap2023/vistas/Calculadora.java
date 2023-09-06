package com.example.tap2023.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {
    private Scene escena;
    private VBox vBox;
    private GridPane grdTeclado;
    private TextField txtResultado;
    private Button[][]arBotones = new Button[4][4];
    private String [] arDigitos =
            { "7","4","1","0",
              "8","5","2",".",
              "9","6","3","+",
              "/","*","-","="};
    public Calculadora(){
        CrearUI();
        escena = new Scene(vBox, 200, 200);
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        int pos =0;
        grdTeclado = new GridPane();
        vBox = new VBox();
        txtResultado = new TextField("0");
        txtResultado.setAlignment(Pos.BASELINE_RIGHT);
        txtResultado.setEditable(false);
        for (int i=0; i < 4;i++){
            for (int j=0; j < 4;j++){
                arBotones[i][j]= new Button(arDigitos[pos]);
                arBotones [i][j].setPrefSize(50, 50);
                int finalPos = pos;
                if (i==3&&j==3){

                }
                else
                    arBotones [i][j].setOnAction((event)-> GExprecion(arDigitos[finalPos]));
                grdTeclado.add(arBotones[i][j], i, j);
                pos ++;
            }
        }
        vBox = new VBox(txtResultado, grdTeclado);
    }

    private void GExprecion(String simbolo){
        txtResultado.appendText(simbolo);
    }
}