package com.example.tap2023.components;

import javafx.scene.control.ProgressBar;

import java.util.Random;

public class Hilo extends Thread{
    private ProgressBar pgbCorredores;
    public Hilo(String nombre, ProgressBar pgb){
        //super(nombre); Es lo mismo que el this
        this.setName(nombre);
        this.pgbCorredores = pgb;
    }
    @Override
    public void run(){
        try {
            double avance = 0;
            super.run();
            while (avance <= 1){
                sleep((long) (Math.random() * 1500));
                avance += Math.random()/10;
                pgbCorredores.setProgress(avance);
            }
        }catch (Exception e){}
    }
}