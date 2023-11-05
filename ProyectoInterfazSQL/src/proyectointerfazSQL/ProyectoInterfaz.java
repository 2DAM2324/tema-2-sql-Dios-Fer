package proyectointerfazSQL;
import java.io.IOException;
import org.xml.sax.SAXException;
import vista.Ventana1;



/**
 *
 * @author Fernando Ortega Castro
 */
public class ProyectoInterfaz {  
    
     /**
     * @brief main
     */
    public static void main(String[] args) {
        
        Ventana1 v;
        try{
            v=new Ventana1();
            v.setVisible(true);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        catch(SAXException saxe){
            saxe.printStackTrace();
        }        
    }
}
