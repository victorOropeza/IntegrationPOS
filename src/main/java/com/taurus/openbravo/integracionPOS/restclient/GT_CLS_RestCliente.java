package com.taurus.openbravo.integracionPOS.restclient;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.xml.sax.SAXException;

public interface GT_CLS_RestCliente {
	public void consultar(String entityName, String id, String queryPart) throws MalformedURLException, IOException, JSONException;
	public JSONObject eliminar(String entityName, String id)  throws MalformedURLException, IOException, JSONException ;
	public JSONObject agregarActualizar(String entityName, JSONObject json)  throws MalformedURLException, IOException, JSONException ;
	public String consultarPorParametro(String entidad, String nombreParametro, String valorParametro)  throws MalformedURLException, IOException;
	public String agregarXML(String entidad, String xml)  throws MalformedURLException, IOException, ParserConfigurationException, SAXException;
	public void actualizarXML(String entidad, String xml) throws MalformedURLException, IOException;
	public String agregarActualizarXML(String entityName, String xml) throws MalformedURLException, IOException;
	public String consultarXML(String entityName, String id, String queryPart) throws MalformedURLException, IOException;
	public void eliminarXML(String entityName, String id) throws MalformedURLException, IOException;
}
