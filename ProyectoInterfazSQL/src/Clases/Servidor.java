package Clases;

/**
 *
 * @author Fernando Ortega Castro
 */
public class Servidor {
    
    /********************************
     ********   Atributos   *********
     ********************************/
    
    private String IdServer; //genearda automaticamente o cargada
    private String region;
    private static int ServidoresActuales=0;  //generacion de id
    
    /********************************
     *******   Constructor   ********
     ********************************/
    
    public Servidor() {
        ServidoresActuales++;
        setIdServer ("S-" + ServidoresActuales);
        setRegion ("");
    }
    
    /**
     * @brief Constructor de Servidor generando id a partir de la variable
     * @pre se deberan de haber cargado los datos para que se actualize la variable generadora de id
     * @post se actualizara la variable utilizada para la generacion de los id
     * @param String region
     */
    public Servidor(String region) {
        ServidoresActuales++;
        setIdServer ("S-" + ServidoresActuales);
        setRegion (region);
    }
    
    /**
     * @brief Constructor de Servidor con id existente (constructor de carga)
     * @post se actualizara la variable utilizada para la generacion de los id
     * @param String Id
     * @param String region
     */
    public Servidor(String Id, String region) {
        setIdServer (Id);
        setRegion (region);
        //actualizacion de ServidoresActuales en carga de datos
        if ((Integer.parseInt(Id.substring(2))) > this.getServidoresActuales()){
            setServidoresActuales((Integer.parseInt(Id.substring(2))));
        }
    }

    /********************************
     **********   Set's   ***********
     ********************************/
    
    public void setIdServer(String IdServer) {
        this.IdServer = IdServer;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    
    public static void setServidoresActuales(int ServidoresActuales) {
        Servidor.ServidoresActuales = ServidoresActuales;
    }
      
    /********************************
     **********   Get's   ***********
     ********************************/
        
    public String getIdServer() {
        return IdServer;
    }
    
    public String getRegion() {
        return region;
    }

    public static int getServidoresActuales() {
        return ServidoresActuales;
    }
    
}
