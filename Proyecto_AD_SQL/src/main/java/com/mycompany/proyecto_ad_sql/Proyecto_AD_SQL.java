package com.mycompany.proyecto_ad_sql;

import java.io.IOException;
import org.xml.sax.SAXException;
import com.mycompany.proyecto_ad_sql.vista.Ventana1;

/**
 *
 * @author DiosFer
 */
public class Proyecto_AD_SQL {
    
    public static void main(String[] args) {
        
        Ventana1 v;
        try{
            v=new Ventana1();
            v.setVisible(true);
        }
        catch(IOException | ClassNotFoundException | SAXException ioe){
            //ioe.printStackTrace();
        }        
    }
}
