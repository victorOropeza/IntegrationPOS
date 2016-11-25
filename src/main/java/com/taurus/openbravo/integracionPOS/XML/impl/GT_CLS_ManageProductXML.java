package com.taurus.openbravo.integracionPOS.XML.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.taurus.openbravo.integracionPOS.XML.GT_CLS_XMLServiceInterface;
import com.taurus.openbravo.integracionPOS.XML.entidades.GT_CLS_Producto;
import com.taurus.openbravo.integracionPOS.main.GT_CLS_Base;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_ReadProperties;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_SaveLog;

public class GT_CLS_ManageProductXML extends GT_CLS_Base implements GT_CLS_XMLServiceInterface<GT_CLS_Producto> {

    public String agregarActualizar(String xml, boolean isNuevo, String entidad) {
        if (isNuevo) {
            if (!xml.equals("")) {
                return agregar(xml, entidad);
            }
        } else if (!xml.equals("")) {
            actualizar(xml, entidad);
        }
        return null;
    }

    public void procesarXML(NodeList nodosProductos) throws IOException {

        for (int i = 0; i < nodosProductos.getLength(); i++) {
            // Obtiene cada arbol de producto
            Element elementoProducto = (Element) nodosProductos.item(i);
            //// Nodo basico
            NodeList nodoBasico = elementoProducto.getElementsByTagName("Basico");
            // Esto si solo viene un bloque básico, si vienen varios, habrá que
            // meterlo en un for y cambiar el item(0) por el contador
            Element basico = (Element) nodoBasico.item(0);
            String searchKey = basico.getAttribute("ClavePrincipal");
            // Así (item -> 0) porque sólo trae una descripcion, sucursal, etc
            String name = basico.getElementsByTagName("Descripcion").item(0).getTextContent();
            // // Organización
            String product_category = basico.getElementsByTagName("Grupo").item(0).getTextContent();
            //Sucursal
            String sucursal = basico.getElementsByTagName("Sucursal").item(0).getTextContent();
            // Nodo de impuestos 
            NodeList nodoImpuestos = elementoProducto.getElementsByTagName("Impuestos");
            String impuestoClave = ((Element) nodoImpuestos.item(0)).getElementsByTagName("ImpuestoClave").item(0)
                    .getTextContent();
            //String unidadBase = basico.getElementsByTagName("UnidadBase").item(0).getTextContent();
            /* Nodos de unidades de medida, se debe leer antes UnidadBase y esa debe ir en la tabla de M_product*/
            NodeList nodosUMmain = elementoProducto.getElementsByTagName("UnidadesMedida");
            Map<String, Map<String, List<String>>> mapaUMCodBar = new HashMap<>();
            for (int j = 0; j < nodosUMmain.getLength(); j++) {
                NodeList nodosUM = ((Element) nodosUMmain.item(j)).getElementsByTagName("UnidadMedida");
                String medida = "";
                for (int x = 0; x < nodosUM.getLength(); x++) {
                    Map<String, List<String>> mapaCodigos = new HashMap<>();
                    List<String> principal = new ArrayList<>();
                    List<String> restoCodigos = new ArrayList<>();
                    medida = ((Element) nodosUM.item(x)).getAttribute("Clave");
                    NodeList nodosCodBars = ((Element) nodosUM.item(x)).getElementsByTagName("CodigoBarras");
                    for (int y = 0; y < nodosCodBars.getLength(); y++) {
                        if ((((Element) nodosCodBars.item(y)).getAttribute("Principal").equals("X"))) {
                            principal.add(((Element) nodosCodBars.item(y)).getAttribute("Valor"));
                        } else {
                            restoCodigos.add(((Element) nodosCodBars.item(y)).getAttribute("Valor"));
                        }
                    }
                    mapaCodigos.put("Principal", principal);
                    mapaCodigos.put("Otros", restoCodigos);
                    mapaUMCodBar.put(medida, mapaCodigos);
                }
            }
            /*
			 * Este le pone los guiones al final al searchKey, de acuerdo a la
			 * unidad de medida
             */
            Map<String, Map<String, List<String>>> mapaUmCodigoBarraNew = generarSearchKeysInsertar(searchKey,
                    mapaUMCodBar);
            /*
			 * Por cada key del mapa anterior, un insert de producto con un
			 * codigo barra (el principal), un update con los demás codigo
			 * barras y un insert a product list
             */
            Set<String> keys = mapaUmCodigoBarraNew.keySet();
            List<GT_CLS_Producto> listaProductos = new ArrayList<>();
            for (String uomSearchKey : keys) {
                GT_CLS_Producto producto = new GT_CLS_Producto();
                producto.setClavePrincipal(uomSearchKey);
                producto.setDescripcion(name);
                StringTokenizer st = new StringTokenizer(uomSearchKey, "_");
                String uom = st.nextToken();
                uom = st.nextToken();
                producto.setUnidadMedida(uom);
                producto.setGrupo(product_category);
                producto.setImpuestoClave(impuestoClave);
                producto.setCodigoBarraPrincipal(mapaUmCodigoBarraNew.get(uomSearchKey).get("Principal").get(0));
                producto.setCodigosBarrasSec(mapaUmCodigoBarraNew.get(uomSearchKey).get("Otros"));
                producto.setSucursal(sucursal);
                listaProductos.add(producto);
            }
            String[] parts;
            for (GT_CLS_Producto producto : listaProductos) {
                String id = null;
                String xml = "";
                
                if (producto.getCodigoBarraPrincipal() != null) {
                    parts = producto.getClavePrincipal().split("_");
                    producto = validarDatos(producto);
                    //System.out.println("\tParts" + parts[0]);
                    //if (buscar("Product", "searchKey", producto.getClavePrincipal()) == null) {
                    if (buscar("Product", "searchKey", parts[0]) == null) {
                    //System.out.println("\n\tNo searchKey" + producto.getClavePrincipal());
                        //producto = validarDatos(producto);
                        if (producto != null) {
                            xml = generarXML(producto, "Product");//Obtener id para actualizar multiUPC
                            id = agregarActualizar(xml, true, "Product");
                        }//No se encontraron los datos relacionados, no se insertará producto						
                    } else {
                        //producto = validarDatos(producto);
                        //id = buscar("Product", "searchKey", producto.getClavePrincipal());
                        id = buscar("Product", "searchKey", parts[0]);
                        producto.setId(id);
                        xml = generarXMLactualizarProducto(producto);
                        System.out.println("\t" + xml);
                        agregarActualizar(xml, false, "Product");
                        //producto = validarUPCsProductoActualizar(id, producto);
                    }
                    if (id != null) {
                        
                        
                        //xml = generarXMLActualizarUPC(id, producto.getCodigosBarrasSec(), "true");
                        //agregarActualizar(xml, false, "Product"); // Actualizamos product para actualizar los upc
                        //xml = generarXMLActualizarUPC(id, producto.getCodigosBarrasSecDesactivos(), "false");
                        //agregarActualizar(xml, false, "Product"); // Actualizamos product para actualizar los upc
                        //Finalmente, se debe agregar a la lista de productos				 
                        //xml = generarXMLAgregarListaProductos(producto);
                        //agregarActualizar(xml, false, "OBRETCO_Prol_Product");
                    }

                } /* FIN PREGUNTA SI HAY CODIGO DE BARRAS */ else {
                    //No hay codigo de barras principal asociado
                    List<String> mensaje = new ArrayList<>();
                    mensaje.add(GT_CLS_ReadProperties.getPropertieValue("mensaje.producto.noinsertado") + " " + producto.getClavePrincipal());
                    mensaje.add(GT_CLS_ReadProperties.getPropertieValue("mensaje.producto.noinsertado.nocodigobarraprincipal"));
                    GT_CLS_SaveLog.guardarArchivo(mensaje);
                    GT_CLS_SaveLog.guardarLogDb(mensaje);
                }
            }
            /* FIN FOR DE LISTA DE PRODUCTOS A INSERTAR */
        }
        /* for principal */
    }

    private Map<String, Map<String, List<String>>> generarSearchKeysInsertar(String searchKey,
            Map<String, Map<String, List<String>>> mapaUM_Codigos) {
        Set<String> keys = mapaUM_Codigos.keySet();
        Map<String, Map<String, List<String>>> umCodigoBarraMapaNew = new HashMap<>();
        for (String key : keys) {// Cada key es el sufijo
            String newSearchKey = searchKey + "_" + key;
            //String newSearchKey = searchKey;
            Map<String, List<String>> codigoBarras = mapaUM_Codigos.get(key);
            umCodigoBarraMapaNew.put(newSearchKey, codigoBarras);
        }
        return umCodigoBarraMapaNew;
    }

    private boolean validarUPCs(String codBar) {
        String idUPC = buscar("Product", "upc", codBar);
        if (idUPC != null) {
            // Si hay
            return true;
        }
        String idMultiUPC = buscar("obmupc_prod_multiupc", "upc", codBar);

        if (idMultiUPC != null) {
            // Si hay
            return true;
        } else {// Si llego a este punto, no hay, ya que dio null en ambos
            return false;
        }
    }

    private String generarXMLActualizarUPC(String id, List<String> listaCodBar, String active) {
        StringBuilder xml = new StringBuilder(addHeader());
        StringBuilder sb = new StringBuilder();
        for (String codBar : listaCodBar) {
            if (!validarUPCs(codBar)) {
                StringBuilder xmlAux = new StringBuilder();
                StringBuilder xmlAux2 = new StringBuilder();
                xmlAux.append(addTag("upc", codBar, null, null, false));
                xmlAux.append(addTag("product", null, "id", id, true));
                xmlAux.append(addTag("active", active, null, null, false));
                xmlAux2.append(addTag("obmupc_prod_multiupc", xmlAux.toString(), null, null, false));
                sb.append(xmlAux2);
            } else if (active.equals("false")) {
                StringBuilder xmlAux = new StringBuilder();
                StringBuilder xmlAux2 = new StringBuilder();
                xmlAux.append(addTag("upc", codBar, null, null, false));
                xmlAux.append(addTag("product", null, "id", id, true));
                xmlAux.append(addTag("active", active, null, null, false));
                xmlAux2.append(addTag("obmupc_prod_multiupc", xmlAux.toString(), null, null, false));
                sb.append(xmlAux2);
            }
        }
        if (sb.length() > 0) {
            String idTag = addTag("id", id, null, null, false);
            StringBuilder multiUpcTag = new StringBuilder(idTag);
            multiUpcTag.append(addTag("obmupcProdMultiupcList", sb.toString(), null, null, false));
            xml.append(addTag("Product", multiUpcTag.toString(), "id", id, false));
            xml.append(addFooter());
            return xml.toString();
        } else {
            return "";
        }
    }

    public String generarXMLactualizarProducto(GT_CLS_Producto producto) {
//        System.out.println("\n\tuOm - " + producto.getUnidadMedida());
//        System.out.println("\n\tproductCategory - " + producto.getGrupo());
//        System.out.println("\n\ttaxCategory- " + producto.getImpuestoClave());
        StringBuilder xml = new StringBuilder();
        xml.append(addTag("id", producto.getId(), null, null, false));
        xml.append(addTag("name", producto.getDescripcion(), null, null, false));
        xml.append(addTag("uOM", null, "id", producto.getUnidadMedida(), true));
        xml.append(addTag("productCategory", null, "id", producto.getGrupo(), true));
        xml.append(addTag("FinancialMgmtTaxCategory", null, "id", producto.getImpuestoClave(), true));
        StringBuilder xmlFull = new StringBuilder(addTag("Product", xml.toString(), "id", producto.getId(), false));
        xml = new StringBuilder(addHeader());
        xml.append(xmlFull);
        xml.append(addFooter());
        return xml.toString();
    }

    private String generarXMLAgregarListaProductos(GT_CLS_Producto producto) {
        StringBuilder sb = new StringBuilder();
        String idProdList = buscar("OBRETCO_ProductList", "name", producto.getSucursal());//"Productos Disponibles para Venta");// "DA49776CEEC24603BB8E38AFDDBD9493";
        if (idProdList != null) {
            sb.append(addTag("active", "true", null, null, false));
            sb.append(addTag("obretcoProductlist", null, "id", idProdList, true));
            sb.append(addTag("product", null, "id", producto.getId(), true));
            sb.append(addTag("bestseller", "false", null, null, false));
            StringBuilder xml = new StringBuilder(addHeader());
            xml.append(addTag("OBRETCO_Prol_Product", sb.toString(), null, null, false));
            xml.append(addFooter());
            return xml.toString();
        } else {
            return null;
        }
    }

    @Override
    public String generarXML(GT_CLS_Producto producto, String tipo) {
        
//        String tax = buscar("FinancialMgmtTaxCategory", "name", producto.getImpuestoClave());
//	String uom = buscar("UOM", "name", producto.getUnidadMedida());
//	String prodCat = buscar("ProductCategory", "name", producto.getGrupo());
//        
        //if (tax != null && uom != null && prodCat != null) {
        StringBuilder xml = new StringBuilder();
        xml.append(addTag("active", "true", null, null, false));
        xml.append(addTag("searchKey", producto.getClavePrincipal(), null, null, false));
        xml.append(addTag("name", producto.getDescripcion(), null, null, false));
        xml.append(addTag("uPCEAN", producto.getCodigoBarraPrincipal(), null, null, false));
        xml.append(addTag("uOM", null, "id", producto.getUnidadMedida(), true));
        xml.append(addTag("productCategory", null, "id", producto.getGrupo(), true));
        xml.append(addTag("taxCategory", null, "id", producto.getImpuestoClave(), true));
        xml.append(addTag("organization", null, "id", producto.getSucursal(), false));
        xml.append(addTag("stocked", "true", null, null, false));
        xml.append(addTag("purchase", "true", null, null, false));
        xml.append(addTag("productType", "I", null, null, false));
        xml.append(addTag("sale", "true", null, null, false));
        xml.append(addTag("production", "false", null, null, false));
        xml.append(addTag("costType", null, "xsi:nil", "true", true));
        StringBuilder xmlFull = new StringBuilder(addTag("Product", xml.toString(), null, null, false));
        xml = new StringBuilder(addHeader());
        xml.append(xmlFull);
        xml.append(addFooter());
        System.out.println(xml);
        return xml.toString();
//        } else {
//			return "";
//		}
    }

    @Override
    public GT_CLS_Producto validarDatos(GT_CLS_Producto producto) throws IOException {
        String tax = buscar("FinancialMgmtTaxCategory", "name", producto.getImpuestoClave());
        String uom = buscar("UOM", "name", producto.getUnidadMedida());
        String prodCat = buscar("ProductCategory", "name", producto.getGrupo());
        System.out.println("Organization - " + producto.getSucursal());
        System.out.println("Tax - " + producto.getImpuestoClave());
        System.out.println("UOM - " + producto.getUnidadMedida());
        String org = buscar("Organization", "name", producto.getSucursal());
//         System.out.println("\n\ttax" + producto.getImpuestoClave() + "\n\tuom" + producto.getUnidadMedida() + "\n\tprodCat" + producto.getGrupo());
//        System.out.println("\n\ttax" + tax + "\n\tuom" + tax + "\n\tprodCat" + prodCat);

        //if (tax != null && uom != null && prodCat != null && !validarUPCs(producto.getCodigoBarraPrincipal())) {
        System.out.println("\ntax -- " + tax + "\nuom -- " + uom + "\nprodCat -- " + prodCat + "\nOrg -- " + org);
        
        
        if (tax != null && uom != null && prodCat != null) {
            producto.setImpuestoClave(tax);
            producto.setUnidadMedida(uom);
            producto.setGrupo(prodCat);
            producto.setSucursal(org);
            System.out.println("Validation succesful");
            return producto;
        } else {
            List<String> mensaje = new ArrayList<>();
            mensaje.add(GT_CLS_ReadProperties.getPropertieValue("mensaje.producto.noinsertado") + " " + producto.getClavePrincipal());
            mensaje.add(GT_CLS_ReadProperties.getPropertieValue("mensjae.producto.noinsertado.elementosrelacionados"));
            GT_CLS_SaveLog.guardarArchivo(mensaje);
            GT_CLS_SaveLog.guardarLogDb(mensaje);
            System.out.println("Validation error");
        }
        return null;
    }

    private GT_CLS_Producto validarUPCsProductoActualizar(String id, GT_CLS_Producto producto) {
        //Obtener UPC de la tabla de producto para ese id
        List<String> upc_main = buscarYObtenerOtrosParametros("Product", "id", id, "uPCEAN");

        //Obtener UPCs de la tabla de multiupc para ese id
        List<String> upc_sec = buscarYObtenerOtrosParametros("obmupc_prod_multiupc", "product.id", id, "upc");

        List<String> upc_sec_active = new ArrayList<>();
        List<String> upc_sec_inactive = new ArrayList<>();

        if (upc_sec != null) {
            for (String upc : upc_sec) {
                List<String> active = buscarYObtenerOtrosParametros("obmupc_prod_multiupc", "upc", upc, "active");
                if (active.get(0).equals("YES")) {
                    upc_sec_active.add(upc);
                } else {
                    upc_sec_inactive.add(upc);
                }
            }
        }
        String upcPrincipalAnterior = "";
        if (!producto.getCodigoBarraPrincipal().equals(upc_main.get(0))) {
            //debo actualizar el UPC 			
            upcPrincipalAnterior = upc_main.get(0);
            StringBuilder sb = new StringBuilder(addTag("uPCEAN", producto.getCodigoBarraPrincipal(), null, null, false));
            String aux = sb.toString();
            sb = new StringBuilder(addHeader());
            sb.append(addTag("Product", aux, "id", id, false));
            sb.append(addFooter());
            agregarActualizar(sb.toString(), false, "Product");
        }
        List<String> nuevosUPC = producto.getCodigosBarrasSec();
        List<String> upcDesactivar = new ArrayList<>();
        upc_sec_inactive.add(upcPrincipalAnterior);
        for (String inactive : upc_sec_inactive) {
            for (String nuevo : nuevosUPC) {
                if (inactive.equals(nuevo)) {
                    nuevosUPC.add(inactive);
                    break;
                }
            }
        }
        for (String active : upc_sec_active) {
            for (String nuevo : nuevosUPC) {
                if (active.equals(nuevo)) {
                    nuevosUPC.remove(active);
                    break;
                }
            }
        }

        for (String originalUPC : upc_sec_inactive) {
            boolean estaEnNuevos = false;
            for (String newUPC : producto.getCodigosBarrasSec()) {
                if (originalUPC.equals(newUPC)) {
                    //Quiere decir que el original(que está en la bd) si está en los nuevos
                    //Debo quitar este codigo de barras de codigo de barras sec en entidad producto
                    nuevosUPC.remove(newUPC);
                    estaEnNuevos = true;
                    break;
                }
            }
            //producto.setCodigosBarrasSec(nuevosUPC);
            if (!estaEnNuevos) {
                upcDesactivar.add(originalUPC);
            }
        }
        /*if(!upcPrincipalAnterior.equals("") && !nuevosUPC.contains(upcPrincipalAnterior) && upcDesactivar.contains(upcPrincipalAnterior)){
			nuevosUPC.add(upcPrincipalAnterior);
		}*/
        producto.setCodigosBarrasSec(nuevosUPC);
        producto.setCodigosBarrasSecDesactivos(upcDesactivar);
        return producto;
    }

}
