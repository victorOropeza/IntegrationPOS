package Inventario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Proceso {

   
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException 
    {
       SAXParserFactory SPF= SAXParserFactory.newInstance();
       SAXParser saxParser= SPF.newSAXParser();
       File file=new File("C:\\Users\\Alex\\Desktop\\a\\MGARAY_MOVSINV_D.xml");
      
       HandlerInventario handler=new HandlerInventario();
       saxParser.parse(file, handler);
       ArrayList<Inventario> datos = handler.getDatos();
       ArrayList<String> Cs;
       
       for(Inventario i: datos)
       {
           Cs=i.getF();
           for(String s: Cs)
           {
               System.out.println(s);
           }
       }
      
      
        
    }
    
}
