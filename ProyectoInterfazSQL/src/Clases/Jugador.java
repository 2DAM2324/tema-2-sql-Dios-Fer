package Clases;


import java.util.ArrayList;

/**
 *
 * @author Fernando Ortega Castro
 */
public class Jugador {
    
    /********************************
     ********   Atributos   *********
     ********************************/
    
    private String IdPlayer;  //genearda automaticamente o cargada
    private String NickName; 
    private int nivel; 
    private static int JugadoresActuales=0;  //generacion de id
    ArrayList<InventarioCompartido> inventariosConAcceso;
    
    /********************************
     *******   Constructor   ********
     ********************************/
    
    
    public Jugador() {
        JugadoresActuales++;
        setIdPlayer("J-" + JugadoresActuales);
        setNickName ("");
        setNivel (0);
        inventariosConAcceso = new ArrayList<InventarioCompartido>();
    }
    
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
        
        //actualizacion de JugadoresActuales en carga de datos
        if ((Integer.parseInt(Id.substring(2))) > this.getJugadoresActuales()){
            setJugadoresActuales((Integer.parseInt(Id.substring(2))));
        }
        
    }
    
    /**
     * @brief Constructor de Jugador generando id a partir de la variable 
     * @pre se deberan de haber cargado los datos para que se actualize la variable generadora de id
     * @post se actualizara la variable utilizada para la generacion de los id
     * @param String NickName
     * @param int nivel
     */
    public Jugador(String NickName, int nivel) {
        JugadoresActuales++;
        setIdPlayer("J-" + JugadoresActuales);
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
    
    public void setJugadoresActuales(int n) {
        this.JugadoresActuales = n;
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
    
    public int getJugadoresActuales (){
        return this.JugadoresActuales;
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
