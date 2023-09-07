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
    private Button[][] arBotones = new Button[4][4];
    private String[] arDigitos =
            {"7", "4", "1", "0",
             "8", "5", "2", ".",
             "9", "6", "3", "+",
             "/", "*", "-", "="};
    double num1=0, num2=0, numR=0;
    String registroActual="0", operacionFinal="";

    public Calculadora() {
        CrearUI();
        escena = new Scene(vBox, 200, 200);
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void generarExpresion(String simbolo) {
        String expresionAnterior=txtResultado.getText();
        int l=expresionAnterior.length();
        String ultimoDigito=expresionAnterior.substring(l-1);

        if(expresionAnterior.equals("Sintax Error") || expresionAnterior.equals("Math Error")){
            limpiar();}
        if(expresionAnterior.equals("0")) {
            if(simbolo.equals(".") || simbolo.equals("+") || simbolo.equals("-") || simbolo.equals("x") || simbolo.equals("/")) {}
            else {
                limpiar();
            }
        }

        String nuevoTexto="";
        if(simbolo.equals("+") || simbolo.equals("x")||simbolo.equals("/")){
            if(ultimoDigito.equals("+") || ultimoDigito.equals("-") || ultimoDigito.equals("x") || ultimoDigito.equals("/")){
                nuevoTexto=expresionAnterior.substring(0,l-1);
                txtResultado.setText(nuevoTexto);
                if(nuevoTexto.substring(l-2).equals("x")){
                    nuevoTexto=expresionAnterior.substring(0,l-2);
                    txtResultado.setText(nuevoTexto);
                }
            }
        }
        if(simbolo.equals("-")){
            if(ultimoDigito.equals("+") || ultimoDigito.equals("-")){
                nuevoTexto=expresionAnterior.substring(0,l-1);
                txtResultado.setText(nuevoTexto);
            }
        }

        if(Character.isDigit(simbolo.charAt(0)) || simbolo.equals(".")){
            switch(ultimoDigito){
                case "+":
                    calcular(ultimoDigito);
                    operacionFinal="+";
                    registroActual=""+simbolo;
                    break;
                case "-":
                    switch(expresionAnterior.substring(l-2, l-1)){
                        case "x":
                            num1=num1*(-1);
                            if(operacionFinal.equals("")){
                                calcular("+");
                            }
                            else{calcular("x");
                            }
                            operacionFinal="x";
                            registroActual ="-"+simbolo;
                            break;
                        case "/":
                            num1=num1*(-1);
                            if(operacionFinal.equals("")){
                                calcular("+");
                            }
                            else{calcular("/");
                            }
                            operacionFinal="/";
                            registroActual ="-"+simbolo;
                            break;
                        default:
                            if(operacionFinal.equals("")){
                                calcular("+");
                            }
                            else{calcular("-");
                            }
                            operacionFinal="-";
                            registroActual =""+simbolo;
                            break;
                    }
                    break;
                case "x":
                    if(operacionFinal.equals("")){
                        calcular("+");
                    }
                    else{calcular("x");
                    }
                    operacionFinal="x";
                    registroActual =""+simbolo;
                    break;
                case "/":
                    if(operacionFinal.equals("")){
                        calcular("+");
                    }
                    else{calcular("/");
                    }
                    operacionFinal="/";
                    registroActual =""+simbolo;
                    break;
                default:
                    registroActual=registroActual+simbolo;
                    break;
            }
        }

        txtResultado.appendText(simbolo);
    }
    private void calcular(String operando){
        num2=Double.parseDouble(registroActual);
        switch(operando){
            case "+":
                numR=num1+num2;
                break;
            case "-":
                numR=num1-num2;
                break;
            case "x":
                numR=num1*num2;
                break;
            case "/":
                numR=num1/num2;
                break;
            default:
                break;
        }
        num1=numR;
    }

    private void CrearUI() {
        int Finalpos = 0;
        grdTeclado = new GridPane();
        vBox = new VBox();
        txtResultado = new TextField("0");
        txtResultado.setAlignment(Pos.BASELINE_RIGHT);
        txtResultado.setEditable(false);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                arBotones[i][j] = new Button(arDigitos[Finalpos]);
                arBotones[i][j].setPrefSize(50, 50);
                int finalC1 = Finalpos;
                if (i == 3 && j == 3) {
                    arBotones[i][j].setOnAction( (event)->validarExpresion() );
                }
                else{
                    arBotones[i][j].setOnAction((event) -> generarExpresion(arDigitos[finalC1]));}
                grdTeclado.add(arBotones[i][j], i, j);
                Finalpos++;
            }
        }

        vBox = new VBox(txtResultado, grdTeclado);
    }

    private void validarExpresion() {
        String expresion = txtResultado.getText();
        int longitud = expresion.length();

        char primerSimbolo = expresion.charAt(0);
        switch(primerSimbolo) {
            case '+','-','x','/':
                txtResultado.setText("Sintax Error");
                break;
            default:
                break;

        }

        char ultimoSimbolo = expresion.charAt(longitud-1);
        switch(ultimoSimbolo) {
            case '+','-','x','/':
                txtResultado.setText("Sintax Error");
                break;
            default:
                break;

        }

        if(numR!=0){
            calcular(operacionFinal);
            txtResultado.setText(""+numR);
            operacionFinal=""; num2=0; registroActual="0";
            if(txtResultado.getText().equals("Infinity")){
                txtResultado.setText("Math Error");
            }
        }
        else{ txtResultado.setText("Math Error"); }
    }

    private void limpiar(){
        String expresion = txtResultado.getText();
        switch(expresion){
            case "Sintax Error", "0", "Math Error":
                txtResultado.setText("");
                break;
            default:
                break;
        }
        operacionFinal=""; numR=0; num1=0; num2=0; registroActual="0";
    }
}