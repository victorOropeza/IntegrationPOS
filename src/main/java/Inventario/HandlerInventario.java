package Inventario;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class HandlerInventario extends DefaultHandler 
{
    private Inventario NuevoInventario;
    private String datosF=null;
    private final ArrayList<Inventario> datos=new ArrayList();
    private final ArrayList<String> f=new ArrayList();
    
    
    public ArrayList<Inventario> getDatos() {
        return datos;
    }
    



    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName); 
        
         switch(qName)
        {
            case "MovimientosInventario":
            {
                NuevoInventario.setF(f);
                datos.add(NuevoInventario);
            }
            case "F":
            {
                f.add(datosF);
            }
         }
        
       
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); 
        switch(qName)
        {
            case "MovimientosInventario":
            {
             
                NuevoInventario=new Inventario();
                break;
            }
                        
            case "F":
            {
                datosF=
                        
                        attributes.getValue("C03")+","+ //sucursal
                        attributes.getValue("C04")+","+ //almacen
                        attributes.getValue("C05")+","+ //articulo
                        attributes.getValue("C06")+","+ //cantidad
                        attributes.getValue("C07"); //uom
                
                
                break;              
                
            }
                
        }
        
    }

      
}
