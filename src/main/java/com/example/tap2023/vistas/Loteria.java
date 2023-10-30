package com.example.tap2023.vistas;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class Loteria extends Stage {
    private Scene escena;
    private HBox hPrincipal,hBtnSelec;
    private VBox vTablilla, vMazo;
    private ImageView imvCarta;
    private GridPane[] GP = new GridPane[5];
    private NoTabla[] NumeroT = new NoTabla[5];
    private Button btnAnterior, btnSiguiente, btnInicio, btnReinicio;
    private Timer time;
    private int lugarA = 0, numero = 0;
    private static int cont;
    private static String cart;
    private static List<String> Random;
    private static boolean StartG = false;
    private String[] arImagenes = {
            "1 LM.jpg","2 LM.jpg","3 LM.jpg","4 LM.jpg","5 LM.jpg","6 LM.jpg","7 LM.jpg","8 LM.jpeg","9 LM.jpg",
            "10 LM.jpg","11 LM.jpg","12 LM.jpg","13 LM.jpg","14 LM.jpg","15 LM.jpg","16 LM.jpg","17 LM.jpg","18 LM.jpg","19 LM.jpg",
            "20 LM.jpg","21 LM.jpg","22 LM.jpg","23 LM.jpg","24 LM.jpg","25 LM.jpg","26 LM.jpg","27 LM.jpg","28 LM.jpg","29 LM.jpg",
            "30 LM.jpg","31 LM.jpg","32 LM.jpg","33 LM.jpg","34 LM.jpg","35 LM.jpg","36 LM.jpg","37 LM.jpg","38 LM.jpg","39 LM.jpg",
            "40 LM.jpg","41 LM.jpg","42 LM.jpg","43 LM.jpg","44 LM.jpg","45 LM.jpg","46 LM.jpg","47 LM.jpg","48 LM.jpg","49 LM.jpg",
            "51 LM.jpg","52 LM.jpg","53 LM.jpg","54 LM.jpg"
    };
    /*public Loteria (){
        CrearUI();

        escena = new Scene(hPrincipal,800, 600);
        this.setTitle("Loteria");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        CrearTablilla();
        CrearMazo();

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

    private void CrearMazo() {
        Image imgDorso = new Image(new File("src/main/java/com/example/tap2023/Imagenes/dorso.jpeg").toURI().toString());
        imvCarta = new ImageView(imgDorso);
        imvCarta.setFitWidth(200);
        imvCarta.setFitHeight(300);
        btnInicio = new Button("Iniciar");
        vMazo = new VBox(imvCarta, btnInicio);
    }

    private void CrearTablilla() {
        grdTablilla = new GridPane();
        int cont = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageView imv;
                try {
                    InputStream stream = new FileInputStream("src/main/java/com/example/tap2023/Imagenes/"+arImagenes[cont]);
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
}*/
    private void CrearGUI() {
        CrearBaraja();
        for (int i = 0; i < NumeroT.length; i++) {
            NumeroT[i] = new NoTabla();
            GP[i] = new GridPane();
            GP[i] = NumeroT[i].generateT(arImagenes);
        }
        btnAnterior = new Button("<-");
        btnAnterior.setPrefSize(210, 100);
        btnAnterior.setOnAction(event -> {
            if (numero == 0)
                numero = 4;
            else
                numero--;
            vTablilla.getChildren().clear();
            vTablilla.getChildren().addAll(GP[numero], hBtnSelec);
        });
        btnSiguiente = new Button("->");
        btnSiguiente.setPrefSize(210, 100);
        btnSiguiente.setOnAction(event -> {
            if (numero == 4)
                numero = 0;
            else
                numero++;
            vTablilla.getChildren().clear();
            vTablilla.getChildren().addAll(GP[numero], hBtnSelec);
        });
        hBtnSelec = new HBox(btnAnterior, btnSiguiente);
        numero = 0;
        vTablilla = new VBox(GP[numero], hBtnSelec);
        vTablilla.setSpacing(20);
        hPrincipal = new HBox(vTablilla, vMazo);
        hPrincipal.setPadding(new Insets(20));
        hPrincipal.setSpacing(20);
    }

    private void CrearBaraja() {
        Image imgDorso = new Image(new File("src/main/java/com/example/tap2023/Imagenes/dorso.jpeg").toURI().toString());
        imvCarta = new ImageView(imgDorso);
        imvCarta.setFitWidth(200);
        imvCarta.setFitHeight(350);
        btnInicio = new Button("Iniciar juego");
        btnInicio.setOnAction(event -> {
            StartG = true;
            btnInicio.setDisable(true);
            btnSiguiente.setDisable(true);
            btnAnterior.setDisable(true);
            mezclar(arImagenes);
            time = new Timer();
            time.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    jugarMazo();
                }
            }, 0, 5000);
        });
        btnReinicio = new Button("Reiniciar");
        btnReinicio.setOnAction(event -> {
            this.close();
            lugarA= 0;
            if (time != null)
                time.cancel();
            new Loteria();
        });
        vMazo = new VBox(imvCarta, btnInicio, btnReinicio);
        vMazo.setSpacing(20);
    }

    public Loteria() {
        CrearGUI();
        escena = new Scene(hPrincipal, 850, 650);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_loteria.css").toString());
        this.setTitle("Lotería");
        this.setScene(escena);
        this.setOnCloseRequest(event -> {
            if (time != null) {
                time.cancel();
            }
        });
        this.show();
    }

    public void mezclar(String[] cats) {
        Random = Arrays.asList(cats);
        Collections.shuffle(Random);
    }

    public void jugarMazo() {
        if (lugarA < Random.size()) {
            cart = Random.get(lugarA);
            Image imgCarta = new Image(getClass().getResource("src/main/java/com/example/tap2023/Imagenes" + cart).toString());
            imvCarta.setImage(imgCarta);
            lugarA++;
        } else {
            if (cont!= 16) {
                Platform.runLater(() -> {
                    lugarA = 0;
                    time.cancel();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Perdedor");
                    alert.setHeaderText("Perdiste :(");
                    alert.setContentText("Has perdido");
                    alert.showAndWait();
                });
            }
            time.cancel();
        }
    }
    class NoTabla {

        private GridPane grdT;
        private ImageView imgCart;
        private Button[][] btnofT = new Button[4][4];
        private List<String> arrCts;

        public GridPane generateT(String[] names) {
            cont = 0;
            grdT = new GridPane();
            arrCts = Arrays.asList(names);
            Collections.shuffle(arrCts);
            int pos = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    try {
                        String carta = arrCts.get(pos);
                        Image imgCartaP = new Image(getClass().getResource("src/main/java/com/example/tap2023/Imagenes" + carta).toString());
                        imgCart = new ImageView(imgCartaP);
                        imgCart.setFitHeight(130);
                        imgCart.setFitWidth(90);
                        btnofT[i][j] = new Button();
                        btnofT[i][j].setPrefSize(100, 140);
                        btnofT[i][j].setGraphic(imgCart);

                        int finalI = i;
                        int finalJ = j;
                        btnofT[i][j].setOnAction(event -> {
                            if (Loteria.StartG) {
                                if (Loteria.cart.equals(carta)) {
                                    btnofT[finalI][finalJ].setDisable(true);
                                    cont++;
                                    if (cont == 16) {
                                        Platform.runLater(() -> {
                                            lugarA = 0;
                                            time.cancel();
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("The winner is...");
                                            alert.setHeaderText("¡YOU WIN!");
                                            alert.setContentText("Has ganado");
                                            alert.showAndWait();
                                        });
                                    }
                                }
                            }
                        });
                        grdT.add(btnofT[i][j], i, j);
                        pos++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return grdT;
        }
    }
}