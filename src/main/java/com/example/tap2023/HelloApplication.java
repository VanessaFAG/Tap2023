package com.example.tap2023;

import com.example.tap2023.components.Hilo;
import com.example.tap2023.modelos.Conexion;
import com.example.tap2023.modelos.PistaAtl;
import com.example.tap2023.vistas.Calculadora;
import com.example.tap2023.vistas.Loteria;
import com.example.tap2023.vistas.Restaurante;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    private Scene escena;
    private BorderPane borderPane;
    private MenuBar menuBar;
    private Menu menuParcial1, menuParcial2, menuSalir;
    private MenuItem mitCalculadora,mitLoteria,mitRestaurante,midPista, mitsalir;
    private void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction((event) -> new Calculadora());

        mitLoteria = new MenuItem("Loteria");
        mitLoteria.setOnAction((event)-> new Loteria());

        menuParcial1 = new Menu("Parcial 1");
        menuParcial1.getItems().addAll(mitCalculadora,mitLoteria);

        //Parcial 2
        mitRestaurante = new MenuItem("Restaurante");
        mitRestaurante.setOnAction((event)-> new Restaurante());

        midPista =  new MenuItem("Pista Atletismo");
        midPista.setOnAction((event)-> new PistaAtl());

        menuParcial2 = new Menu("Parcial 2");
        menuParcial2.getItems().addAll(mitRestaurante, midPista);

        menuSalir = new Menu("Más opciones...");
        mitsalir = new MenuItem("Salir");
        mitsalir.setOnAction((event)->Salir());
        menuSalir.getItems().add(mitsalir);

        menuBar = new MenuBar(menuParcial1,menuParcial2,menuSalir);
    }

    private void Salir() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Mensaje del Sistema");
        alerta.setHeaderText("¿Seguro que deseas salir?");
        Optional<ButtonType> option = alerta.showAndWait();
        if (option.get() == ButtonType.OK){
            System.exit(0);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {

        connectToDB();
        CrearUI();
        borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        escena = new Scene(borderPane, 200, 300);
        escena.getStylesheets().add(getClass().getResource("/estilos/estilos.css").toString());
        stage.setScene(escena);
        stage.setMaximized(true);
        stage.show();
    }
    public void connectToDB(){
        Conexion.createConnection();
        System.out.println("Conexcion establecida");
    }
    public static void main(String[] args) {
        launch();
    }
}