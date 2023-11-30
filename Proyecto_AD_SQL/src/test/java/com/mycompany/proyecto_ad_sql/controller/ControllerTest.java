package com.mycompany.proyecto_ad_sql.controller;

import com.mycompany.proyecto_ad_sql.modelos.InventarioCompartido;
import com.mycompany.proyecto_ad_sql.modelos.Jugador;
import com.mycompany.proyecto_ad_sql.modelos.Partida;
import com.mycompany.proyecto_ad_sql.modelos.Servidor;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author DiosFer
 */
public class ControllerTest {
    
    
    private static Controller instance;
    
    
    
    
    ///////////
    
    private static String idServerCreadoPrueba1;
    
    private static Servidor sIniPreModPrueba2;
    
    private static Servidor sEliminadoPrueba3;
    
    //////////
    
    public ControllerTest()  throws SQLException {
        instance = new Controller("ProyectoGamesTest.db");
        
        idServerCreadoPrueba1 = "";
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass()  throws SQLException {
        //Borramos todo lo insertado y modificado
        
        //Eliminar cambios Prueba1
        if (!idServerCreadoPrueba1.equals("")){
            instance.EliminarServidor(idServerCreadoPrueba1);
        }
        
        //Eliminar cambios Prueba2
        if (sIniPreModPrueba2!=null){
            instance.ModificarServidor(sIniPreModPrueba2.getIdServer(), sIniPreModPrueba2.getRegion());
        }
        
        
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    /**
     * Prueba 0
     * Partimos de que nuestra bd tiene instancias y la lectura inicial es en la creacion del controlador
     * por tanto esta lectura debera de haber rellenado los vectores del controlador cosa que se comprueba en este test
     */
    @Test
    public void testLecturaInicial() throws SQLException {
        Boolean condicion=true;
        if (instance.getServidores_sistema().size()==0){
            condicion=false;
        }
        
        if (instance.getInventarios_sistema().size()==0){
            condicion=false;
        }
        
        else if (instance.getJugadores_sistema().size()==0){
            condicion=false;
        }
        
        else if (instance.getPartidas_sistema().size()==0){
            condicion=false;
        }
        
        assertEquals(true, condicion);
    }

    /**
     * Prueba 1
     * Prueba para comprobar que se crea un servidor nuevo, se comprobara tanto su adicion al vector del sistema 
     * y que tenga id, la id es generada por la bd por eso nos confirma que se ha creado en la bd
     * posteriormente se restauraran los cambios
     */
    @Test 
    public void testCrearServidor() throws SQLException {
        int numServers = instance.getServidores_sistema().size()-1;
        Boolean condicion=true;
        
        String region = "Europa";
        
        instance.crearServidor(region);
 
        //Se ha creado y añadido
        if (numServers+1 == instance.getServidores_sistema().size()-1){
            
            //Se ha creado pero no se ha introducido en la base de datos pues no tine id y la base de datos se lo crea e introduce
            if (instance.getServidores_sistema().get(instance.getServidores_sistema().size()-1).getIdServer()=="" || instance.getServidores_sistema().get(instance.getServidores_sistema().size()-1).getIdServer()==null ){

                //NO pasa el test
                condicion=false;
                assertEquals(true, condicion);
                
            }
            else {

                //SI pasa el test
                condicion=true;
                assertEquals(true, condicion);
                idServerCreadoPrueba1 = instance.getServidores_sistema().get(instance.getServidores_sistema().size()-1).getIdServer();

            }
        }
        else {

            //NO pasa el test
            condicion=false;
            assertEquals(true, condicion);
            
        }
        
        
    }

    /**
     * Prueba2
     * Modifico uno de los servidores, guardando su instancia inicial y comprobando que se modifican los datos
     * se restaurara posteriormente
     */
    @Test
    public void testModificarServidor() throws SQLException{
        Boolean condicion = true; 
        Servidor sIni = instance.getServidores_sistema().get(0);
        Servidor sMod = null;

        String regionMod = "Granada";
        String idIni = instance.getServidores_sistema().get(0).getIdServer();

        instance.ModificarServidor(idIni, regionMod);

        sMod = instance.getconn().getServidorSQLByID(idIni);
        
        if (sMod==null){
            condicion=false;
        }
        if (!sMod.getRegion().equals(regionMod)){
            condicion=false;
        }
        
        assertEquals(true, condicion);
        sIniPreModPrueba2 = sIni;
    }
        
    

    /**
     * Prueba3
     * se crea y elimina un servidor, el crear es partiendo de que ya funciona el crear, pero se hace esto ya que el 
     * objetivo de este test es comprobar su correcta eliminacion y es mas simple eliminar uno sin dependencias, para nuestro actual objetivo
     */
    @Test
    public void testEliminarServidor() throws SQLException {
        
        //Creamos un servidor para comprobar que se elimine correctamente, objetivo de esta prueba
        instance.crearServidor("Prueba");
        String idNewServerPrueba = instance.getServidores_sistema().get(instance.getServidores_sistema().size()-1).getIdServer();
        
        Boolean condicion=false;
        int tamanio = instance.getServidores_sistema().size();
        String IdEliminado = idNewServerPrueba;
        sEliminadoPrueba3 = instance.getServidores_sistema().get(instance.getServidores_sistema().size()-1);

        instance.EliminarServidor(IdEliminado);
        
        if (instance.getServidores_sistema().size()==tamanio-1){
            condicion=true;
        }
        if (instance.getconn().getServidorSQLByID(IdEliminado)!=null){
            condicion=false;
        }
        
        assertEquals(true, condicion);
        
    }


    /**
     * Test of crearInventario method, of class Controller.
     */
    @Test
    public void testCrearInventario() {
        
    }

    /**
     * Test of ModificarInventario method, of class Controller.
     */
    @Test
    public void testModificarInventario() {
        
    }

    /**
     * Test of EliminarInventario method, of class Controller.
     */
    @Test
    public void testEliminarInventario() {
        
    }


    /**
     * Test of crearPartida method, of class Controller.
     */
    @Test
    public void testCrearPartida() {
        
    }

    /**
     * Test of ModificarPartida method, of class Controller.
     */
    @Test
    public void testModificarPartida() {
        
    }

    /**
     * Test of EliminarPartida method, of class Controller.
     */
    @Test
    public void testEliminarPartida() {
        
    }


    /**
     * Test of crearJugador method, of class Controller.
     */
    @Test
    public void testCrearJugador() {
        
    }

    /**
     * Test of ModificarJugador method, of class Controller.
     */
    @Test
    public void testModificarJugador() {
        
    }

    /**
     * Test of EliminarJugador method, of class Controller.
     */
    @Test
    public void testEliminarJugador() {
        
    }

    /**
     * Test of getJugadores_sistema method, of class Controller.
     */
    @Test
    public void testGetJugadores_sistema() {
        
    }

    
}
