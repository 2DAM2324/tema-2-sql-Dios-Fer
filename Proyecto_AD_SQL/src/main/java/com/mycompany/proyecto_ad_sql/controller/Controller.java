package com.mycompany.proyecto_ad_sql.controller;

import com.mycompany.proyecto_ad_sql.conexion.Conexion;
import com.mycompany.proyecto_ad_sql.modelos.InventarioCompartido;
import com.mycompany.proyecto_ad_sql.modelos.Jugador;
import com.mycompany.proyecto_ad_sql.modelos.Partida;
import com.mycompany.proyecto_ad_sql.modelos.Servidor;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 *
 * @author DiosFer
 */
public class Controller {

    private Conexion conn;
    
    private ArrayList<Servidor> servidores_sistema;
    private ArrayList<InventarioCompartido> inventarios_sistema;
    private ArrayList<Partida> partidas_sistema;
    private ArrayList<Jugador> jugadores_sistema;

    
     /**
     * @brief Constructor del controlador con la lectura inicial de la base de datos
     */
    public Controller (String dbName)  throws SQLException {
            
            conn = new Conexion(dbName) ;
            
            servidores_sistema = conn.getServidoresSQL();
            
            inventarios_sistema = conn.getInventariosSQL();
            
            jugadores_sistema = conn.getJugadoresSQL();

            partidas_sistema = conn.getPartidasSQL();
            
    }
    
    public void cerrar_conexion ()  throws SQLException{
        conn.cerrarConexion();
    }
    
    /*////////////////////////////////////////////////////////
    ///////////////////      Servidor      //////////////////
    //////////////////////////////////////////////////////*/
     
    /**
     * @brief Crear un servidor por medio de sus atributos previamente obtenidos
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se creara y guardara un servidor 
     * @param String region
     */
    public void crearServidor (String region) throws SQLException{
        Servidor s = new Servidor (region);
        conn.InsertarServidorSQL(s);
        servidores_sistema.add (s);
    }
    
    /**
     * @brief Modificara un servidor existente obtenido con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se modificara y guardara un servidor 
     * @param String Id -> id del servidor a modificar
     * @param String region -> nuevo dato
     */
    public void ModificarServidor (String Id, String region) throws SQLException{
        int i=0;
        for (i=0; i<servidores_sistema.size() && !servidores_sistema.get(i).getIdServer().equals(Id); i++){
        }
        conn.ModificarServidorSQL(servidores_sistema.get(i), region);

    }
    
    /**
     * @brief Eliminara un servidor existente obtenido con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se eliminara y se guardaran los cambios
     * @param String Id -> id del servidor a eliminar
     */
    public void EliminarServidor (String Id) throws SQLException{
        int i=0;
        for (i=0; i<servidores_sistema.size() && !servidores_sistema.get(i).getIdServer().equals(Id); i++){
        }
        conn.EliminarServidorSQL(Id);
        servidores_sistema.remove(i);
        
    }
    
    public ArrayList<Servidor> getServidores_sistema() {
        return servidores_sistema;
    }
    
    /*////////////////////////////////////////////////////////
    ///////////////////     Inventario     //////////////////
    //////////////////////////////////////////////////////*/
    
    /**
     * @brief Crear un inventario por medio de sus atributos previamente obtenidos
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se creara y guardara un inventario 
     * @param int slotsMaximos
     * @param int slotsOcupados
     * @param String[] idsJugadorInventarios -> jugadores con acceso a ese inventario
     */
    public void crearInventario (int slotsMaximos, int slotsOcupados, String[] idsJugadorInventarios) throws SQLException{
        InventarioCompartido inv = new InventarioCompartido (slotsMaximos, slotsOcupados);
        
        
        if (!idsJugadorInventarios[0].trim().equals("")){
            for (String idJ : idsJugadorInventarios){
                inv.setJugadorConAcceso(getJugadorById(idJ));
                getJugadorById(idJ).setInventarioConAcceso(inv);
            }
        }
        
        inventarios_sistema.add (inv);
        conn.InsertarInventarioSQL(inv);
    }
    
    /**
     * @brief Modificara un inventario existente obtenido con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se modificara y guardara un inventario 
     * @param String Id -> id del inventario a modificar
     * @param int slotsMaximos
     * @param int slotsOcupados
     * @param String[] idsJugadorInventarios -> jugadores con acceso a ese inventario
     */
    public void ModificarInventario (String Id, int slotsMaximos, int slotsOcupados, String[] idsJugadorInventarios) throws SQLException{
        int i=0;
        InventarioCompartido inv;
        
        for (i=0; i<inventarios_sistema.size() && !inventarios_sistema.get(i).getIdInventario().equals(Id); i++){
        }
        inv=inventarios_sistema.get(i);
        conn.ModificarInventarioSQL(inv, slotsMaximos, slotsOcupados, idsJugadorInventarios);


    }
    
    /**
     * @brief Eliminara un inventario existente obtenido con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se eliminara y se guardaran los cambios
     * @param String Id -> id del inventario a eliminar
     */
    public void EliminarInventario (String Id) throws SQLException{
        int i=0;
        for (i=0; i<inventarios_sistema.size() && !inventarios_sistema.get(i).getIdInventario().equals(Id); i++){
        }
        conn.EliminarInventarioSQL(inventarios_sistema.get(i));
        inventarios_sistema.remove(i);
        
    }
    
    public ArrayList<InventarioCompartido> getInventarios_sistema(){
        return inventarios_sistema;
    }
    
    /**
     * @brief Comprueba si existe la id de un jugador en la instancia actual del sistema
     * @return Boolean existe 
     * @param String Id -> id del jugador a comprobar
     */
    public Boolean existeIDJugador (String id){
        Boolean existe=false;
        for (int i=0; i<jugadores_sistema.size() && existe==false; i++){
            if (id.equals(jugadores_sistema.get(i).getIdPlayer())){
                existe=true;
            }
        }
        return existe;
    }
    
    /*////////////////////////////////////////////////////////
    ////////////////////     Partida     ////////////////////
    //////////////////////////////////////////////////////*/
    
    
    /**
     * @brief Crear una Partida por medio de sus atributos previamente obtenidos
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se creara y guardara una partida 
     * @param int numEspectadores
     * @param String IdServerPartida -> id de un servidor que sera asignado a la partida
     * @param String[] idsjugadores -> grupo de ids de jugadores que seran usadas para buscar el jugador y asignarlo a la partida
     */
    public void crearPartida (int numEspectadores, String IdServerPartida, String[] idsjugadores) throws SQLException{
        
        Partida p = new Partida (numEspectadores, obtenerServidorId(IdServerPartida));
        
        if (!idsjugadores[0].trim().equals("")){
            for (String idj : idsjugadores){
                p.setUnJugador(getJugadorById(idj));
            }
        }
        conn.InsertarPartidaSQL(p);
        partidas_sistema.add (p);
        
    }
    
    /**
     * @brief Modificar una Partida por medio de sus nuevos atributos previamente obtenidos
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se modificara y guardara una partida 
     * @param String Id -> id de la partida a modificar
     * @param int numEspectadores
     * @param String IdServerPartida -> id de un servidor que sera asignado a la partida
     * @param String[] idsjugadores -> grupo de ids de jugadores que seran usadas para buscar el jugador y asignarlo a la partida
     */
    public void ModificarPartida (String Id, int numEspectadores, String id_server, String[] idsjugadores) throws SQLException{
        int i=0;
        for (i=0; i<partidas_sistema.size() && !partidas_sistema.get(i).getIdPartida().equals(Id); i++){
        }
        
        conn.ModificarPartidaSQL(partidas_sistema.get(i), numEspectadores, id_server, idsjugadores);
    }
    
    /**
     * @brief Eliminara una partida existente obtenida con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se eliminara y se guardaran los cambios
     * @param String Id -> id de la partida a eliminar
     */
    public void EliminarPartida (String Id) throws SQLException{
        int i=0;
        for (i=0; i<partidas_sistema.size() && !partidas_sistema.get(i).getIdPartida().equals(Id); i++){
        }
        conn.EliminarPartidaSQL(partidas_sistema.get(i));
        partidas_sistema.remove(i);
        
    }
    
    public ArrayList<Partida> getPartidas_sistema() {
        return partidas_sistema;
    }
    
    /**
     * @brief Comprueba si existe la id de un servidor en la instancia actual del sistema
     * @return Boolean existe 
     * @param String Id -> id del servidor a comprobar
     */
    public Boolean existeIDServer (String Id){
        Boolean existe=false;
        for (int i=0; i<servidores_sistema.size() && existe==false; i++){
            if (Id.equals(servidores_sistema.get(i).getIdServer())){
                existe=true;
            }
        }
        return existe;
    }
    
    /**
     * @brief Comprueba si un servidor esta usado ya en alguna partida (Se debera tener en cuenta a la hora de editar que ya esta siendo usado)
     * @return Boolean libre 
     * @param String Id -> id del servidor a comprobar
     */
    public Boolean estaLibreIDServer (String Id){
        Boolean libre=true;
        
        for (int i=0; i<partidas_sistema.size() && libre==true; i++){
            
            if (partidas_sistema.get(i).getServerPartida().getIdServer().equals(Id)){
                libre=false;
            }
        }
        return libre;
    }
    
    /**
     * @brief busca y retorna un servidor del sistema por su id
     * @pre debera de existir el servidor
     * @return Servidor s
     * @param String Id -> id del servidor a buscar
     */
    public Servidor obtenerServidorId (String id){
        int i=0;

        for (i=0; i<servidores_sistema.size() && !servidores_sistema.get(i).getIdServer().equals(id); i++ ){
        }

        return servidores_sistema.get(i);
    }
    
    /**
     * @brief Comprueba si existe la id de un jugador en la instancia actual del sistema
     * @return Boolean existe 
     * @param String Id -> id del jugador a comprobar
     */
    public Boolean comprobarExistenciaIdJugador (String id){
        Boolean existe = false;
        
        for (Jugador j : jugadores_sistema){
            if (j.getIdPlayer().equals(id)){
                existe=true;
            }
        }
        
        return existe;
    }
    
    /**
     * @brief Comprueba si un jugador esta dentro de alguna partida (Se debera tener en cuenta a la hora de editar que ya esta siendo usado)
     * @return Boolean usado 
     * @param String Id -> id del jugador a comprobar
     */
    public Boolean comprobarUsoIdJugador (String id){
        Boolean usado = false;
        
        for (Partida p : partidas_sistema){
            for (Jugador j : p.getJugadores()){
                if (j.getIdPlayer().equals(id)){
                    usado=true;
                }
            }
        }
        
        return usado;
    }
    
    /**
     * @brief busca y retorna un jugador del sistema por su id
     * @pre debera de existir el jugador
     * @return Jugador j
     * @param String Id -> id del jugador a buscar
     */
    public Jugador getJugadorById (String id){
        int i=0;

        for (i=0; i<jugadores_sistema.size() && !jugadores_sistema.get(i).getIdPlayer().equals(id); i++ ){
        }

        return jugadores_sistema.get(i);
    }
    
    /*////////////////////////////////////////////////////////
    ////////////////////     Jugador     ////////////////////
    //////////////////////////////////////////////////////*/
    
    /**
     * @brief Crear una Jugador por medio de sus atributos previamente obtenidos
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se creara y guardara una jugador 
     * @param String NickName
     * @param int nivel
     * @param String[] idsInventariosJugador -> grupo de ids de inventarios usados para asignar a estos al jugador 
     */
    public void crearJugador (String NickName, int nivel, String[] idsInventariosJugador) throws SQLException{
        
        Jugador j = new Jugador (NickName, nivel);
        
        if (!idsInventariosJugador[0].trim().equals("")){
            for (String idI : idsInventariosJugador){
                j.setInventarioConAcceso(getInventarioById(idI));
                getInventarioById(idI).setJugadorConAcceso(j);
            }
        }

        jugadores_sistema.add (j);
        
        conn.InsertarJugadorSQL(j);
    }
    
    /**
     * @brief Modificar una jugador por medio de sus nuevos atributos previamente obtenidos
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se modificara y guardara el jugador
     * @param String Id -> id del jugador a modificar
     * @param String NickName
     * @param int nivel
     * @param String[] idsInventariosJugador -> grupo de ids de inventarios usados para asignar a estos al jugador      
     */
    public void ModificarJugador (String Id, String NickName, int nivel, String [] idsInventariosJugador) throws SQLException{
        int i=0;
        Jugador j;
        for (i=0; i<jugadores_sistema.size() && !jugadores_sistema.get(i).getIdPlayer().equals(Id); i++){
        }
        j=jugadores_sistema.get(i);
        conn.ModificarJugadorSQL(j, NickName, nivel, idsInventariosJugador);
    }
    
    /**
     * @brief Eliminara un jugador existente obtenido con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se eliminara y se guardaran los cambios
     * @param String Id -> id del jugador a eliminar
     */
    public void EliminarJugador (String Id) throws SQLException{
        int i=0;
        for (i=0; i<jugadores_sistema.size() && !jugadores_sistema.get(i).getIdPlayer().equals(Id); i++){
        }
        conn.EliminarJugadorSQL(jugadores_sistema.get(i));
        jugadores_sistema.remove(i);
        
    }
    
    public ArrayList<Jugador> getJugadores_sistema() {
        return jugadores_sistema;
    }
    
    /**
     * @brief busca y retorna un inventario del sistema por su id
     * @pre debera de existir el inventario
     * @return Inventario inv
     * @param String idI -> id del inventario a buscar
     */
    public InventarioCompartido getInventarioById (String idI){
        int i=0;

        for (i=0; i<inventarios_sistema.size() && !inventarios_sistema.get(i).getIdInventario().equals(idI); i++ ){
        }

        return inventarios_sistema.get(i);
    }
    
    /**
     * @brief Comprueba si existe la id de un inventario en la instancia actual del sistema
     * @return Boolean existe 
     * @param String Id -> id del inventario a comprobar
     */
    public Boolean existeIDInventario (String id){
        Boolean existe=false;
        for (int i=0; i<inventarios_sistema.size() && existe==false; i++){
            if (id.equals(inventarios_sistema.get(i).getIdInventario())){
                existe=true;
            }
        }
        return existe;
    }
    
    
    public Conexion getconn (){
        return this.conn;
    }

    
}
