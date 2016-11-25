package Inventario;

import com.taurus.openbravo.integracionPOS.main.GT_CLS_Base;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jettison.json.JSONException;


public class CrearXML 
{
    public String CrearXML(String datos) throws IOException, MalformedURLException, JSONException
    {
        return SepararDatos(datos);
    }
    
    private String SepararDatos(String datos) throws IOException, MalformedURLException, JSONException
    {
        String[] Separacion=datos.split(",");
        System.out.println(datos);
        
//        attributes.getValue("C03")+","+ //sucursal
//        attributes.getValue("C04")+","+ //almacen
//        attributes.getValue("C05")+","+ //articulo
//        attributes.getValue("C06")+","+ //cantidad
//        attributes.getValue("C07"); //uom
        
        
        return REST(Separacion);
       
    }
    
    private String REST(String[] datos) throws IOException, MalformedURLException, JSONException
    {
        String storageBin=null,product = null,organization = null,uom = null,cantidad = null,date = null;
        GT_CLS_Base rest=new GT_CLS_Base();
        
        //storageBin
        String a="0B3E1D8D0FCB44FB81EA1A3FF158B086";
//        storageBin=rest.buscar("Warehouse", "description", datos[1]);
        //product
        String b="02A2FDAA4B3D468AA1C46194F21B94A8";
//        product=rest.buscar("Product", "searchKey", datos[2]);
        //organization
        String c="4E087E72C4BC49C3A28FE2EB88FC5560";
//        organization=rest.buscar("Organization", "SearchKey", datos[0]);
        //uom
        String d="100";
//        uom=rest.buscar("UOM", "name", datos[3]);
        //date
        date=obtenerFechaMovimiento();
        
        
        
//        System.out.println(storageBin+"-"+product+"-"+organization+"-"+uom+"-"+cantidad+"-"+date);
        System.out.println(ArmarXML(a,b,c,d,cantidad,date));
        return ArmarXML(a,b,c,d,cantidad,date);
//        return ArmarXML(storageBin, product, organization, uom, cantidad, date);
    }
    
    private String ArmarXML(String storageBin, String product, String organization, String uom, String date,String cantidad)
    {
        String XML=
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<ob:Openbravo xmlns:ob=\"http://www.openbravo.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> \n" +
        "<MaterialMgmtMaterialTransaction>\n" +
        "	<active>true</active>\n" +
        "	<movementType>I+</movementType>\n" +
        "	<storageBin id=\""+storageBin+"\"/>\n" +
        "	<product id=\""+product+"\"/>\n" +
        "	<organization id=\""+organization+"\"/>\n" +
        "	<uOM id=\""+uom+"\"/>\n" +
        "	<movementDate>"+date+"</movementDate>\n" +
        "	<movementQuantity>"+cantidad+"</movementQuantity>\n" +
        "	<costingStatus>NC</costingStatus>\n" +
        "	<checkReservedQuantity>true</checkReservedQuantity>\n" +
        "	<isProcessed>false</isProcessed>\n" +
        "	<checkpricedifference>false</checkpricedifference>\n" +
        "	<manualcostadjustment>false</manualcostadjustment>\n" +
        "	<isCostPermanent>false</isCostPermanent>\n" +
        "</MaterialMgmtMaterialTransaction>\n" +
        "</ob:Openbravo>";
        
        return XML;
    }
    
    private String obtenerFechaMovimiento(){
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fecha1 = formato.format(fecha);
		formato = new SimpleDateFormat("HH:mm:ss.SS");
		String fecha2 = formato.format(fecha);
		String fechaFinal = fecha1+"T"+fecha2+"Z";
		return fechaFinal;
	}
    
}
