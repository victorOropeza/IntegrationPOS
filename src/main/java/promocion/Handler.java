package promocion;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler 
{
    private Promocion promocion;
    private final StringBuilder buffer=new StringBuilder();
    private ArrayList<String> articulos=null;
    private final ArrayList<Promocion> promos=new ArrayList();

    public ArrayList<Promocion> getPromos() {
        return promos;
    }
    

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length); 
        
        buffer.append(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName); 
        
        switch(qName)
        {
            case "Promocion":
                promos.add(promocion);
                break;
            case "PromocionID":
                promocion.setPromocionID(buffer.toString());
                break;
            case "Destino":
                promocion.setDestino(buffer.toString());
                break;
            case "Tipo":
                promocion.setTipo(buffer.toString());
                break;
            case "Condicion": 
                promocion.setCondicion(buffer.toString());
                break;
            case "VigenciaInicio":
                promocion.setVigenciaInicio(buffer.toString());
                break;
            case "VigenciaFin":
                promocion.setVigenciaFin(buffer.toString());
                break;
            case "CantidadVenta":
                promocion.setCantidadVenta(buffer.toString());
                break;
            case "Descripcion":
                promocion.setDescripcion(buffer.toString());
                break;
            
            
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); 
        switch(qName)
        {
               
            case "Promocion":
                promocion=new Promocion();
                promocion.setRegistroID(attributes.getValue("RegistroID"));
                break;
            case "Articulo":
                articulos.add(attributes.getValue("ClavePrincipal")+"_"+attributes.getValue("UnidadMedida"));
                break;  
            case "ArticulosVenta":
                articulos=new ArrayList();
                promocion.setArticulo(articulos);
                break;
            case "Beneficios":
                articulos=new ArrayList();
                promocion.setBeneficio(articulos);
                
                break;
            case "PromocionID":
            case "Destino":
            case "Tipo":
            case "Condicion":              
            case "VigenciaInicio":
            case "VigenciaFin":
            case "CantidadVenta":
            case "Descripcion":
                buffer.delete(0, buffer.length());
                break;                
           
               
        }
        
    }

      
}
