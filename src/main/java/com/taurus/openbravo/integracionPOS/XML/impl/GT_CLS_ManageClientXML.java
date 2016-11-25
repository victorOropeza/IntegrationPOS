package com.taurus.openbravo.integracionPOS.XML.impl;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.taurus.openbravo.integracionPOS.XML.GT_CLS_XMLServiceInterface;
import com.taurus.openbravo.integracionPOS.XML.entidades.GT_CLS_Cliente;
import com.taurus.openbravo.integracionPOS.main.GT_CLS_Base;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_ReadProperties;
import java.io.IOException;

public class GT_CLS_ManageClientXML extends GT_CLS_Base implements GT_CLS_XMLServiceInterface<GT_CLS_Cliente> {

    public String generarXML(GT_CLS_Cliente cliente, String tipo) {
        String xml = "";
        if (tipo.equals("location")) {
            StringBuilder sb = new StringBuilder();
            sb.append(addTag("active", "true", null, null, false));
            sb.append(addTag("addressLine1", cliente.getCalle(), null, null, false));
            sb.append(addTag("addressLine2", cliente.getDistrito(), null, null, false));
            sb.append(addTag("cityName", cliente.getPoblacion(), null, null, false));
            sb.append(addTag("postalCode", cliente.getCp(), null, null, false));
            sb.append(addTag("country", null, "id", cliente.getPais(), true));
            sb.append(addTag("region", null, "id", cliente.getEstado(), true));
            sb.append(addTag("tdirmNumex", cliente.getNumero(), null, null, false));
            sb.append(addTag("tdirmLocalidad", cliente.getLocalidad(), null, null, false));
            String xmlAux = sb.toString();
            sb = new StringBuilder(addTag("Location", xmlAux, null, null, false));
            xml = addHeader() + sb.toString() + addFooter();
        } else if (tipo.equals("bpartner")) {
            StringBuilder sb = new StringBuilder();
            sb.append(addTag("active", "true", null, null, false));
            sb.append(addTag("searchKey", cliente.getIdSAP(), null, null, false));
            sb.append(addTag("name", cliente.getNombre(), null, null, false));//
            sb.append(addTag("businessPartnerCategory", null, "id", cliente.getCategoria(), true));// businessPartnerLocationList
            sb.append(addTag("customer", "true", null, null, false));// <customer>true</customer>
            sb.append(addTag("vendor", "false", null, null, false));
            sb.append(addTag("employee", "false", null, null, false));
            sb.append(addTag("taxID", cliente.getRfc(), null, null, false));
            String xmlAux = sb.toString();
            sb = new StringBuilder(addTag("BusinessPartner", xmlAux, null, null, false));
            xml = addHeader() + sb.toString() + addFooter();
        } else if (tipo.equals("bpartner_location")) {
            StringBuilder sb = new StringBuilder();
            sb.append(addTag("active", "true", null, null, false));
            sb.append(addTag("invoiceToAddress", "true", null, null, false));
            sb.append(addTag("shipToAddress", "true", null, null, false));
            sb.append(addTag("payFromAddress", "true", null, null, false));
            sb.append(addTag("remitToAddress", "true", null, null, false));
            sb.append(addTag("phone", cliente.getTelefono(), null, null, false));
            sb.append(addTag("locationAddress", null, "id", cliente.getC_location_id(), true));
            sb.append(addTag("businessPartner", null, "id", cliente.getId(), true));
            String xmlAux = sb.toString();
            sb = new StringBuilder(addTag("BusinessPartnerLocation", xmlAux, null, null, false));
            xml = addHeader() + sb.toString() + addFooter();
        }
        return xml;
    }

    @Override
    public void procesarXML(NodeList nodos) throws IOException {

        for (int i = 0; i < nodos.getLength(); i++) {
            GT_CLS_Cliente cliente = new GT_CLS_Cliente();
            Element elementoCliente = (Element) nodos.item(i);
            NodeList nodeList = elementoCliente.getChildNodes();

            for (int j = 0; j < nodeList.getLength(); j++) {

                if (nodeList.item(j).getNodeName().equals("Direccion")) {
                    /*
					 * ES EL BLOQUE DE DIRECCION Y DEBO REVISAR LOS NODOS DENTRO
					 * DE ESTE
                     */
                    Element elementoDirecccion = (Element) nodeList.item(j);
                    NodeList nodoDireccion = elementoDirecccion.getChildNodes();// Direccion
                    for (int indexDir = 0; indexDir < nodoDireccion.getLength(); indexDir++) {
                        if (nodoDireccion.item(indexDir).getNodeName().equals("Pais")) {
                            cliente.setPais(nodoDireccion.item(indexDir).getTextContent());
                        } else if (nodoDireccion.item(indexDir).getNodeName().equals("Localidad")) {
                            cliente.setLocalidad(nodoDireccion.item(indexDir).getTextContent());
                        } else if (nodoDireccion.item(indexDir).getNodeName().equals("Poblacion")) {
                            cliente.setPoblacion(nodoDireccion.item(indexDir).getTextContent());
                        } else if (nodoDireccion.item(indexDir).getNodeName().equals("Distrito")) {
                            cliente.setDistrito(nodoDireccion.item(indexDir).getTextContent());
                        } else if (nodoDireccion.item(indexDir).getNodeName().equals("CP")) {
                            cliente.setCp(nodoDireccion.item(indexDir).getTextContent());
                        } else if (nodoDireccion.item(indexDir).getNodeName().equals("Estado")) {
                            cliente.setEstado(nodoDireccion.item(indexDir).getTextContent());
                        } else if (nodoDireccion.item(indexDir).getNodeName().equals("Calle")) {
                            cliente.setCalle(nodoDireccion.item(indexDir).getTextContent());
                        } else if (nodoDireccion.item(indexDir).getNodeName().equals("Numero")) {
                            cliente.setNumero(nodoDireccion.item(indexDir).getTextContent());
                        }
                    }
                } else if (nodeList.item(j).getNodeName().equals("RFC")) {
                    cliente.setRfc(nodeList.item(j).getTextContent());
                } else if (nodeList.item(j).getNodeName().equals("IDSAP")) {
                    cliente.setIdSAP(nodeList.item(j).getTextContent());
                } else if (nodeList.item(j).getNodeName().equals("Telefono")) {
                    cliente.setTelefono(nodeList.item(j).getTextContent());
                } else if (nodeList.item(j).getNodeName().equals("FormasPago")) {
                    cliente.setFormaPago(nodeList.item(j).getTextContent());
                } else if (nodeList.item(j).getNodeName().equals("Nombre")) {
                    cliente.setNombre(nodeList.item(j).getTextContent());
                }
            }
            /* FIN DEL 'FOR' QUE RECORRE CADA NODO DE CLIENTE */
 /* SE DEBE REVISAR SI ESE CLIENTE NO EXISTE!!!! */
            System.out.println("Buscar BusinessPartner // " + buscar("BusinessPartner", "searchKey", cliente.getIdSAP()));
                               
            if (buscar("BusinessPartner", "searchKey", cliente.getIdSAP()) == null) {
                                /*
				 * Se debe buscar: pais, estado,
				 * businessPartnerCategory,pricelist, formaPago
                 */
                //System.out.println("validarDatosCliente // " + validarDatos(cliente));
                
                cliente = validarDatos(cliente);
                if (cliente != null) {
                    String xml = generarXML(cliente,
                            "location");
                    System.out.println(xml);
                    /* AQUI GENERA XML PARA C_LOCATION */
                    String id = agregar(xml, "Location");
                    if (id != null) {
                        cliente.setC_location_id(id);
                        xml = generarXML(cliente, "bpartner");
                        System.out.println(xml);
                        id = agregar(xml, "BusinessPartner");
                        if (id != null) {
                            cliente.setId(id);
                            xml = generarXML(cliente, "bpartner_location");
                            System.out.println(xml);
                            if (!xml.equals("")) {
                                agregar(xml, "BusinessPartnerLocation");
                            }
                        }
                    }
                }
            } // EL CLIENTE YA ESTA DADO DE ALTA (ACTUALIZAR!!)
            else {
                String id = buscar("BusinessPartner", "searchKey", cliente.getIdSAP());
            }
        }
        /* FIN DEL FOR PRINCIPAL */
    }/* FIN MéTODO */


    public GT_CLS_Cliente validarDatos(GT_CLS_Cliente cliente) throws IOException {

        String idPais = buscar("Country", "iSOCountryCode", cliente.getPais());
        
        System.out.println("Id Pais // " + idPais);
        if (idPais != null) {
            cliente.setPais(idPais);
        } else {
            //guardar log antes
            return null;
        }

        String idEstado = buscar("Region", "description", cliente.getEstado());
        System.out.println("Id Estado // " + idEstado);
        if (idEstado != null) {
            cliente.setEstado(idEstado);
        } else {
            return null;
        }

        String idBPCate = buscar("BusinessPartnerCategory", "name", "Clientes");// REVISAR
        System.out.println("Id BPCate // " + idBPCate);
        
        if (idBPCate != null) {
            cliente.setCategoria(idBPCate);
        } else {
            return null;
        }

        String idPriceList = buscar("PricingPriceList", "name", GT_CLS_ReadProperties.getPropertieValue("pricing.PriceList"));// REVISAR
        System.out.println("Id PriceList // " + idPriceList);
        if (idPriceList != null) {
            cliente.setPricelist(idPriceList);
        } else {
            return null;
        }

        String idFormaPago = buscar("FIN_PaymentMethod", "description", cliente.getFormaPago());
        System.out.println("Id Forma Pago // " + idFormaPago);
        if (idFormaPago != null) {
            cliente.setFormaPago(idFormaPago);
        } else {
            return null;
        }

        return cliente;
    }
}
