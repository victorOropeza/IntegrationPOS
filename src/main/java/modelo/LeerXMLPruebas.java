package modelo;

import java.io.IOException;


public class LeerXMLPruebas 
{
    
    public String xml(String nombre) throws IOException
    {
          
      
      String x=
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<MovimientosInventario xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ClienteID=\"160\" Ambiente=\"P00\" Destino=\"CD01\" Tipo=\"MOVSINV_D\" Referencia=\"CD01158\" ArchivoOrigen=\"CD01158.txt\">\n" +
"	<F C01=\"CD01158\" C02=\"1716\" C03=\"CD01\" C04=\"A001\" C05=\"COCO0012\" C06=\"0.000\" C07=\"PCE\" C08=\"A\" />\n" +
"	<F C01=\"CD01158\" C02=\"1717\" C03=\"CD01\" C04=\"A001\" C05=\"COCO0013\" C06=\"0.000\" C07=\"PCE\" C08=\"A\" />\n" +
"	<F C01=\"CD01158\" C02=\"1718\" C03=\"CD01\" C04=\"A001\" C05=\"COCO0014\" C06=\"0.000\" C07=\"PCE\" C08=\"A\" />\n" +
"	<F C01=\"CD01158\" C02=\"1719\" C03=\"CD01\" C04=\"A001\" C05=\"COCO0015\" C06=\"0.000\" C07=\"EX\" C08=\"A\" />\n" +
"	<F C01=\"CD01158\" C02=\"1720\" C03=\"CD01\" C04=\"A001\" C05=\"COCO0016\" C06=\"900.000\" C07=\"PCE\" C08=\"A\" />\n" +
"</MovimientosInventario>";
        return x;
    }
    
}
