package com.mycompany.proyecto_ad_sql.controller;

import com.mycompany.proyecto_ad_sql.modelos.InventarioCompartido;
import com.mycompany.proyecto_ad_sql.modelos.Jugador;
import com.mycompany.proyecto_ad_sql.modelos.Partida;
import com.mycompany.proyecto_ad_sql.modelos.Servidor;
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
    
    //////////
    
    public ControllerTest() {
        instance = new Controller("ProyectoGamesTest.db");
        
        idServerCreadoPrueba1 = "";
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
        //TODO borrar todo lo insertado y modificado
        
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
     * Prueba 1
     * Test of crearServidor method, of class Controller.
     */
    @Test 
    public void testCrearServidor() {
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
     * Test of ModificarServidor method, of class Controller.
     */
    @Test
    public void testModificarServidor() {
        Boolean condicion = true; 
        Servidor sIni = instance.getServidores_sistema().get(0);
        Servidor sMod = null;

        String regionMod = "Granada";
        String idIni = instance.getServidores_sistema().get(0).getIdServer();

        instance.ModificarServidor(idIni, regionMod);

        sMod = instance.getconn().getServidorSQLByID(idIni);
        
        if (sMod.equals(null)){
            condicion=false;
        }
        if (!sMod.getRegion().equals(regionMod)){
            condicion=false;
        }
        
        assertEquals(true, condicion);
        sIniPreModPrueba2 = sIni;
    }
        
    

    /**
     * Test of EliminarServidor method, of class Controller.
     */
    @Test
    public void testEliminarServidor() {
        
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
