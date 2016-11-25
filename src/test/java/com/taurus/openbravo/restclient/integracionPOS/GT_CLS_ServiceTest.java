package com.taurus.openbravo.restclient.integracionPOS;

/*import com.taurus.openbravo.integracionPOS.service.GT_CLS_MainService;
 import com.taurus.openbravo.integracionPOS.soapclient.impl.GT_CLS_TaurusSyncClientImpl;
 import com.taurus.openbravo.soapclient.entities.files.FileContainer;*/
import com.taurus.openbravo.integracionPOS.main.GT_CLS_Main;
import junit.framework.TestCase;

public class GT_CLS_ServiceTest extends TestCase {

    public void testBuscar() throws Exception {

        GT_CLS_Main main = new GT_CLS_Main();
        main.manejarPeticionesWS();

//       CrearXML Cxml=new CrearXML();   
//       SAXParserFactory SPF= SAXParserFactory.newInstance();
//       SAXParser saxParser= SPF.newSAXParser();
//       File file=new File("C:\\Users\\Alex\\Desktop\\MGARAY_MOVSINV_D.xml");
//      
//       HandlerInventario handler=new HandlerInventario();
//       saxParser.parse(file, handler);
//       ArrayList<Inventario> datos = handler.getDatos();
//       ArrayList<String> Cs;
//       
//       for(Inventario i: datos)
//       {
//           Cs=i.getF();
//           for(String s: Cs)
//           {
//              Cxml.CrearXML(s);
//           }
//       }
//       SAXParserFactory SPF= SAXParserFactory.newInstance();
//       SAXParser saxParser= SPF.newSAXParser();
//       File file=new File("C:\\Users\\Alex\\Desktop\\Documentos\\IntegracionPOS\\integracionPOS\\promos.xml");
//       Handler handler=new Handler();
//       saxParser.parse(file, handler);
//       
//        ArrayList<Promocion> promos = handler.getPromos();
//        ArrayList<String> articulos;
//        for (Promocion  p: promos)
//        {
//           //String IDArticulo, String VigenciaInicio, String VigenciaFin,
//            //                String Descripcion, String IDPromo, String X,String Y
//            //System.out.println(p);
//            articulos=p.getArticulo();
//            for(String s: articulos)
//            {
//                                            
//               GT_CLS_Base operaciones = new GT_CLS_Base();
//               
//               CrearPromocion promocion=new CrearPromocion();
//               System.out.println(s.replace("_", ""));
//               System.out.println(operaciones.buscar("Product", "searchKey", s.replace("_", "") ));
//               String NuevaPromocion=promocion.Promocion2x1(
//                      operaciones.buscar("Product", "searchKey", s.replace("_", "") ),
//                      p.getVigenciaInicio(),
//                      p.getVigenciaFin(), 
//                      p.getDescripcion(),
//                      p.getPromocionID(), 
//                      "3", 
//                      "2"
//               );
//               try
//               {
//                   
//                   
//                   System.out.println(operaciones.agregar(NuevaPromocion, "PricingAdjustment"));
//               }
//               catch(Exception ex)
//               {
//                   ex.printStackTrace();
//               }
//               
//               
//            }
//           
//        }
    }
}
