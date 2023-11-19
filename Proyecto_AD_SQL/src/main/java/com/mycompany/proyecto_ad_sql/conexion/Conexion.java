package com.mycompany.proyecto_ad_sql.conexion;

import com.mycompany.proyecto_ad_sql.modelos.Jugador;
import com.mycompany.proyecto_ad_sql.modelos.Partida;
import com.mycompany.proyecto_ad_sql.modelos.Servidor;
import com.mycompany.proyecto_ad_sql.modelos.InventarioCompartido;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DiosFer
 */
public class Conexion {
    
    /*////////////////////////////////////////////////////////
    /////////////////////    Atributos    ///////////////////
    //////////////////////////////////////////////////////*/
    
    private ArrayList<Servidor> servidores_conexion;
    private ArrayList<Partida> partidas_conexion;
    private ArrayList<InventarioCompartido> inventarios_conexion;
    private ArrayList<Jugador> jugadores_conexion;
    
    private static Connection conn = null;

    
    /*////////////////////////////////////////////////////////
    ////////////////////    Costructor    ///////////////////
    //////////////////////////////////////////////////////*/
    
    public Conexion (){
        this.getConection();
        
        servidores_conexion = new ArrayList<Servidor>();
        inventarios_conexion = new ArrayList<InventarioCompartido>();
        jugadores_conexion = new ArrayList<Jugador>();
        partidas_conexion = new ArrayList<Partida>();
        
        this.CargarServidoresSQL();
        this.CargarInventariosSQL();
        this.CargarJugadoresSQL();
        this.cagarRelacionJI();
        this.CargarPartidasSQL();
    }

    
    /*////////////////////////////////////////////////////////
    ///////////////////    getConection    //////////////////
    //////////////////////////////////////////////////////*/
    
     /**
     * @brief Abre una conexion en caso de no estar ya abierta una
     * @pre 
     * @post 
     * @param 
     */
    public Connection getConection (){
        
        if (conn==null){
            this.abrirConexion();
        }
        
        return conn;
    }
    
    /*////////////////////////////////////////////////////////
    ///////////////////    abrir-cerrar    //////////////////
    //////////////////////////////////////////////////////*/
    
     /**
     * @brief Abre una conexion en la base de datos
     * @pre no debe de haber una conexion abierta ya
     * @post se abrira una conexion 
     * @param 
     */
    private void abrirConexion (){
        try {
            String url = "jdbc:sqlite:ProyectoGames.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conexion iniciada correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    
    /**
     * @brief Cierra la consexion de la base de datos
     * @pre debe de haber una conexion abierta
     * @post se cerrara la conexion
     * @param 
     */
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
    
    
    /*////////////////////////////////////////////////////////
    /////////////////////    Insertar    ////////////////////
    //////////////////////////////////////////////////////*/
    
    /**
     * @brief Inserta un servidor en la base de datos y actualiza su id en el sistema
     * @param Servidor s
     * @post se actualizara la id y se añadira a la base de datos
     */
    public void InsertarServidorSQL (Servidor s){

        String sent = "INSERT INTO servidores (region) VALUES (?)";
        PreparedStatement sentencia = null;
        ResultSet generatedKeys = null;
        int idGenerado = 0;

        
        try{
            sentencia = conn.prepareStatement(sent, Statement.RETURN_GENERATED_KEYS);
            
            sentencia.setString(1, s.getRegion());
            
            sentencia.executeUpdate();
            
            generatedKeys = sentencia.getGeneratedKeys();
            idGenerado = generatedKeys.getInt(1);
            
            s.setIdServer("S-" + idGenerado);
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            try{
                if (sentencia != null)
                    sentencia.close();
            }
            catch(SQLException sqle2){
                sqle2.printStackTrace();
            }
        }
        
        
    }
    
    /**
     * @brief Inserta una partida en la base de datos y actualiza su id en el sistema
     * @param Partida p
     * @post se actualizara la id y se añadira a la base de datos
     */
    public void InsertarPartidaSQL (Partida p){

        String sent = "INSERT INTO partidas (num_Espectadores, id_servidor) VALUES (?, ?)";
        PreparedStatement sentencia = null;
        ResultSet generatedKeys = null;
        int idGenerado = 0;

        
        try{
            sentencia = conn.prepareStatement(sent, Statement.RETURN_GENERATED_KEYS);
            
            sentencia.setString(1, String.valueOf(p.getNumEspectadores()));
            sentencia.setString(2, p.getServerPartida().getIdServer().substring(0, p.getServerPartida().getIdServer().length()));

            sentencia.executeUpdate();
            
            generatedKeys = sentencia.getGeneratedKeys();
            idGenerado = generatedKeys.getInt(1);
            
            p.setIdPartida("P-" + idGenerado);
            
            for (int num=0; num < p.getJugadores().size(); num++){
                sent = "UPDATE jugadores SET id_partida = ? " + "WHERE id_jugador = ?";
                sentencia = null;
                
                sentencia = conn.prepareStatement(sent);
                
                sentencia.setString(1, p.getIdPartida().substring(2, p.getIdPartida().length()));

                //Id
                sentencia.setString(2, p.getJugadores().get(num).getIdPlayer().substring(2, p.getJugadores().get(num).getIdPlayer().length()));
                
                sentencia.executeUpdate();

            }
            
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            try{
                if (sentencia != null)
                    sentencia.close();
            }
            catch(SQLException sqle2){
                sqle2.printStackTrace();
            }
        }
        
        
    }
    
    /*////////////////////////////////////////////////////////
    ////////////////////    Modificar    ////////////////////
    //////////////////////////////////////////////////////*/
    
    /**
     * @brief Modifica un servidor en la base de datos
     * @param Servidor s 
     * @pre que este el objeto en la base de datos
     * @post se modificara el objeto y la instancia en la base de datos
     */
    public void ModificarServidorSQL (Servidor s, String region){

            String sentenciaSql = "UPDATE servidores SET region = ? " + "WHERE id_servidor = ?";
            PreparedStatement sentencia = null;
            try {
                sentencia = conn.prepareStatement(sentenciaSql);
                
                //cambios
                 //region
                sentencia.setString(1, region);
                s.setRegion(region);
                
                //Id
                sentencia.setString(2, s.getIdServer().substring(2, s.getIdServer().length()));
                
                sentencia.executeUpdate();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } finally {
                if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        
        
    }
    
    
    /**
     * @brief Modifica una partida en la base de datos
     * @param Partida p 
     * @pre que este el objeto en la base de datos
     * @post se modificara el objeto y la instancia en la base de datos
     */
    public void ModificarPartidaSQL (Partida p, int numEspectadores, String id_server, String[] idsjugadores){

            String sentenciaSql = "";
            PreparedStatement sentencia = null;
            try {
                /// num_Espectadores
                sentenciaSql = "UPDATE partidas SET num_Espectadores = ? " + "WHERE id_partida = ?";
                sentencia = null;
                
                sentencia = conn.prepareStatement(sentenciaSql);
                
                
                sentencia.setString(1, String.valueOf(numEspectadores));
                p.setNumEspectadores(numEspectadores);
                sentencia.setString(2, p.getIdPartida().substring(2, p.getIdPartida().length()));
                sentencia.executeUpdate();                
                ///
                
                /// id_servidor
                sentenciaSql = "UPDATE partidas SET id_servidor = ? " + "WHERE id_partida = ?";
                sentencia = null;
                
                sentencia = conn.prepareStatement(sentenciaSql);
                
                
                sentencia.setString(1, id_server.substring(2, id_server.length()));
                p.setServerPartida(getServidorById(id_server));
                sentencia.setString(2, p.getIdPartida().substring(2, p.getIdPartida().length()));
                sentencia.executeUpdate();         
                ///
                
                
                //// Limpiar jugadores
                for (int num=0; num < p.getJugadores().size(); num++){
                    sentenciaSql = "UPDATE jugadores SET id_partida = ? " + "WHERE id_jugador = ?";
                    sentencia = null;

                    sentencia = conn.prepareStatement(sentenciaSql);

                    sentencia.setString(1, null);

                    //Id
                    sentencia.setString(2, p.getJugadores().get(num).getIdPlayer().substring(2, p.getJugadores().get(num).getIdPlayer().length()));

                    sentencia.executeUpdate();

                }
                ////
                
                //Actualizamos los jugadores del objeto
                
                p.getJugadores().clear();
        
                if (!idsjugadores[0].trim().equals("")){
                    for (String idj : idsjugadores){
                        p.setUnJugador(getJugadorById(idj));
                    }
                }

                
                //// Insertar nuevos jugadores
                for (int num=0; num < p.getJugadores().size(); num++){
                    sentenciaSql = "UPDATE jugadores SET id_partida = ? " + "WHERE id_jugador = ?";
                    sentencia = null;

                    sentencia = conn.prepareStatement(sentenciaSql);

                    sentencia.setString(1, p.getIdPartida().substring(2, p.getIdPartida().length()));

                    //Id
                    sentencia.setString(2, p.getJugadores().get(num).getIdPlayer().substring(2, p.getJugadores().get(num).getIdPlayer().length()));

                    sentencia.executeUpdate();

                }
                ////
                

                sentencia.executeUpdate();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } finally {
                if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        
    }
    
    /*////////////////////////////////////////////////////////
    /////////////////////    Eliminar    ////////////////////
    //////////////////////////////////////////////////////*/
    /**
     * @brief Eliminara un servidor de la base de datos
     * @param String id 
     * @pre debe de existir el servidor en la base de datos
     * @post se eliminara el servidor
     */
    public void EliminarServidorSQL (String id){
    String sentenciaSql = "DELETE FROM servidores WHERE id_servidor = ?";
    PreparedStatement sentencia = null;
    try {
        sentencia = conn.prepareStatement(sentenciaSql);
        sentencia.setString(1, id.substring(2, id.length()));
        sentencia.executeUpdate();
    } catch (SQLException sqle) {
        sqle.printStackTrace();
    } finally {
        if (sentencia != null)
            try {
                sentencia.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
    
    
    
    
    /*////////////////////////////////////////////////////////
    //////////////////////    Cargas    /////////////////////
    //////////////////////////////////////////////////////*/
    
    
     /**
     * @brief Carga en el vector de la conexion los datos de la base de datos
     * @pre debe de haber una conexion abierta
     * @post se actualizara el vector de la conexion 
     */
    public void CargarServidoresSQL (){

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
        
        
    }

     /**
     * @brief Carga en el vector de la conexion los datos de la base de datos
     * @pre debe de haber una conexion abierta
     * @post se actualizara el vector de la conexion 
     */
    public void CargarInventariosSQL (){

        String cons = "SELECT * FROM inventarios;";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        InventarioCompartido i;

        inventarios_conexion.clear();

        try{                                

            consulta = this.getConection().prepareStatement(cons);

            resultado = consulta.executeQuery();
            while(resultado.next()){

                i = new InventarioCompartido ("I-"+resultado.getString(1).toString(), resultado.getInt(3), resultado.getInt(2));
                inventarios_conexion.add(i);

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
    }
    
     /**
     * @brief Carga en el vector de la conexion los datos de la base de datos
     * @pre debe de haber una conexion abierta
     * @post se actualizara el vector de la conexion 
     */
    public void CargarJugadoresSQL (){

        String cons = "SELECT * FROM jugadores;";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Jugador j;

        jugadores_conexion.clear();

        try{                                

            consulta = this.getConection().prepareStatement(cons);

            resultado = consulta.executeQuery();
            while(resultado.next()){

                j = new Jugador ("J-"+resultado.getString(1).toString(), resultado.getString(2), resultado.getInt(3));
                jugadores_conexion.add(j);

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
    }
    
     /**
     * @brief Carga la relacion acceso en los vectores de inventarios y jugadores
     * @pre debe de haber una conexion abierta
     * @pre debe de haberse cargado previamente los jugadores e inventarios
     * @post se actualizara los vectores de la conexion 
     */
    public void cagarRelacionJI (){

        String cons = "SELECT * FROM accesos;";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        


        try{                                

            consulta = this.getConection().prepareStatement(cons);
            Boolean insertado;
            resultado = consulta.executeQuery();
            while(resultado.next()){
                insertado=false;
                    
                    getJugadorById ("J-"+resultado.getString(1).toString()).setInventarioConAcceso(getInventarioById ("I-"+resultado.getString(2).toString()));
                    
                    getInventarioById ("I-"+resultado.getString(2).toString()).setJugadorConAcceso(getJugadorById ("J-"+resultado.getString(1).toString()));
                
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
    }
    
     /**
     * @brief Carga en el vector de la conexion los datos de la base de datos
     * @pre debe de haber una conexion abierta
     * @pre debe de haberse cargado previamente los servidores y los jugadores
     * @post se actualizara el vector de la conexion 
     */
    public void CargarPartidasSQL (){

        String cons = "SELECT * FROM partidas;";
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Partida p;

        partidas_conexion.clear();
        
        try{                                
                                
            consulta = this.getConection().prepareStatement(cons);

            resultado = consulta.executeQuery();
            while(resultado.next()){

                p = new Partida ("P-"+resultado.getString(1).toString(), resultado.getInt(2), getServidorById("S-" + resultado.getInt(3)));
                partidas_conexion.add(p);
                
            }
            
            //Cargamos los jugadores de la partida
            
            for (Partida part : partidas_conexion){
                cons = "SELECT id_jugador FROM jugadores WHERE id_partida==" + part.getIdPartida().substring(2, part.getIdPartida().length()) + ";";
                
                consulta = null;
                resultado = null;
                
                consulta = this.getConection().prepareStatement(cons);

                resultado = consulta.executeQuery();
                while(resultado.next()){
                    part.setUnJugador(getJugadorById("J-" + resultado.getString(1).toString()));
                }
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
        

    }
    
    /*////////////////////////////////////////////////////////
    //////////////////////    Get's    //////////////////////
    //////////////////////////////////////////////////////*/
    
     /**
     * @brief Obtiene un inventario del vector de la conexion
     * @param String id
     * @pre debe de existir el inventario
     * @return InventarioCompartido
     */
    public InventarioCompartido getInventarioById (String id){
        
        Boolean encontrado=false;
        int i=0;
        for (i=0; i<inventarios_conexion.size() && encontrado==false; i++){
            if (inventarios_conexion.get(i).getIdInventario().equals(id)){
                encontrado=true;
            }
        }
        return inventarios_conexion.get(i-1);
    }
    
     /**
     * @brief Obtiene un jugador del vector de la conexion
     * @param String id
     * @pre debe de existir el jugador
     * @return Jugador
     */
    public Jugador getJugadorById (String id){
        
        Boolean encontrado=false;
        int i=0;
        for (i=0; i<jugadores_conexion.size() && encontrado==false; i++){
            if (jugadores_conexion.get(i).getIdPlayer().equals(id)){
                encontrado=true;
            }
        }
        return jugadores_conexion.get(i-1);
    }
    
    /**
     * @brief Obtiene un jugador del vector de la conexion
     * @param String id
     * @pre debe de existir el servidor
     * @return Servidor
     */
    public Servidor getServidorById (String id){
        
        Boolean encontrado=false;
        int i=0;
        for (i=0; i<servidores_conexion.size() && encontrado==false; i++){
            if (servidores_conexion.get(i).getIdServer().equals(id)){
                encontrado=true;
            }
        }
        return servidores_conexion.get(i-1);
    }
    
      
     /**
     * @brief retorna el vector de la conexion
     * @return ArrayList<Servidor>
     */
    public ArrayList<Servidor> getServidoresSQL (){
        return servidores_conexion;
    }
    
     /**
     * @brief retorna el vector de la conexion
     * @return ArrayList<InventarioCompartido>
     */
    public ArrayList<InventarioCompartido> getInventariosSQL (){
        return inventarios_conexion;
    }
        
     /**
     * @brief retorna el vector de la conexion
     * @return ArrayList<Jugador>
     */
    public ArrayList<Jugador> getJugadoresSQL (){
        return jugadores_conexion;
    }
    
     /**
     * @brief retorna el vector de la conexion
     * @return ArrayList<Partida>
     */
    public ArrayList<Partida> getPartidasSQL (){
        return partidas_conexion;
    }
}



