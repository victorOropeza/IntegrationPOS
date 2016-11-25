package com.taurus.openbravo.integracionPOS.XML;

import java.io.IOException;

import org.w3c.dom.NodeList;

import com.taurus.openbravo.integracionPOS.XML.entidades.GT_CLS_EntidadBase;

public interface GT_CLS_XMLServiceInterface<C extends GT_CLS_EntidadBase> {

	public String generarXML(C entidad, String tipo);
	public void procesarXML(NodeList nodos) throws IOException;
	public C validarDatos(C entidad) throws IOException;
}
