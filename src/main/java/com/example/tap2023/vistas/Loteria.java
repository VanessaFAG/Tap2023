package com.example.tap2023.vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class Loteria extends Stage {
    private Scene escena;
    private HBox hPrincipal,hBtnSelec;
    private VBox vTablilla, vMazo;
    private Image imgMazo;
    private Button[][] argBtnTablilla = new Button[4][4];
    private GridPane grdTablilla;
    private Button btnAnterior, btnSiguiente, btnInicio;
    public Loteria (){
        CrearUI();
        CrearMazo();

        escena = new Scene(hPrincipal,800, 600);
        this.setTitle("Loteria");
        this.setScene(escena);
        this.show();
    }

    private void CrearMazo() {
        Image imgDorso = new Image(new File());

    }

    private void CrearUI() {
        CrearTablilla();

        btnAnterior = new Button("<");
        btnAnterior.setPrefSize(200,100);
        btnSiguiente = new Button(">");
        btnSiguiente.setPrefSize(200,100);
        hBtnSelec = new HBox(btnAnterior,btnSiguiente);
        vTablilla =new VBox(grdTablilla, hBtnSelec);
        vTablilla.setSpacing(20);

        hPrincipal = new HBox(vTablilla, vMazo);
        hPrincipal.setPadding(new Insets(20));
    }

    private void CrearTablilla() {
        grdTablilla = new GridPane();
        //int cont = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageView imv;
                try {
                    InputStream stream = new FileInputStream("src/main/java/com/example/tap2023/Imagenes/botella.jpeg");
                    Image imgCartaP = new Image(stream);
                    imv = new ImageView(imgCartaP);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                imv.setFitHeight(130);
                imv.setFitWidth(90);
                argBtnTablilla[i][j] = new Button();
                argBtnTablilla[i][j].setPrefSize(90, 140);
                argBtnTablilla[i][j].setGraphic(imv);
                grdTablilla.add(argBtnTablilla[i][j],i,j);
            }
        }
    }
}