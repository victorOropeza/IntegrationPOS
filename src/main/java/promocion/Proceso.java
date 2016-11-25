package promocion;

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
       File file=new File("promo.xml");
       Handler handler=new Handler();
       saxParser.parse(file, handler);
        CrearPromocion CP=new CrearPromocion();
        ArrayList<Promocion> promos = handler.getPromos();
        ArrayList<String> articulos;
        for (Promocion  p: promos)
        {
           
            articulos=p.getArticulo();
            for(String s: articulos)
            {

                
                System.out.println(CP.Promocion2x1("02A2FDAA4B3D468AA1C46194F21B94A8", p.getVigenciaInicio(), p.getVigenciaFin(),
                        p.getDescripcion(), p.getPromocionID(), "2", "1"));
               
            }
           
        }
        
    }
    
}
