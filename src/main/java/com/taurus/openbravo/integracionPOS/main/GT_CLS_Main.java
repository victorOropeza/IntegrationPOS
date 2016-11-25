package com.taurus.openbravo.integracionPOS.main;

import java.io.IOException;
import java.util.StringTokenizer;

import org.w3c.dom.Document;

import com.taurus.openbravo.integracionPOS.XML.impl.GT_CLS_ManageClientXML;
import com.taurus.openbravo.integracionPOS.XML.impl.GT_CLS_ManageCreditoXML;
import com.taurus.openbravo.integracionPOS.XML.impl.GT_CLS_ManagePriceXML;
import com.taurus.openbravo.integracionPOS.XML.impl.GT_CLS_ManageProductXML;
import com.taurus.openbravo.integracionPOS.XML.impl.GT_CLS_ManagePromotionXML;
import com.taurus.openbravo.integracionPOS.XML.impl.GT_CLS_ManageStockXML;
import com.taurus.openbravo.integracionPOS.soapclient.impl.GT_CLS_TaurusSyncClientImpl;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_ReadProperties;
import com.taurus.openbravo.soapclient.entities.files.FileContainer;
import javax.xml.parsers.ParserConfigurationException;

public class GT_CLS_Main extends GT_CLS_Base {

    private final GT_CLS_TaurusSyncClientImpl taurusSync = new GT_CLS_TaurusSyncClientImpl();
    private String xml;

    public void manejarPeticionesWS() throws ParserConfigurationException {
//       	try {
//                    //C:\Users\Alex\Desktop\MGARAY_WBBDLD04_D.txt
//                    LeerXMLPruebas nuevo=new LeerXMLPruebas();
//                    Vista.Vista.eventos.append("Buscando archivos...\n");
//                    Thread.sleep(1000);
//                    xml=nuevo.xml("C:\\Users\\Alex\\Desktop\\CD01158.txt");
//                    
//                    System.out.println("----------------------------------------");
//                    
//                    Vista.Vista.eventos.append("Enviando archivos...\n");
//                    
//                    Thread.sleep(1000);
//                    Document doc = inicializarLecturaXML(xml);
//                    try
//                    {
//                         System.out.println(xml);
//                         determinarAccionPorEntidad(doc);
//                    }
//                    catch(Exception ex)
//                    {
//                        ex.printStackTrace();
//                    }
        try {
            String archivos = GT_CLS_ReadProperties.getPropertieValue("filekey.archivos");
            StringTokenizer st = new StringTokenizer(archivos, ",");
            while (st.hasMoreTokens()) {
                String fileKey = st.nextToken();
                // Primero validar si hay archivos y cuantos
                //FileContainer[] files = getTaurusSync().buscarArchivos(fileKey);
                /*if (files.length > 0) {
                 for (FileContainer file : files) {
                 String xml = getTaurusSync().bajarArchivos(file, fileKey);
                 Document doc = inicializarLecturaXMLFalsa(xml);
                 determinarAccionPorEntidad(doc);
                 //String processResult = getTaurusSync().confirmarProcesoArchivo(file, fileKey);
                 //System.out.println("El resultado de processingFile: " + processResult);
                 //break; // QUITAR ESTE!!!!!!!!
                 } // FOR ITERA FILECONTAINER'S
                 }
                 //break;// QUITAR ESTE!!!!!!!!
                 */
                String xml = "";
                FileContainer[] files = new FileContainer[0];
                Document doc = inicializarLecturaXMLFalsa(xml);
                determinarAccionPorEntidad(doc);
            }
        } catch (Exception e) {//Poner excepciones especificas 
            e.printStackTrace();
        }
}

private void determinarAccionPorEntidad(Document doc) throws IOException {
		final String PRODUCTO = GT_CLS_ReadProperties.getPropertieValue("nodo.productos");
		final String PRECIO = GT_CLS_ReadProperties.getPropertieValue("nodo.precios");
		final String PROMOCION = GT_CLS_ReadProperties.getPropertieValue("nodo.promocion");
		final String INVENTARIO = GT_CLS_ReadProperties.getPropertieValue("nodo.inventarios");
		final String CLIENTE = GT_CLS_ReadProperties.getPropertieValue("nodo.clientes");
		final String CREDITO = GT_CLS_ReadProperties.getPropertieValue("nodo.creditos");
		
                
                Vista.Vista.eventos.append("Recibiendo información...\n");
		try{                
                    if (doc.getElementsByTagName(PRODUCTO).getLength() > 0) {
                        Vista.Vista.eventos.append("Procesando productos...\n");
                            GT_CLS_ManageProductXML productXML = new GT_CLS_ManageProductXML();
                            productXML.procesarXML(doc.getElementsByTagName(PRODUCTO));
                    } else if (doc.getElementsByTagName(PRECIO).getLength() > 0) {
                        Vista.Vista.eventos.append("Procesando precios...\n");
                            GT_CLS_ManagePriceXML priceXML = new GT_CLS_ManagePriceXML();
                            priceXML.procesarXML(doc.getElementsByTagName(PRECIO));
                    } else if (doc.getElementsByTagName(INVENTARIO).getLength() > 0) {
                        Vista.Vista.eventos.append("Procesando inventario...\n");
                            GT_CLS_ManageStockXML stockXML = new GT_CLS_ManageStockXML();
                            stockXML.procesarXML(doc.getElementsByTagName(INVENTARIO));
                    } else if (doc.getElementsByTagName(CLIENTE).getLength() > 0) {
                        Vista.Vista.eventos.append("Procesando cliente...\n");
                            GT_CLS_ManageClientXML clientXML = new GT_CLS_ManageClientXML();
                            clientXML.procesarXML(doc.getElementsByTagName(CLIENTE));
                    } else if (doc.getElementsByTagName(CREDITO).getLength() > 0) {
                        Vista.Vista.eventos.append("Procesando credito...\n");
                            GT_CLS_ManageCreditoXML creditoXML = new GT_CLS_ManageCreditoXML();
                            creditoXML.procesarXML(doc.getElementsByTagName(CREDITO));
                    } else if (doc.getElementsByTagName(PROMOCION).getLength() > 0) {
                        Vista.Vista.eventos.append("Procesando promociones...\n");
                            GT_CLS_ManagePromotionXML promoXML = new GT_CLS_ManagePromotionXML();
                            promoXML.procesarXML(xml);
                    } 
                    else
                    {
                        Vista.Vista.eventos.append("datos incompatibles...\n");
                    }
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
	}

	public GT_CLS_TaurusSyncClientImpl getTaurusSync() {
		return taurusSync;
	}

}
