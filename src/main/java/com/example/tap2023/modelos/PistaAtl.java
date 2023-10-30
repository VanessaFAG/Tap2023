package com.example.tap2023.modelos;

import com.example.tap2023.components.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PistaAtl extends Stage {
    private ProgressBar[] pgbCorredores = new ProgressBar[6];
    private VBox vb;
    private Button btnStr;
    private Hilo[] thrCor = new Hilo[6];
    private String[] listaC = {
            "Yuno", "Julieta", "Vic", "Gus",
            "Van", "Braulio"
    };
    private Scene escena;
    public PistaAtl(){
        CrearUI();
        escena = new Scene(vb);
        this.setTitle("Pista de Atletismo :D");
        this.setScene(escena);
    }

    private void CrearUI() {
        vb = new VBox();
        for(int i = 0; i < pgbCorredores.length; i++){
            pgbCorredores[i] = new ProgressBar(0);
            thrCor[i] = new Hilo(listaC[i], pgbCorredores[i]);
            vb.getChildren().add(pgbCorredores[i]);
        }
        btnStr = new Button("Iniciar Carrera :b");
        btnStr.setOnAction( event -> {
            for (int i = 0; i < pgbCorredores.length; i++){
            thrCor[i].start();}
        });
        vb.getChildren().add(btnStr);
        this.show();
    }
}