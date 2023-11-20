/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_ad_sql.modelos;

import java.util.ArrayList;

/**
 *
 * @author DiosFer
 */
public class Jugador {
    
    
    /********************************
     ********   Atributos   *********
     ********************************/
    
    private String IdPlayer;  //genearda automaticamente o cargada
    private String NickName; 
    private int nivel; 
    ArrayList<InventarioCompartido> inventariosConAcceso;
    
    /********************************
     *******   Constructor   ********
     ********************************/
    

    /**
     * @brief Constructor de Jugador con id existente (constructor de carga)
     * @post se actualizara la variable utilizada para la generacion de los id
     * @param String Id
     * @param String NickName
     * @param int nivel
     */
    public Jugador(String Id, String NickName, int nivel) {
        setIdPlayer (Id);
        setNickName (NickName);
        setNivel (nivel);
        inventariosConAcceso = new ArrayList<InventarioCompartido>();
        
        
    }
    
    /**
     * @brief Constructor de Jugador generando id a partir de la variable 
     * @pre se deberan de haber cargado los datos para que se actualize la variable generadora de id
     * @post se actualizara la variable utilizada para la generacion de los id
     * @param String NickName
     * @param int nivel
     */
    public Jugador(String NickName, int nivel) {
        
        setIdPlayer("");
        setNickName (NickName);
        setNivel (nivel);
        inventariosConAcceso = new ArrayList<InventarioCompartido>();
    }
    
    
    
    /********************************
     **********   Set's   ***********
     ********************************/
    
    public void setIdPlayer(String IdPlayer) {
        this.IdPlayer = IdPlayer;
    }
    
    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
    
    public void setInventarioConAcceso (InventarioCompartido i){
        inventariosConAcceso.add (i);
    }

    /********************************
     **********   Get's   ***********
     ********************************/
    
    public String getIdPlayer() {
        return IdPlayer;
    }
    
    public String getNickName() {
        return NickName;
    }

    public int getNivel() {
        return nivel;
    }
    

    
    public InventarioCompartido getInventarioConAcceso (int pos){
        return inventariosConAcceso.get (pos);
    }
    
    public ArrayList<InventarioCompartido> getInventarios (){
        return inventariosConAcceso;
    }
    
    /**
     * @brief Quitar el acceso de un jugador a un inventario
     * @pre debera de existir el id del inventario y el jugador tener acceso al el
     * @post se actualizaran los inventarios a los que tieneacceso el jugador
     * @param String IdInventario
     */
    public void removerInventario (String IdInventario){
        int i=0;
        for (i=0; i < this.getInventarios().size() && !this.getInventarioConAcceso(i).getIdInventario().equals(IdInventario); i++){
        }
        this.getInventarios().remove(i);
    }

}
