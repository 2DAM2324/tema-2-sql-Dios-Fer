package com.mycompany.proyecto_ad_sql.conexion;

import com.mycompany.proyecto_ad_sql.modelos.Servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 *
 * @author DiosFer
 */
public class Conexion {
    
    private ArrayList<Servidor> servidores_conexion;
    
    private static Connection conn = null;

    public Conexion (){
        this.getConection();
        servidores_conexion = new ArrayList<Servidor>();

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
    
    
    
    
    public ArrayList<Servidor> getServidoresSQL (){

        String cons = "SELECT * FROM servidores;";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Servidor s;

        servidores_conexion.clear();
        
        try{                                
                                
            consulta = this.getConection().prepareStatement(cons);

            resultado = consulta.executeQuery();
            while(resultado.next()){

                s = new Servidor ("S-"+resultado.getString(1).toString(), resultado.getString(2));
                servidores_conexion.add(s);
                
            }
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            if (consulta != null){
                try{
                   consulta.close();
                    resultado.close(); 
                }
                catch(SQLException sqle2){
                    sqle2.printStackTrace();
                }
                
            }
        }
        return servidores_conexion;
        
    }
    
}



