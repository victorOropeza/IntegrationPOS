package com.taurus.openbravo.integracionPOS.XML.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.taurus.openbravo.integracionPOS.XML.GT_CLS_XMLServiceInterface;
import com.taurus.openbravo.integracionPOS.XML.entidades.GT_CLS_NodeSE;
import com.taurus.openbravo.integracionPOS.XML.entidades.GT_CLS_Precio;
import com.taurus.openbravo.integracionPOS.XML.entidades.GT_CLS_VersionLista;
import com.taurus.openbravo.integracionPOS.XML.entidades.GT_CLS_VersionNode;
import com.taurus.openbravo.integracionPOS.main.GT_CLS_Base;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_ReadProperties;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_SaveLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GT_CLS_ManagePriceXML extends GT_CLS_Base implements GT_CLS_XMLServiceInterface<GT_CLS_Precio> {

    @Override
    public void procesarXML(NodeList nodosPrecios) throws IOException {

        List<GT_CLS_VersionNode> versiones = new ArrayList<>();
        List<GT_CLS_Precio> precios = new ArrayList<>();
        List<GT_CLS_Precio> preciosOf = new ArrayList<>();
        List<GT_CLS_Precio> preciosOt = new ArrayList<>();
        String claseCondicion;
        String desctoCve = "VKA0";
        String xmlVL;
        String today;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        today = format1.format(cal.getTime());

        GT_CLS_NodeSE node = new GT_CLS_NodeSE(true, "Default", "Grupo Mayoreo Garay", "Def");
        List<GT_CLS_NodeSE> lcp = new ArrayList<>();
        lcp.add(node);
        GT_CLS_VersionNode newN = new GT_CLS_VersionNode(today, lcp);
        versiones.add(newN);

        //System.out.println(cal.getTime());
        for (int i = 0; i < nodosPrecios.getLength(); i++) {
            GT_CLS_Precio precio = new GT_CLS_Precio();
            // Aquí tengo nodo de precio ya
            Element elementoPrecio = (Element) nodosPrecios.item(i);
            // elementoPrecio.getElementsByTagName("Destino");
            NodeList nodos = elementoPrecio.getChildNodes();
            for (int j = 0; j < nodos.getLength(); j++) {
                Node nodo = nodos.item(j);
                String nombreNodo = nodo.getNodeName();
                if (nombreNodo.equals("Basico")) {
                    NamedNodeMap naNoMap = nodo.getAttributes();
                    precio.setClavePrincipal(naNoMap.getNamedItem("ProductoClave").getNodeValue());
                    NodeList nodosBasico = nodo.getChildNodes();
                    for (int b = 0; b < nodosBasico.getLength(); b++) {
                        Node nodoDeBasico = nodosBasico.item(b);
                        if (nodoDeBasico.getNodeName().equals("Destino")) {
                            precio.setDestino(nodoDeBasico.getTextContent());
                        } else if (nodoDeBasico.getNodeName().equals("Organizacion")) {
                            precio.setDestino(nodoDeBasico.getTextContent());
                        } else if (nodoDeBasico.getNodeName().equals("Canal")) {
                            precio.setCanal(nodoDeBasico.getTextContent());
                        }
                    }
                } else if (nombreNodo.equals("Condiciones")) {

                    NamedNodeMap naNoMap = nodo.getAttributes();
                    claseCondicion = naNoMap.getNamedItem("ClaseCondicion").getNodeValue();
                    precio.setUom(naNoMap.getNamedItem("UnidadMedida").getNodeValue());
                    precio.setClaseCondicion(naNoMap.getNamedItem("ClaseCondicion").getNodeValue());//
                    if (claseCondicion.equals(desctoCve)) {
                        try {
                            versiones = addDate(fixDateE(naNoMap.getNamedItem("VigenciaFin").getNodeValue()), versiones, precio.getClavePrincipal(), precio.getDestino(), precio.getClaseCondicion(), false);
                        } catch (ParseException ex) {
                            Logger.getLogger(GT_CLS_ManagePriceXML.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        versiones = addDate(fixDate(naNoMap.getNamedItem("VigenciaInicio").getNodeValue()), versiones, precio.getClavePrincipal(), precio.getDestino(), precio.getClaseCondicion(), true);
                        precio.setVigenciaFin(naNoMap.getNamedItem("VigenciaFin").getNodeValue());
                        precio.setVigenciaInicio(naNoMap.getNamedItem("VigenciaInicio").getNodeValue());
                    }
                    precio.setMoneda(naNoMap.getNamedItem("Moneda").getNodeValue());
                    NodeList nodosCondiciones = nodo.getChildNodes();
                    for (int c = 0; c < nodosCondiciones.getLength(); c++) {
                        Node nodoDeCondiciones = nodosCondiciones.item(c);
                        if (nodoDeCondiciones.getNodeName().equals("CostoActualizado")) {
                            precio.setCostoActualizado(nodoDeCondiciones.getTextContent());
                        } else if (nodoDeCondiciones.getNodeName().equals("PrecioBase")) {
                            precio.setPrecioBase(nodoDeCondiciones.getTextContent());
                        }
                    }

                }
            }

            precio = validarDatos(precio);
            if (precio != null) {
                //xmlTemp.append(generarXML(precio,""));
                if (precio.getClaseCondicion().equals(desctoCve)) {
                    preciosOf.add(precio);
                } else {
                    preciosOt.add(precio);
                }
            }
        } // for-principal

        preciosOf.addAll(preciosOt);
        precios.addAll(preciosOf);

        int cont = 0;
        String idVL;
        String xmlTemp2 = null;
        boolean flag = false;

        for (GT_CLS_VersionNode v : versiones) {
            List<String> addedP = new ArrayList<>();
            cont++;
            System.out.println("Iteracion -- " + cont);
            GT_CLS_VersionLista VL = new GT_CLS_VersionLista();
            VL.setName(v.getVigencia() + "_" + v.getProdCve().get(0).getDestino() + "_" + v.getProdCve().get(0).getClaseC());
            VL.setValidFromDate(v.getVigencia());

            System.out.println(precios.size());

            xmlVL = generarXMLversionLista(VL, precios.get(0).getDestino());
            if (xmlVL.length() > 0) {
                idVL = agregar(xmlVL, "PricingPriceListVersion");

                for (GT_CLS_Precio precio : precios) {
                    System.out.println(precio.getClavePrincipal() + " -- " + v.getVigencia());
                    flag = false;
                    if (precio.getClaseCondicion().equals(desctoCve)) {
                        try {
                            if (compareDates(v.getVigencia(), fixDate(precio.getVigenciaInicio()), fixDate(precio.getVigenciaFin()))) {
                                xmlTemp2 = generarXML(precio, idVL);
                                addedP.add(precio.getClavePrincipal());
                                System.out.println("Oferta: " + xmlTemp2);
                                flag = true;
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(GT_CLS_ManagePriceXML.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //}
                    } else if (!addedP.contains(precio.getClavePrincipal())) {
                        xmlTemp2 = generarXML(precio, idVL);
                        addedP.add(precio.getClavePrincipal());
                        System.out.println("Regular: " + xmlTemp2);
                        flag = true;
                    }
                    if (flag) {
                        agregar(xmlTemp2, "PricingProductPrice");
                    }
                }
            }
        }
    }

    @Override
    public GT_CLS_Precio validarDatos(GT_CLS_Precio precio) throws IOException {
        // Buscar product por clave principal. Ojo:, en OB las claves
        // principales tendran un sufijo con la uom 
        //String searchKey = precio.getClavePrincipal() + "_" + precio.getUom();
        String searchKey = precio.getClavePrincipal();
        String idProducto = buscar("Product", "searchKey", searchKey);
        if (idProducto != null) {
            precio.setArticulo(idProducto);
            return precio;
        } else {
            List<String> mensaje = new ArrayList<>();
            mensaje.add(GT_CLS_ReadProperties.getPropertieValue("mensaje.precio.noinsertado.producto") + " " + searchKey);
            mensaje.add(GT_CLS_ReadProperties.getPropertieValue("mensaje.precio.noinsertado.elementosrelacionados"));
            GT_CLS_SaveLog.guardarArchivo(mensaje);
            GT_CLS_SaveLog.guardarLogDb(mensaje);
        }
        return null;

    }

    @Override
    public String generarXML(GT_CLS_Precio precio, String idVersion) {
        // Buscar priceList por canal
        //String nombreCanal = precio.getDestino() + "_" + precio.getCanal();
        // 1. Obtener priceList_id por ese canal (name)
        //String idPriceList = buscar("PricingPriceList", "name", nombreCanal);
        //String idVersion = buscar("PricingPriceListVersion", "pricelist.id", idPriceList);
        // 2. id de la version
        String cliente = null;
        try {
            cliente = GT_CLS_ReadProperties.getPropertieValue("sucursal");
        } catch (IOException ex) {
            Logger.getLogger(GT_CLS_ManagePriceXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        String idClient = buscar("ClientInformation", "client.name", cliente);
        String idOrganization = buscar("Organization", "searchKey", precio.getDestino());

        StringBuilder sb = new StringBuilder();
        sb.append(addTag("priceListVersion", null, "id", idVersion, true));//"CED844EDFA054C288CC21EF08EBC8A56"
        sb.append(addTag("product", null, "id", precio.getArticulo(), true));
        sb.append(addTag("client", null, "id", idClient, true));//"CED844EDFA054C288CC21EF08EBC8A56"
        sb.append(addTag("organization", null, "id", idOrganization, true));
        sb.append(addTag("active", "true", null, null, false));
        sb.append(addTag("listPrice", precio.getPrecioBase(), null, null, false));
        sb.append(addTag("standardPrice", precio.getPrecioBase(), null, null, false));
        sb.append(addTag("priceLimit", "0", null, null, false));
        sb.append(addTag("cost", "0", null, null, false));
        sb.append(addTag("algorithm", "S", null, null, false));
        String xmlTemp = sb.toString();
        sb = new StringBuilder();
        sb.append(addTag("PricingProductPrice", xmlTemp, null, null, false));
        xmlTemp = sb.toString();
        sb = new StringBuilder();
        sb.append(addHeader()).append(xmlTemp).append(addFooter());
        return sb.toString();

    }

    public String generarXMLversionLista(GT_CLS_VersionLista vl, String destino) throws IOException {
        // Buscar priceList por canal
        String cliente = GT_CLS_ReadProperties.getPropertieValue("sucursal");
        String idClient = buscar("ClientInformation", "client.name", cliente);
        String idOrganization = buscar("Organization", "searchKey", destino);
        String idList = buscar("PricingPriceList", "name", GT_CLS_ReadProperties.getPropertieValue("pricing.PriceList"));
        //String idListSchema = buscar("PricingPriceListSchema", "name", GT_CLS_ReadProperties.getPropertieValue("filekey.pricingPriceListSchema"));
        String idListSchema = GT_CLS_ReadProperties.getPropertieValue("pricing.PriceListSchemaId");

        StringBuilder sb = new StringBuilder();
        sb.append(addTag("client", null, "id", idClient, true));//"CED844EDFA054C288CC21EF08EBC8A56"
        sb.append(addTag("organization", null, "id", idOrganization, true));
        sb.append(addTag("active", "true", null, null, false));
        sb.append(addTag("name", vl.getName(), null, null, false));
        sb.append(addTag("priceList", null, "id", idList, true));
        sb.append(addTag("priceListSchema", null, "id", idListSchema, true));
        sb.append(addTag("validFromDate", vl.getValidFromDate(), null, null, false));
        sb.append(addTag("create", "false", null, null, false));
        sb.append(addTag("generatePriceListVersion", "false", null, null, false));
        String xmlTemp = sb.toString();
        sb = new StringBuilder();
        sb.append(addTag("PricingPriceListVersion", xmlTemp, null, null, false));
        xmlTemp = sb.toString();
        sb = new StringBuilder();
        sb.append(addHeader()).append(xmlTemp).append(addFooter());
        return sb.toString();

    }

    public String fixDate(String date) {
        String newDate;
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        newDate = year + "-" + month + "-" + day;

        //System.out.println("FechaInicio -- " + newDate);
        return newDate;
    }

    public String fixDateE(String date) throws ParseException {
        String newDate;
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        newDate = year + "-" + month + "-" + day;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(newDate));
        c.add(Calendar.DATE, 1);  // number of days to add
        newDate = sdf.format(c.getTime());
        //System.out.println("FechaFinal -- " + newDate);

        return newDate;
    }

    public boolean compareDates(String dateVL, String datePI, String datePE) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar vl = Calendar.getInstance();
        vl.setTime(sdf.parse(dateVL));
        Calendar p = Calendar.getInstance();
        p.setTime(sdf.parse(datePI));
        Calendar pe = Calendar.getInstance();
        pe.setTime(sdf.parse(datePE));
        pe.add(Calendar.DATE, 1);

        //return (p.compareTo(vl) <= 0 && pe.compareTo(vl) > 0) || (p.compareTo(vl) < 0 && pe.compareTo(vl) > 0);
        return (p.compareTo(vl) <= 0 && pe.compareTo(vl) > 0);
    }

    public String searchDate(String d, List<GT_CLS_VersionNode> n) {
        String dateR = "";
        boolean flag = false;
        for (GT_CLS_VersionNode nodo : n) {
            for (GT_CLS_NodeSE no : nodo.getProdCve()) {
                if (d.equals(no.getProdCve())) {
                    flag = true;
                    dateR = nodo.getVigencia();
                    break;
                }
            }
        }
        if (flag) {
            return dateR;
        } else {
            return "";
        }

    }

    public boolean validateDate(String d, GT_CLS_VersionNode n) {
        boolean flag = false;
        //for (GT_CLS_VersionNode nodo : n) {
        for (GT_CLS_NodeSE no : n.getProdCve()) {
            if (d.equals(no.getProdCve())) {
                flag = true;
                break;
            }
        }
        //}
        return flag;
    }

    public int verifyDate(String d, List<GT_CLS_VersionNode> n) {
        int cont = 0;
        boolean flag = false;
        for (GT_CLS_VersionNode nodo : n) {
            if (d.equals(nodo.getVigencia())) {
                flag = true;
                break;
            }
            cont++;
        }
        if (flag) {
            return cont;
        } else {
            return -1;
        }
    }

    public List<GT_CLS_VersionNode> addDate(String date, List<GT_CLS_VersionNode> n, String cve, String dest, String claseC, boolean i) {
        int search = verifyDate(date, n);
        GT_CLS_NodeSE node = new GT_CLS_NodeSE(i, cve, dest, claseC);

        if (search != -1) {
            n.get(search).getProdCve().add(node);
        } else {
            List<GT_CLS_NodeSE> lcp = new ArrayList<>();
            lcp.add(node);
            GT_CLS_VersionNode newN = new GT_CLS_VersionNode(date, lcp);
            n.add(newN);
        }
        return n;
    }
}
