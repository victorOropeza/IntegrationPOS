package com.taurus.openbravo.integracionPOS.restclient.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.taurus.openbravo.integracionPOS.restclient.GT_CLS_RestCliente;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_ReadProperties;

//import org.apache.log4j.Logger;
//import static org.junit.Assert.assertEquals;
public class GT_CLS_RestClienteImpl implements GT_CLS_RestCliente {

    private static void assertSingleRecord(JSONObject resp) {
        @SuppressWarnings("unchecked")
        Iterator<String> it = resp.keys();
        Set<String> receivedProperties = new HashSet<String>();

        while (it.hasNext()) {
            receivedProperties.add(it.next());
        }
        // assertThat("Properties received in JSON", receivedProperties,
        // is(equalTo(null)));
    }

    // Este debe ir en la interfaz, parametros(3): 1. entidad (Product,etc),
    // 3. id(Opcional)
    // 4. queryPart, si lleva query en especial. Por el momento no regresa nada
    public void consultar(String entidad, String id, String queryPart) throws JSONException, MalformedURLException, IOException {
        JSONObject resp = new JSONObject(request(entidad, null, id, queryPart, "GET", false));
        // JSONObject resp = new JSONObject(request("Product", "",
        // "5E970B51E2294101858D0F9BDBC6CC45", "", "GET"));
        assertSingleRecord(resp);
    }

    // Debe ir en la interfaz, parametros: entidad, id
    public JSONObject eliminar(String entidad, String id) throws MalformedURLException, IOException, JSONException {
        String res = request(entidad, null, id, null, "DELETE", false);
        JSONObject json = new JSONObject(res);
        JSONObject jsonRes = json.getJSONObject("response");
        // assertEquals("JSON response status", 0, jsonRes.getInt("status"));
        // assertEquals("JSON response data length", 1,
        // jsonRes.getJSONArray("data").length());
        return jsonRes;
    }

    // En caso de actualizar, el json debe traer el id
    public JSONObject agregarActualizar(String entidad, JSONObject json) throws MalformedURLException, IOException, JSONException {
        String content = json.toString();
        String res = request(entidad, content, null, null, "PUT", false);
        JSONObject jsonAll = new JSONObject(res);
        JSONObject jsonRes = jsonAll.getJSONObject("response");
        return jsonRes;
        // assertEquals("JSON response status", 0, jsonRes.getInt("status"));
        // assertEquals("JSON response data length", 1,
        // jsonRes.getJSONArray("data").length());
    }

    /*
	 * Los siguientes métodos son para mandar y obtener la data en XML
     */
    public String consultarPorParametro(String entidad, String nombreParametro, String valorParametro)
            throws MalformedURLException, IOException {
        //System.out.println("\n" + nombreParametro + "\t " + valorParametro);
        if (valorParametro.equals("")) {
            valorParametro = null;
        } else {
            valorParametro = "'" + valorParametro + "'";
        }
        String xml;
        xml = request(entidad, null, null, "where=" + nombreParametro + "=" + valorParametro, "GET", true);
        return xml;
    }

    public String agregarXML(String entidad, String xml) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        String res = request(entidad, xml, null, null, "POST", true);
        // aquí realizar tratamiento para regresar ID
        if (res != null) {
            Document doc = inicializarLecturaXML(res);
            NodeList nodoId = doc.getElementsByTagName(entidad);
            String id = ((Element) nodoId.item(0)).getAttribute("id");
            return id;
        }
        return res;
    }

    public void actualizarXML(String entidad, String xml) throws MalformedURLException, IOException {
        request(entidad, xml, null, null, "POST", true);
    }

    public String agregarActualizarXML(String entidad, String xml) throws MalformedURLException, IOException {
        return request(entidad, xml, null, null, "POST", true);
    }

    public String consultarXML(String entidad, String id, String queryPart) throws MalformedURLException, IOException {
        String res = request(entidad, null, id, queryPart, "GET", true);
        // System.out.println("XML : " + res);
        return res;
    }

    public void eliminarXML(String entidad, String id) throws MalformedURLException, IOException {
        request(entidad, null, id, null, "DELETE", true);
    }

    protected static String request(String entidad, String content, String id, String queryPart, String method,
            boolean xml) throws MalformedURLException, IOException {
        String wsPart = entidad + (id == null ? "" : "/" + id) + (queryPart == null ? "" : "?" + queryPart);
        StringBuilder sb = new StringBuilder("");

        String inicioURL;
        if (xml) {
            inicioURL = "/ws/dal/";
        } else {
            inicioURL = "/org.openbravo.service.json.jsonrest/";
        }
        wsPart = wsPart.replace(" ", "%20");
        String URL = inicioURL + wsPart;
        final HttpURLConnection hc = createConnection(URL, method);
        if (method.equals("POST") || method.equals("PUT")) {
            final OutputStream os = hc.getOutputStream();
            os.write(content.getBytes("UTF-8"));
            os.flush();
            os.close();
        }
        //consumir 
        hc.connect();
        final InputStream is = hc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        String retXML = sb.toString();
        return retXML;

    }

    protected static HttpURLConnection createConnection(String wsPart, String method) throws MalformedURLException, IOException {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                try {
                    return new PasswordAuthentication(GT_CLS_ReadProperties.getPropertieValue("usuario.openbravo"),
                            GT_CLS_ReadProperties.getPropertieValue("password.openbravo").toCharArray());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    return null;
                }
            }
        });
        final URL url = new URL(GT_CLS_ReadProperties.getPropertieValue("url.openbravo") + wsPart);

        final HttpURLConnection hc = (HttpURLConnection) url.openConnection();
        hc.setRequestMethod(method);
        hc.setAllowUserInteraction(false);
        hc.setDefaultUseCaches(false);
        hc.setDoOutput(true);
        hc.setDoInput(true);
        hc.setInstanceFollowRedirects(true);
        hc.setUseCaches(false);

        hc.setRequestProperty("Content-Type", "text/xml");
        return hc;
    }

    private Document inicializarLecturaXML(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
        Document doc = db.parse(is);
        return doc;
    }

}
