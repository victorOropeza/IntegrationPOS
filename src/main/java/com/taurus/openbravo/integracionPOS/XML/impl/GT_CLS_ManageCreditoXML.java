package com.taurus.openbravo.integracionPOS.XML.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.taurus.openbravo.integracionPOS.XML.GT_CLS_XMLServiceInterface;
import com.taurus.openbravo.integracionPOS.XML.entidades.GT_CLS_Credito;
import com.taurus.openbravo.integracionPOS.main.GT_CLS_Base;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_ReadProperties;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_SaveLog;

public class GT_CLS_ManageCreditoXML extends GT_CLS_Base implements GT_CLS_XMLServiceInterface<GT_CLS_Credito>{

	@Override
	public String generarXML(GT_CLS_Credito credito, String tipo) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(addTag("creditLimit", credito.getLimite(), null, null, false));
		sb.append(addTag("creditUsed", credito.getSaldo(), null, null, false));
		String aux = sb.toString();
		sb = new StringBuilder(addHeader());
		sb.append(addTag("BusinessPartner", aux, "id", credito.getIdSAP(), false));
		sb.append(addFooter());
		return sb.toString();
	}

	@Override
	public void procesarXML(NodeList nodos) throws IOException {
		for(int i=0; i<nodos.getLength(); i++){
			GT_CLS_Credito credito = new GT_CLS_Credito();
			Node nodoMov = nodos.item(i);
			NamedNodeMap map = nodoMov.getAttributes();
			String clienteSAP = "";
			if(map!= null){
				clienteSAP = map.getNamedItem("IDSAP").getNodeValue();
				credito.setIdSAP(map.getNamedItem("IDSAP").getNodeValue());
				credito.setLimite(map.getNamedItem("Limite").getNodeValue());
				credito.setSaldo(map.getNamedItem("Saldo").getNodeValue());
				credito.setBloqueado(map.getNamedItem("Bloqueado").getNodeValue());
			}
			credito = validarDatos(credito);
			if(credito!=null){
				String xml = generarXML(credito, null);
				agregar(xml, "BusinessPartner");
			}else{
				//El cliente no existe
				List<String> mensaje = new ArrayList<>();
				mensaje.add(GT_CLS_ReadProperties.getPropertieValue("mensaje.credito.noinsertado" + " " +  clienteSAP));
				GT_CLS_SaveLog.guardarArchivo(mensaje);
				GT_CLS_SaveLog.guardarLogDb(mensaje);
			}
		}//FIN DEL FOR PRINCIPAL
	}

	@Override
	public GT_CLS_Credito validarDatos(GT_CLS_Credito credito) {
		String idBPartner = buscar("BusinessPartner","searchKey", credito.getIdSAP());
		if(idBPartner!=null){
			credito.setIdSAP(idBPartner);
			if(credito.getBloqueado().equals("X")){
				credito.setLimite("0.01");
			}
			return credito;
		}
		return null;
	}

}
