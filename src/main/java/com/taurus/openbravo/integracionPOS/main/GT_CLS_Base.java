package com.taurus.openbravo.integracionPOS.main;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.taurus.openbravo.integracionPOS.restclient.impl.GT_CLS_RestClienteImpl;

public class GT_CLS_Base {

    private GT_CLS_RestClienteImpl restClient = new GT_CLS_RestClienteImpl();

    public String buscar(String entidad, String nombreParametro, String valorParametro) {
        String xml;
        try {
            xml = getRestClient().consultarPorParametro(entidad, nombreParametro, valorParametro);
            Document doc = inicializarLecturaXML(xml);
            NodeList nodoId = doc.getElementsByTagName("id");
            if (nodoId.getLength() <= 0) {
                return null;
            }
            String id = nodoId.item(0).getTextContent();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> buscarYObtenerOtrosParametros(String entidad, String nombreParametro, String valorParametro, String parametroADevolver) {
        String xml;
        try {
            xml = getRestClient().consultarPorParametro(entidad, nombreParametro, valorParametro);
            Document doc = inicializarLecturaXML(xml);
            NodeList nodo = doc.getElementsByTagName(parametroADevolver);

            if (nodo.getLength() <= 0) {
                return null;
            } else {
                List<String> valores = new ArrayList<>();
                for (int i = 0; i < nodo.getLength(); i++) {

                    String valor = nodo.item(i).getTextContent();
                    valores.add(valor);

                }
                return valores;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String agregar(String xml, String entidad) {
        try {
            String id = getRestClient().agregarXML(entidad, xml);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizar(String xml, String entidad) {
        try {
            getRestClient().actualizarXML(entidad, xml);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Document inicializarLecturaXML(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
        Document doc = db.parse(is);
        return doc;
    }

    public Document inicializarLecturaXMLFalsa(String xml) throws ParserConfigurationException, SAXException, IOException {
//		xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
//				+ " <Productos xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"Q00\" Tipo=\"WBBDLD04\" ArchivoOrigen=\"WBBDLD_00140003\"> "
//				+ "   <Producto RegistroID=\"1\"> " + "     <Basico ClavePrincipal=\"ACCO0003\"> "
//				+ "       <Descripcion>Tarro test</Descripcion> " + "       <Grupo>Aderezos Y Mayonesas</Grupo> "
//				+ "       <UnidadBase>PCE</UnidadBase> " + "       <AgrupoadorPrecios /> "
//				+ "       <EstatusBloqueo /> " + "       <Sucursal>Grupo Mayoreo Garay</Sucursal> "
//				+ "       <IndicadorABC>A</IndicadorABC> " + "       <Marca /> " + "     </Basico> "
//				+ "     <Impuestos> " + "       <ImpuestoClave>HONORARIOS</ImpuestoClave> " + "     </Impuestos> "
//				+ "     <UnidadesMedida> " + "       <UnidadMedida Clave=\"PCE\" Cantidad=\"12.0000\"> "
//				+ "         <CodigosBarras> "
//				+ "           <CodigoBarras Tipo=\"IC\" Principal=\"\" Valor=\"7502218211003\" /> "
//				+ "           <CodigoBarras Tipo=\"IC\" Principal=\"X\"  Valor=\"21111111001\" /> "
//				+ "           <CodigoBarras Tipo=\"IC\" Principal=\"\" Valor=\"7502218211665\" /> "
//				+ "           <CodigoBarras Tipo=\"IC\" Principal=\"\" Valor=\"7502218211004\" /> "
//				+ "         </CodigosBarras> " + "       </UnidadMedida> "
//				+ "       </UnidadesMedida> "
//				+ "   </Producto> " + " </Productos> ";
//        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
//                + "<Productos xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"P00\" Tipo=\"WBBDLD04\" ArchivoOrigen=\"WBBDLD_00992176\">"
//                + "	<Producto RegistroID=\"2\">"
//                + "		<Basico ClavePrincipal=\"ALBE0075\">"
//                + "			<Descripcion>PAPILL GERBER E-2 JAMON/VERDURA 24/200GR</Descripcion>"
//                + "			<Grupo>Meters</Grupo>"
//                + "			<UnidadBase>PCE</UnidadBase>"
//                + "			<AgrupadorPrecios />"
//                + "			<EstatusBloqueo />"
//                + "			<Sucursal>The White Valley Group</Sucursal>"
//                + "			<IndicadorABC>B</IndicadorABC>"
//                + "			<Marca>GERBER</Marca>"
//                + "		</Basico>"
//                + "		<Impuestos>"
//                + "			<ImpuestoClave>IVA Normal Servicios</ImpuestoClave>"
//                + "		</Impuestos>"
//                + "		<UnidadesMedida>"
//                + "			<UnidadMedida Clave=\"Kilogram\" Cantidad=\"26.0000\">"
//                + "				<CodigosBarras>"
//                + "					<CodigoBarras Tipo=\"IC\" Principal=\"X\" Valor=\"27501000900120\" />"
//                + "				</CodigosBarras>"
//                + "			</UnidadMedida>"
//                + "		</UnidadesMedida>"
//                + "	</Producto></Productos>";

//        xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
//                + "<Clientes xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"P00\" Tipo=\"DEBMAS04\" ArchivoOrigen=\"DEBMAS_00992310\">"
//                + "	<Cliente RegID=\"1\">"
//                + "		<IDSAP>0000000474</IDSAP>"
//                + "		<Tratamiento>Señor</Tratamiento>"
//                + "		<GrupoCuentas>Y002</GrupoCuentas>"
//                + "		<Nombre>IMPULSORA SAHUAYO  SA DE CV</Nombre>"
//                + "		<Telefono />"
//                + "		<Zona>R0020</Zona>"
//                + "		<RFC>ISA950810229</RFC>"
//                + "		<Direccion>"
//                + "			<Pais>US</Pais>"
//                + "			<Localidad />"
//                + "			<Poblacion>2 DE OCTUBRE</Poblacion>"
//                + "			<Distrito>CHIHUAHUA</Distrito>"
//                + "			<CP>31375</CP>"
//                + "			<Estado>Rhode Island</Estado>"
//                + "			<Calle>17 DE JUNIO</Calle>"
//                + "			<Numero>188</Numero>"
//                + "		</Direccion>"
//                + "		<CondicionesComerciales>"
//                + "			<CondicionComercial>"
//                + "				<Organizacion>GV01</Organizacion>"
//                + "				<CanalDistribucion>DG</CanalDistribucion>"
//                + "				<ListaPrecioClave />"
//                + "				<DiasCredito>Y003</DiasCredito>"
//                + "				<SucursalAsignada>CD01</SucursalAsignada>"
//                + "				<VendedorAsignado>TE1</VendedorAsignado>"
//                + "				<Multitienda />"
//                + "				<Interlocutores>"
//                + "					<Interlocutor Tipo=\"AG\" TipoConsec=\"000\" IDSAP=\"0000000474\" />"
//                + "					<Interlocutor Tipo=\"RE\" TipoConsec=\"000\" IDSAP=\"0000000474\" />"
//                + "					<Interlocutor Tipo=\"RG\" TipoConsec=\"000\" IDSAP=\"0000000474\" />"
//                + "					<Interlocutor Tipo=\"WE\" TipoConsec=\"000\" IDSAP=\"0000000474\" />"
//                + "				</Interlocutores>"
//                + "			</CondicionComercial>"
//                + "		</CondicionesComerciales>"
//                + "		<FormasPago>Cheque</FormasPago>"
//                + "		<RutaVisita>"
//                + "			<Dia />"
//                + "			<Frecuencia>VS</Frecuencia>"
//                + "			<Bloqueo />"
//                + "		</RutaVisita>"
//                + "	</Cliente>"
//                + "</Clientes>";
        xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<Precios xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"P00\" Tipo=\"COND_A02\" ArchivoOrigen=\"COND_A_00992501\">"
                + "	<Precio RegistroID=\"1\">"
                + "		<Basico ProductoClave=\"SHAC0019\">"
                + "			<Destino>Grupo Mayoreo Garay</Destino>"
                + "			<Organizacion>Grupo Mayoreo Garay</Organizacion>"
                + "			<Canal>DG</Canal>"
                + "			<ListaPrecioClave />"
                + "		</Basico>"
                + "		<Condiciones UnidadMedida=\"BX\" ClaseCondicion=\"VKA0\" Moneda=\"MXN\" VigenciaInicio=\"20161127\" VigenciaFin=\"20161130\">"
                + "			<CostoActualizado>176.40</CostoActualizado>"
                + "			<PrecioBase>142.80</PrecioBase>"
                + "		</Condiciones>"
                + "	</Precio>"
                + "	<Precio RegistroID=\"2\">"
                + "		<Basico ProductoClave=\"SHAC0018\">"
                + "			<Destino>Grupo Mayoreo Garay</Destino>"
                + "			<Organizacion>Grupo Mayoreo Garay</Organizacion>"
                + "			<Canal>DG</Canal>"
                + "			<ListaPrecioClave />"
                + "		</Basico>"
                + "		<Condiciones UnidadMedida=\"BX\" ClaseCondicion=\"VKA0\" Moneda=\"MXN\" VigenciaInicio=\"20161128\" VigenciaFin=\"20161029\">"
                + "			<CostoActualizado>1076.40</CostoActualizado>"
                + "			<PrecioBase>342.80</PrecioBase>"
                + "		</Condiciones>"
                + "	</Precio>"
                 + "	<Precio RegistroID=\"2\">"
                + "		<Basico ProductoClave=\"SHAC0017\">"
                + "			<Destino>Grupo Mayoreo Garay</Destino>"
                + "			<Organizacion>Grupo Mayoreo Garay</Organizacion>"
                + "			<Canal>DG</Canal>"
                + "			<ListaPrecioClave />"
                + "		</Basico>"
                + "		<Condiciones UnidadMedida=\"BX\" ClaseCondicion=\"VKA0\" Moneda=\"MXN\" VigenciaInicio=\"20161201\" VigenciaFin=\"20161205\">"
                + "			<CostoActualizado>376.40</CostoActualizado>"
                + "			<PrecioBase>442.80</PrecioBase>"
                + "		</Condiciones>"
                + "	</Precio>"
                + "	<Precio RegistroID=\"2\">"
                + "		<Basico ProductoClave=\"SHAC0016\">"
                + "			<Destino>Grupo Mayoreo Garay</Destino>"
                + "			<Organizacion>Grupo Mayoreo Garay</Organizacion>"
                + "			<Canal>DG</Canal>"
                + "			<ListaPrecioClave />"
                + "		</Basico>"
                + "		<Condiciones UnidadMedida=\"BX\" ClaseCondicion=\"VKA0\" Moneda=\"MXN\" VigenciaInicio=\"20161203\" VigenciaFin=\"20161208\">"
                + "			<CostoActualizado>11076.40</CostoActualizado>"
                + "			<PrecioBase>11342.80</PrecioBase>"
                + "		</Condiciones>"
                + "	</Precio>"
                 + "	<Precio RegistroID=\"2\">"
                + "		<Basico ProductoClave=\"SHAC0016\">"
                + "			<Destino>Grupo Mayoreo Garay</Destino>"
                + "			<Organizacion>Grupo Mayoreo Garay</Organizacion>"
                + "			<Canal>DG</Canal>"
                + "			<ListaPrecioClave />"
                + "		</Basico>"
                + "		<Condiciones UnidadMedida=\"BX\" ClaseCondicion=\"ZPR2\" Moneda=\"MXN\">"
                + "			<CostoActualizado>15076.40</CostoActualizado>"
                + "			<PrecioBase>15342.80</PrecioBase>"
                + "		</Condiciones>"
                + "	</Precio>"
                + "	<Precio RegistroID=\"1\">"
                + "		<Basico ProductoClave=\"SHAC0019\">"
                + "			<Destino>Grupo Mayoreo Garay</Destino>"
                + "			<Organizacion>Grupo Mayoreo Garay</Organizacion>"
                + "			<Canal>DG</Canal>"
                + "			<ListaPrecioClave />"
                + "		</Basico>"
                + "		<Condiciones UnidadMedida=\"BX\" ClaseCondicion=\"ZPR2\" Moneda=\"MXN\">"
                + "			<CostoActualizado>276.40</CostoActualizado>"
                + "			<PrecioBase>242.80</PrecioBase>"
                + "		</Condiciones>"
                + "	</Precio>"
                 + "	<Precio RegistroID=\"2\">"
                + "		<Basico ProductoClave=\"SHAC0017\">"
                + "			<Destino>Grupo Mayoreo Garay</Destino>"
                + "			<Organizacion>Grupo Mayoreo Garay</Organizacion>"
                + "			<Canal>DG</Canal>"
                + "			<ListaPrecioClave />"
                + "		</Basico>"
                + "		<Condiciones UnidadMedida=\"BX\" ClaseCondicion=\"ZPR2\" Moneda=\"MXN\">"
                + "			<CostoActualizado>476.40</CostoActualizado>"
                + "			<PrecioBase>542.80</PrecioBase>"
                + "		</Condiciones>"
                + "	</Precio>"
                + "	<Precio RegistroID=\"2\">"
                + "		<Basico ProductoClave=\"SHAC0018\">"
                + "			<Destino>Grupo Mayoreo Garay</Destino>"
                + "			<Organizacion>Grupo Mayoreo Garay</Organizacion>"
                + "			<Canal>DG</Canal>"
                + "			<ListaPrecioClave />"
                + "		</Basico>"
                + "		<Condiciones UnidadMedida=\"BX\" ClaseCondicion=\"ZPR2\" Moneda=\"MXN\">"
                + "			<CostoActualizado>1076.40</CostoActualizado>"
                + "			<PrecioBase>1342.80</PrecioBase>"
                + "		</Condiciones>"
                + "	</Precio>"
                + "</Precios>";
//        xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
//                + "<Creditos xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"P00\" Tipo=\"CRESTA01\" ArchivoOrigen=\"CRESTA_00984338\">\n"
//                + "	<Credito RegID=\"1\" IDSAP=\"0000108255\" Limite=\"500000.0000\" Saldo=\"208438.8400\" Bloqueado=\"X\" />\n"
//                + "	<Credito RegID=\"2\" IDSAP=\"0000000707\" Limite=\"150000.0000\" Saldo=\"108542.4000\" Bloqueado=\"\" />\n"
//                + "</Creditos>";
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
        Document doc = db.parse(is);
        return doc;
    }

    public String addHeader() {
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ob:Openbravo xmlns:ob=\"http://www.openbravo.com\" "
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> ";
        return header;
    }

    public String addFooter() {
        return "</ob:Openbravo>";
    }

    public String addTag(String name, String textoContenido, String valueName, String value, boolean selfClosed) {
        String ini = "<";
        String finTag = "/";
        String fin = ">";
        String spc = " ";
        String equal = "=";
        String comillas = "\"";
        StringBuilder sb = new StringBuilder(ini);
        sb.append(name);
        if (valueName != null) {
            sb.append(spc);
            sb.append(valueName);
            sb.append(equal);
            sb.append(comillas);
            sb.append(value);
            sb.append(comillas);
        }
        if (selfClosed) {
            sb.append(finTag);
            sb.append(fin);
        } else {
            sb.append(fin);
            sb.append(textoContenido);
            sb.append(ini);
            sb.append(finTag);
            sb.append(name);
            sb.append(fin);
        }
        return sb.toString();
    }

    public GT_CLS_RestClienteImpl getRestClient() {
        return restClient;
    }

    public void setRestClient(GT_CLS_RestClienteImpl restClient) {
        this.restClient = restClient;
    }
}
