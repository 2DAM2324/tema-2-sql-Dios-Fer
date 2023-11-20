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
public class Partida {
    
    
    /********************************
     ********   Atributos   *********
     ********************************/
    
    String IdPartida; //genearda automaticamente o cargada
    int NumEspectadores;
    Servidor ServerPartida;
    ArrayList<Jugador> jugadores; 

    /********************************
     *******   Constructor   ********
     ********************************/
    
    
    
    /**
     * @brief Constructor de Partida con id existente (constructor de carga)
     *        Los jugadores se introduciran por medio de set's
     * @post se actualizara la variable utilizada para la generacion de los id
     * @param String Id
     * @param String NickName
     * @param int nivel
     */
    public Partida(String Id, int NumEspectadores, Servidor s) {
        setIdPartida (Id);
        setNumEspectadores (NumEspectadores);
        setServerPartida (s);
        setJugadores (new ArrayList<Jugador>());
        
        
    }
    
    /**
     * @brief Constructor de Partida generando id a partir de la variable 
     *        Los jugadores se introduciran por medio de set's
     * @pre se deberan de haber cargado los datos para que se actualize la variable generadora de id
     * @post se actualizara la variable utilizada para la generacion de los id
     * @param int NumEspectadores
     * @param Servidor s -> servidor previamente cargado en el sistema
     */
    public Partida(int NumEspectadores, Servidor s) {

        setIdPartida("");

        setNumEspectadores (NumEspectadores);
        setServerPartida (s);

        setJugadores (new ArrayList<Jugador>());

    }
    
    /********************************
     **********   Set's   ***********
     ********************************/
    
    public void setIdPartida(String IdPartida) {
        this.IdPartida=IdPartida;
    }

    public void setNumEspectadores(int NumEspectadores) {
        this.NumEspectadores = NumEspectadores;
    }
    
    public void setServerPartida(Servidor s) {
        this.ServerPartida = s;
    }
    
    public void setJugadores(ArrayList<Jugador> j) {
        this.jugadores=j;
    }
    
    public void setUnJugador (Jugador j){
        this.jugadores.add(j);
    }


    
    /********************************
     **********   Get's   ***********
     ********************************/
    
    public String getIdPartida() {    
        return IdPartida;
    }

    public int getNumEspectadores() {
        return NumEspectadores;
    }
    
    public Servidor getServerPartida() {
        return ServerPartida;
    }

    public Jugador getJugadorpos(int pos) {
        return this.jugadores.get(pos);
    }
    
    public ArrayList<Jugador> getJugadores (){
        return this.jugadores;
    }
    

    
}
