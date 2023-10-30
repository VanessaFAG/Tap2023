package com.example.tap2023.modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static String server = "localhost";
    private static String user = "topicosAP";
    private static String passW = "1234567890";
    private static String db = "restaurant";
    public static Connection conexcion;
    public static void createConnection(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conexcion = DriverManager.getConnection("jdbc:mariadb://"+server+":3306/"+db,user,passW);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
