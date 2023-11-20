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
public class InventarioCompartido {
    
    
    /********************************
     ********   Atributos   *********
     ********************************/
    
    private String IdInventario; //genearda automaticamente o cargada
    private int SlotsMaximos;
    private int SlotsOcupados;
    ArrayList<Jugador> jugadoresAccesoInventario;

    /********************************
     *******   Constructor   ********
     ********************************/
    

    
    /**
     * @brief Constructor de Inventario con id existente (constructor de carga)
     * @post se actualizara la variable utilizada para la generacion de los id
     * @param String Id
     * @param int SlotsMaximos
     * @param int SlotsOcupados
     */
    public InventarioCompartido(String Id, int SlotsMaximos, int SlotsOcupados) {
        setIdInventario(Id);
        setSlotsMaximos(SlotsMaximos);
        setSlotsOcupados(SlotsOcupados);
        jugadoresAccesoInventario = new ArrayList<Jugador>();

        
    }
    
    /**
     * @brief Constructor de Inventario generando id a partir de la variable 
     * @pre se deberan de haber cargado los datos para que se actualize la variable generadora de id
     * @post se actualizara la variable utilizada para la generacion de los id
     * @param int SlotsMaximos
     * @param int SlotsOcupados
     */
    public InventarioCompartido(int SlotsMaximos, int SlotsOcupados) {

        setIdInventario ("");
        setSlotsMaximos(SlotsMaximos);
        setSlotsOcupados(SlotsOcupados);
        jugadoresAccesoInventario = new ArrayList<Jugador>();
        
    }

    /********************************
     **********   Set's   ***********
     ********************************/
    
    public void setJugadorConAcceso (Jugador j){
        jugadoresAccesoInventario.add (j);
    }
    
    public void setIdInventario (String IdInventario){
        this.IdInventario=IdInventario;
    }

    public void setSlotsMaximos(int SlotsMaximos) {
        this.SlotsMaximos = SlotsMaximos;
    }
    
    public void setSlotsOcupados(int SlotsOcupados) {
        this.SlotsOcupados = SlotsOcupados;
    }
    


    /********************************
     **********   Get's   ***********
     ********************************/
    
    public String getIdInventario (){
        return IdInventario;
    }

    public int getSlotsMaximos() {
        return SlotsMaximos;
    }
    
    public int getSlotsOcupados() {
        return SlotsOcupados;
    }
    

    
    public Jugador getJugadorConAcceso (int pos){
        return jugadoresAccesoInventario.get (pos);
    }
    
    public ArrayList<Jugador> getJugadores (){
        return jugadoresAccesoInventario;
    }
    
    /**
     * @brief Quitar el acceso de un jugador a un inventario
     * @pre debera de existir el id del jugado y tener acceso al inventario
     * @post se actualizaran los jugadores con acceso al inventario
     * @param String IdPlayer
     */
    public void removerAcceso (String IdPlayer){
        int i=0;
        for (i=0; i < this.getJugadores().size() && !this.getJugadorConAcceso(i).getIdPlayer().equals(IdPlayer); i++){
        }
        this.getJugadores().remove(i);
    }
}
