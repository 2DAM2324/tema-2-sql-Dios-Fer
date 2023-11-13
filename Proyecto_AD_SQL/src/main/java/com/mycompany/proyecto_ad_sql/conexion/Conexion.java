package com.mycompany.proyecto_ad_sql.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DiosFer
 */
public class Conexion {
    
    
    private static Connection conn = null;

    public Conexion (){
        this.getConection();
    }

    public Connection getConection (){
        
        if (conn==null){
            this.abrirConexion();
        }
        
        return conn;
    }
    
    private void abrirConexion (){
        try {
            String url = "jdbc:sqlite:ProyectoGames.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conexion iniciada correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
            if (conn != null) {
                conn.close();
            }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void cerrarConexion (){
        try {
            conn.close();
            conn = null;
            System.out.println("Conexion cerrada correctamente");
        } 
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
}



