package Controller;
import Clases.InventarioCompartido;
import Clases.Jugador;
import Clases.Partida;
import Clases.Servidor;
import java.util.ArrayList;

/// xml

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Result;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

///

/**
 *
 * @author Fernando Ortega Castro
 */
public class Controller { 

    private ArrayList<Servidor> servidores_sistema;
    private ArrayList<InventarioCompartido> inventarios_sistema;
    private ArrayList<Partida> partidas_sistema;
    private ArrayList<Jugador> jugadores_sistema;

    private ArrayList<ArrayList<String>> arrayAuxiliarDeArraysCarga; // Para la carrga de inventarios (parcial)
    
     /**
     * @brief Constructor del controlador con la lectura inicial del xml
     */
    public Controller () {
            servidores_sistema = new ArrayList<Servidor>();
            inventarios_sistema = new ArrayList<InventarioCompartido>();
            partidas_sistema = new ArrayList<Partida>();
            jugadores_sistema = new ArrayList<Jugador>();
            
            arrayAuxiliarDeArraysCarga = new ArrayList<ArrayList<String>>();
            
            this.leerXML_inventario();
            this.leerXML_jugador();
            this.leerXML_servidor();
            this.leerXML_partida();
            this.enlazarInventarioJugador();
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
    public void crearServidor (String region){
        servidores_sistema.add (new Servidor (region));
        this.escribirXML_servidor();
    }
    
    /**
     * @brief Modificara un servidor existente obtenido con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se modificara y guardara un servidor 
     * @param String Id -> id del servidor a modificar
     * @param String region -> nuevo dato
     */
    public void ModificarServidor (String Id, String region){
        int i=0;
        for (i=0; i<servidores_sistema.size() && !servidores_sistema.get(i).getIdServer().equals(Id); i++){
        }
        servidores_sistema.get(i).setRegion(region);
        this.escribirXML_servidor();
    }
    
    /**
     * @brief Eliminara un servidor existente obtenido con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se eliminara y se guardaran los cambios
     * @param String Id -> id del servidor a eliminar
     */
    public void EliminarServidor (String Id){
        int i=0;
        for (i=0; i<servidores_sistema.size() && !servidores_sistema.get(i).getIdServer().equals(Id); i++){
        }
        servidores_sistema.remove(i);
        this.escribirXML_servidor();
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
    public void crearInventario (int slotsMaximos, int slotsOcupados, String[] idsJugadorInventarios){
        InventarioCompartido inv = new InventarioCompartido (slotsMaximos, slotsOcupados);
        
        
        if (!idsJugadorInventarios[0].trim().equals("")){
            for (String idJ : idsJugadorInventarios){
                inv.setJugadorConAcceso(getJugadorById(idJ));
                getJugadorById(idJ).setInventarioConAcceso(inv);
            }
        }
        
        inventarios_sistema.add (inv);
        
        
        this.escribirXML_inventario();
        this.escribirXML_jugador();
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
    public void ModificarInventario (String Id, int slotsMaximos, int slotsOcupados, String[] idsJugadorInventarios){
        int i=0;
        InventarioCompartido inv;
        
        for (i=0; i<inventarios_sistema.size() && !inventarios_sistema.get(i).getIdInventario().equals(Id); i++){
        }
        
        inv=inventarios_sistema.get(i);
        inv.setSlotsMaximos(slotsMaximos);
        inv.setSlotsOcupados(slotsOcupados);
        
        
        //retiramos el acceso de todos los jugadores al inventario
        for (Jugador jug : inv.getJugadores()){
            jug.removerInventario(inv.getIdInventario());
        }
        
        //limpiamos los jugadores del inventario
        inv.getJugadores().clear();
        
        //Establecemos los nuevos jugadores
        if (!idsJugadorInventarios[0].trim().equals("")){
            for (String idJ : idsJugadorInventarios){
                inv.setJugadorConAcceso(getJugadorById(idJ));
                getJugadorById(idJ).setInventarioConAcceso(inv);
            }
        }
        
        this.escribirXML_inventario();
        this.escribirXML_jugador();


    }
    
    /**
     * @brief Eliminara un inventario existente obtenido con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se eliminara y se guardaran los cambios
     * @param String Id -> id del inventario a eliminar
     */
    public void EliminarInventario (String Id){
        int i=0;
        for (i=0; i<inventarios_sistema.size() && !inventarios_sistema.get(i).getIdInventario().equals(Id); i++){
        }
        inventarios_sistema.remove(i);
        this.escribirXML_inventario();
    }
    
    public ArrayList<InventarioCompartido> getInventarios_sistema() {
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
    public void crearPartida (int numEspectadores, String IdServerPartida, String[] idsjugadores){
        
        Partida p = new Partida (numEspectadores, obtenerServidorId(IdServerPartida));
        
        if (!idsjugadores[0].trim().equals("")){
            for (String idj : idsjugadores){
                p.setUnJugador(getJugadorById(idj));
            }
        }

        partidas_sistema.add (p);
        
        this.escribirXML_partida();
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
    public void ModificarPartida (String Id, int numEspectadores, String id_server, String[] idsjugadores){
        int i=0;
        for (i=0; i<partidas_sistema.size() && !partidas_sistema.get(i).getIdPartida().equals(Id); i++){
        }
        partidas_sistema.get(i).setNumEspectadores(numEspectadores);
        partidas_sistema.get(i).setServerPartida(obtenerServidorId(id_server));
        partidas_sistema.get(i).getJugadores().clear();
        
        if (!idsjugadores[0].trim().equals("")){
            for (String idj : idsjugadores){
                partidas_sistema.get(i).setUnJugador(getJugadorById(idj));
            }
        }

        this.escribirXML_partida();
    }
    
    /**
     * @brief Eliminara una partida existente obtenida con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se eliminara y se guardaran los cambios
     * @param String Id -> id de la partida a eliminar
     */
    public void EliminarPartida (String Id){
        int i=0;
        for (i=0; i<partidas_sistema.size() && !partidas_sistema.get(i).getIdPartida().equals(Id); i++){
        }
        partidas_sistema.remove(i);
        this.escribirXML_partida();
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
    public void crearJugador (String NickName, int nivel, String[] idsInventariosJugador){
        
        Jugador j = new Jugador (NickName, nivel);
        
        if (!idsInventariosJugador[0].trim().equals("")){
            for (String idI : idsInventariosJugador){
                j.setInventarioConAcceso(getInventarioById(idI));
                getInventarioById(idI).setJugadorConAcceso(j);
            }
        }

        jugadores_sistema.add (j);
        
        this.escribirXML_jugador();
        this.escribirXML_inventario();
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
    public void ModificarJugador (String Id, String NickName, int nivel, String [] idsInventariosJugador){
        int i=0;
        Jugador j;
        for (i=0; i<jugadores_sistema.size() && !jugadores_sistema.get(i).getIdPlayer().equals(Id); i++){
        }
        j=jugadores_sistema.get(i);
        j.setNickName(NickName);
        j.setNivel(nivel);
        
        //retiramos el acceso de todos los inventarios del jugador
        for (InventarioCompartido inv : j.getInventarios()){
            inv.removerAcceso(j.getIdPlayer());
        }
        
        //limpiamos los inventarios del jugador
        j.getInventarios().clear();
        
        //Establecemos los nuevos inventarios
        if (!idsInventariosJugador[0].trim().equals("")){
            for (String idI : idsInventariosJugador){
                j.setInventarioConAcceso(getInventarioById(idI));
                getInventarioById(idI).setJugadorConAcceso(j);
            }
        }
        
        this.escribirXML_jugador();
        this.escribirXML_inventario();
    }
    
    /**
     * @brief Eliminara un jugador existente obtenido con una id 
     * @pre se obtendran y filtraran los datos en la interfaz
     * @post se eliminara y se guardaran los cambios
     * @param String Id -> id del jugador a eliminar
     */
    public void EliminarJugador (String Id){
        int i=0;
        for (i=0; i<jugadores_sistema.size() && !jugadores_sistema.get(i).getIdPlayer().equals(Id); i++){
        }
        
        //retiramos el acceso de todos los inventarios del jugador
        for (InventarioCompartido inv : jugadores_sistema.get(i).getInventarios()){
            inv.removerAcceso(jugadores_sistema.get(i).getIdPlayer());
        }
        
        jugadores_sistema.remove(i);
        
        this.escribirXML_jugador();
        this.escribirXML_inventario();
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

    /*////////////////////////////////////////////////////////
    ///////////////////    xml_Servidor    //////////////////
    //////////////////////////////////////////////////////*/

     /**
     * @brief escribir el xml de los servidores
     * @post se escribira la instancia actual de los servidores del controlador en un archivo xml
     */
    public void escribirXML_servidor(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            // Crear estructura del documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null, "xml", null);

            // Crear elemento raiz
            Element raiz = documento.createElement("Servidores");
            documento.getDocumentElement().appendChild(raiz);
            
            // Crear nodos para cada producto y colgarlos del arbol
            Element nodoServidor = null;
            Element nodoDatos = null;
            Text texto = null;

            for (Servidor serv : servidores_sistema){
                nodoServidor = documento.createElement("Servidor");
                raiz.appendChild(nodoServidor);

                nodoDatos = documento.createElement("IdServidor");
                nodoServidor.appendChild(nodoDatos);
                texto = documento.createTextNode(serv.getIdServer());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("Region");
                nodoServidor.appendChild(nodoDatos);
                texto = documento.createTextNode(serv.getRegion());
                nodoDatos.appendChild(texto);
            }

            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("servidores_sistema.xml"));
            
            transformer.transform(source, resultado);
            
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(TransformerConfigurationException tce){
            tce.printStackTrace();
        }
        catch(TransformerException te){
            te.printStackTrace();
        }
    }
    
     /**
     * @brief leer el xml de los servidores y guardarlo en la instancia del controlador
     * @post leera un archivo xml y guardaran sus datos
     */
    public void leerXML_servidor(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            File f = new File("servidores_sistema.xml");
            if (!f.exists() || f.length() == 0){
                //No lo podemos leero o no existe o esta vacio
            }
            else {

                documento = builder.parse(f);

                // Recorrer el arbol
                NodeList servidores = documento.getElementsByTagName("Servidor");

                for(int i = 0; i < servidores.getLength(); i++){
                    Node servidor = servidores.item(i);
                    Element elemento = (Element) servidor;

                    servidores_sistema.add(new Servidor (elemento.getElementsByTagName("IdServidor").item(0).getChildNodes().item(0).getNodeValue(), elemento.getElementsByTagName("Region").item(0).getChildNodes().item(0).getNodeValue()));
                }

            }
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        catch(SAXException saxe){
            saxe.printStackTrace();
        }
    }

    /*////////////////////////////////////////////////////////
    //////////////////    xml_Inventario    /////////////////
    //////////////////////////////////////////////////////*/

     /**
     * @brief escribir el xml de los inventarios
     * @post se escribira la instancia actual de los inventarios del controlador en un archivo xml
     */
    public void escribirXML_inventario(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            // Crear estructura del documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null, "xml", null);

            // Crear elemento raiz
            Element raiz = documento.createElement("Inventarios");
            documento.getDocumentElement().appendChild(raiz);
            
            // Crear nodos para cada producto y colgarlos del arbol
            Element nodoInventario = null;
            Element nodoDatos = null;
            Text texto = null;

            
            Element nodoJugador = null;
            Element nodoJugadores = null;

            
            for (InventarioCompartido inv : inventarios_sistema){
                nodoInventario = documento.createElement("Inventario");
                raiz.appendChild(nodoInventario);

                nodoDatos = documento.createElement("IdInventario");
                nodoInventario.appendChild(nodoDatos);
                texto = documento.createTextNode(inv.getIdInventario());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("SlotsMaximos");
                nodoInventario.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(inv.getSlotsMaximos()));
                nodoDatos.appendChild(texto);
                
                nodoDatos = documento.createElement("SlotsOcupados");
                nodoInventario.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(inv.getSlotsOcupados()));
                nodoDatos.appendChild(texto);
                
                nodoJugadores = documento.createElement("Jugadores");
                nodoInventario.appendChild(nodoJugadores);

                for (Jugador j : inv.getJugadores()){
                    nodoJugador = documento.createElement("IdJugador");
                    nodoJugadores.appendChild(nodoJugador);
                    texto = documento.createTextNode(j.getIdPlayer());
                    nodoJugador.appendChild(texto);
                }
                
            }

            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("inventarios_sistema.xml"));
            
            transformer.transform(source, resultado);
            
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(TransformerConfigurationException tce){
            tce.printStackTrace();
        }
        catch(TransformerException te){
            te.printStackTrace();
        }
    }

     /**
     * @brief leer el xml de los inventarios y guardarlo en la instancia del controlador (debera de llamarse posteriormente a enlazarInventarioJugador tras la carga de los jugadores para finalizar la carga de los invcentarios)
     * @post leera un archivo xml y guardaran sus datos
     */
    public void leerXML_inventario(){ 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            File f = new File("inventarios_sistema.xml");
            if (!f.exists() || f.length() == 0){
                //No lo podemos leero o no existe o esta vacio
            }
            else {

                documento = builder.parse(f);

                // Recorrer el arbol
                NodeList inventarios = documento.getElementsByTagName("Inventario");

                for(int i = 0; i < inventarios.getLength(); i++){
                    Node inventario = inventarios.item(i);
                    Element elemento = (Element) inventario;

                    InventarioCompartido inv = new InventarioCompartido (elemento.getElementsByTagName("IdInventario").item(0).getChildNodes().item(0).getNodeValue(), Integer.parseInt(elemento.getElementsByTagName("SlotsMaximos").item(0).getChildNodes().item(0).getNodeValue()), Integer.parseInt(elemento.getElementsByTagName("SlotsOcupados").item(0).getChildNodes().item(0).getNodeValue()));
                    inventarios_sistema.add(inv);

                    ArrayList<String> aux = new ArrayList<String>();
                    NodeList jugadores = elemento.getElementsByTagName("IdJugador");
                    for (int k = 0; k < jugadores.getLength(); k++) {

                        Node jugadorNode = jugadores.item(k);

                        aux.add((jugadorNode.getTextContent()));

                    }
                    arrayAuxiliarDeArraysCarga.add(aux);
                
                }
            }
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        catch(SAXException saxe){
            saxe.printStackTrace();
        }
    }
    
     /**
     * @brief con un array auxiliar terminara la carga de los inventarios introduciendo sus jugadores con acceso
     * @pre haber llamado antes a leerXML_inventario para la carga del auxiliar
     * @pre haber cargado antes los jugadores
     * @post se introduciran los jugadores en los inventarios
     */
    public void enlazarInventarioJugador (){
        for (int i=0; i < inventarios_sistema.size(); i++){
            for (int j=0; j<arrayAuxiliarDeArraysCarga.get(i).size(); j++){
                inventarios_sistema.get(i).setJugadorConAcceso(getJugadorById(arrayAuxiliarDeArraysCarga.get(i).get(j)));
            }
        }
        
    }
    

    /*////////////////////////////////////////////////////////
    ///////////////////    xml_Partida    ///////////////////
    //////////////////////////////////////////////////////*/

     /**
     * @brief escribir el xml de las partidas
     * @post se escribira la instancia actual de las partidas del controlador en un archivo xml
     */
    public void escribirXML_partida(){
    
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            // Crear estructura del documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null, "xml", null);

            // Crear elemento raiz
            Element raiz = documento.createElement("Partidas");
            documento.getDocumentElement().appendChild(raiz);
            
            // Crear nodos para cada producto y colgarlos del arbol
            Element nodoPartida = null;
            Element nodoDatos = null;
            Text texto = null;
            
            Element nodoJugador = null;
            Element nodoJugadores = null;

            for (Partida part : partidas_sistema){
                nodoPartida = documento.createElement("Partida");
                raiz.appendChild(nodoPartida);

                nodoDatos = documento.createElement("IdPartida");
                nodoPartida.appendChild(nodoDatos);
                texto = documento.createTextNode(part.getIdPartida());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("NumEspectadores");
                nodoPartida.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(part.getNumEspectadores()));
                nodoDatos.appendChild(texto);
                
                nodoDatos = documento.createElement("IdServidorPartida");
                nodoPartida.appendChild(nodoDatos);
                texto = documento.createTextNode(part.getServerPartida().getIdServer());
                nodoDatos.appendChild(texto);
                
                nodoJugadores = documento.createElement("Jugadores");
                nodoPartida.appendChild(nodoJugadores);

                for (Jugador j : part.getJugadores()){
                    nodoJugador = documento.createElement("IdJugador");
                    nodoJugadores.appendChild(nodoJugador);
                    texto = documento.createTextNode(j.getIdPlayer());
                    nodoJugador.appendChild(texto);
                }
                

            }

            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("partidas_sistema.xml"));
            
            transformer.transform(source, resultado);
            
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(TransformerConfigurationException tce){
            tce.printStackTrace();
        }
        catch(TransformerException te){
            te.printStackTrace();
        }
    }

     /**
     * @brief leer el xml de las partidas y guardarlo en la instancia del controlador
     * @pre haber cargado antes los jugadores
     * @post leera un archivo xml y guardaran sus datos
     */
    public void leerXML_partida(){
    
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        Partida p;

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            File f = new File("partidas_sistema.xml");
            if (!f.exists() || f.length() == 0){
                //No lo podemos leero o no existe o esta vacio
            }
            else {
                documento = builder.parse(f);

                // Recorrer el arbol
                NodeList partidas = documento.getElementsByTagName("Partida");

                for(int i = 0; i < partidas.getLength(); i++){
                    Node partida = partidas.item(i);
                    Element elemento = (Element) partida;


                    p = new Partida (elemento.getElementsByTagName("IdPartida").item(0).getChildNodes().item(0).getNodeValue(), Integer.parseInt(elemento.getElementsByTagName("NumEspectadores").item(0).getChildNodes().item(0).getNodeValue()), obtenerServidorId(elemento.getElementsByTagName("IdServidorPartida").item(0).getChildNodes().item(0).getNodeValue()));

                    NodeList jugadores = elemento.getElementsByTagName("IdJugador");

                    for (int j = 0; j < jugadores.getLength(); j++) {
                        Node jugadorNode = jugadores.item(j);
                        p.setUnJugador(getJugadorById(jugadorNode.getTextContent()));
                    }
                    partidas_sistema.add(p);
                }
            }

        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        catch(SAXException saxe){
            saxe.printStackTrace();
        }
    }
    
    /*////////////////////////////////////////////////////////
    ///////////////////    xml_Jugador    ///////////////////
    //////////////////////////////////////////////////////*/

     /**
     * @brief escribir el xml de los jugadores
     * @post se escribira la instancia actual de los jugadores del controlador en un archivo xml
     */
    public void escribirXML_jugador(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            // Crear estructura del documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null, "xml", null);

            // Crear elemento raiz
            Element raiz = documento.createElement("Jugadores");
            documento.getDocumentElement().appendChild(raiz);
            
            // Crear nodos para cada producto y colgarlos del arbol
            Element nodoJugador = null;
            Element nodoDatos = null;
            Text texto = null;
            
            Element nodoInventario = null;
            Element nodoInventarios = null;

            for (Jugador jug : jugadores_sistema){
                nodoJugador = documento.createElement("Jugador");
                raiz.appendChild(nodoJugador);

                nodoDatos = documento.createElement("IdJugador");
                nodoJugador.appendChild(nodoDatos);
                texto = documento.createTextNode(jug.getIdPlayer());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("NickName");
                nodoJugador.appendChild(nodoDatos);
                texto = documento.createTextNode(jug.getNickName());
                nodoDatos.appendChild(texto);
                
                nodoDatos = documento.createElement("Nivel");
                nodoJugador.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(jug.getNivel()));
                nodoDatos.appendChild(texto);
                
                nodoInventarios = documento.createElement("Inventarios");
                nodoJugador.appendChild(nodoInventarios);

                for (InventarioCompartido ic : jug.getInventarios()){
                    nodoInventario = documento.createElement("IdInventario");
                    nodoInventarios.appendChild(nodoInventario);
                    texto = documento.createTextNode(ic.getIdInventario());
                    nodoInventario.appendChild(texto);
                }
                
            }

            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("jugadores_sistema.xml"));
            
            transformer.transform(source, resultado);
            
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(TransformerConfigurationException tce){
            tce.printStackTrace();
        }
        catch(TransformerException te){
            te.printStackTrace();
        }
    }

     /**
     * @brief leer el xml de los jugadores y guardarlo en la instancia del controlador
     * @pre haber cargado antes los inventarios (aunque sea de manera parcial)
     * @post leera un archivo xml y guardaran sus datos
     */
    public void leerXML_jugador(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            
            File f = new File("jugadores_sistema.xml");
            if (!f.exists() || f.length() == 0){
                //No lo podemos leero o no existe o esta vacio
            }
            else {
                

                documento = builder.parse(f);

                // Recorrer el arbol
                NodeList jugadores = documento.getElementsByTagName("Jugador");

                for(int i = 0; i < jugadores.getLength(); i++){
                    Node jugador = jugadores.item(i);
                    Element elemento = (Element) jugador;

                    Jugador j = new Jugador (elemento.getElementsByTagName("IdJugador").item(0).getChildNodes().item(0).getNodeValue(), elemento.getElementsByTagName("NickName").item(0).getChildNodes().item(0).getNodeValue(), Integer.parseInt(elemento.getElementsByTagName("Nivel").item(0).getChildNodes().item(0).getNodeValue()));
                    
                    NodeList inventarios = elemento.getElementsByTagName("IdInventario");

                    for (int k = 0; k < inventarios.getLength(); k++) {
                        Node inventarioNode = inventarios.item(k);
                        j.setInventarioConAcceso(getInventarioById(inventarioNode.getTextContent()));
                    }
                    
                    jugadores_sistema.add(j);
                }

            }
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        catch(SAXException saxe){
            saxe.printStackTrace();
        }
    }
}
