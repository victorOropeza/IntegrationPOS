package com.taurus.openbravo.restclient.integracionPOS;

import org.codehaus.jettison.json.JSONObject;

import com.taurus.openbravo.integracionPOS.restclient.impl.GT_CLS_RestClienteImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GT_CLS_ClienteTest extends TestCase {

	
	
	public GT_CLS_ClienteTest(String name) {
		super(name);
	}
	public static Test suite()
    {
        return new TestSuite( GT_CLS_ClienteTest.class );
    }

	public void testAgregar() throws Exception{
		//GT_CLS_ClienteImpl clienteImpl = new GT_CLS_ClienteImpl();
		String content = "{" //
                + " \"data\": {" //
                + " \"_entityName\": \"Product\"," //
                + " \"_new\": true," //Quitar para modificar el objeto
                //+ " \"id\": \"6EEFBD2B38D74637B8F2F96A78C0A9DF\", "
                + " \"organization\": \"26EE7342B6B946A787736DF5C7CDFED0\"," //
                + " \"active\": true," //
                + " \"searchKey\": \"NUEVO04\"," //
                + " \"name\": \"Super nuevo 4444\"," //
                + " \"uOM\":\"22AC088DD6004FEAACCF50D801B9A7A3\", "
                + " \"summaryLevel\":false,"
                + " \"stocked\":true, "
                + " \"purchase\":true, "
                + " 	\"sale\":true, "
                + "	\"billOfMaterials\":false, "
                + "	\"printDetailsOnInvoice\":false, "
                + "	\"printDetailsOnPickList\":false, "
                + "	\"bOMVerified\":false, "
                + "	\"productCategory\":\"A3F6B5F65823426D87EA6921D52A0229\", "
                + "	\"volume\":0, "
                + "	\"weight\":0, "
                + "	\"taxCategory\":\"8E85B371792340A2A93A63CA2E0C4740\", "
                + "	\"discontinued\":false, "
                + "	\"processNow\":false, "
                + "	\"productType\":\"I\", "
                + "	\"enforceAttribute\":false, "
                + "	\"calculated\":false, "
                + "	\"production\":false, "
                + "	\"quantityType\":false, "
                + "	\"contmxDeducirietu\":false, "
                + "	\"isquantityvariable\":false, "
                + "	\"deferredRevenue\":false, "
                + "	\"contmxDeducirisr\":false, "
                + "	\"isdeferredexpense\":false, "
                + "	\"bookUsingPurchaseOrderPrice\":false, "
                + "	\"isGeneric\":false, "
                + "	\"createVariants\":false, "
                + "	\"obposScale\":false, "
                + "	\"updateInvariants\":false, "
                + "	\"obposGroupedproduct\":true, "
                + "	\"obposShowstock\":false, "
                + "	\"obposShowChDesc\":true, "
                + "	\"manageVariants\":false "
                + " }" //
                + "}";
		JSONObject json = new JSONObject(content);
		//clienteImpl.consultar("Product", "6EEFBD2B38D74637B8F2F96A78C0A9DF", null);//("Product", json);
		//clienteImpl.eliminar("Product", "6EEFBD2B38D74637B8F2F96A78C0A9DF");
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ob:Openbravo xmlns:ob=\"http://www.openbravo.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">  "
				+ "  <Product>  "
				+ "    <client id=\"9703E31F69644A4AA0306AC426279C7D\" entity-name=\"ADClient\" identifier=\"Grupo Mayoreo Garay\"/>  "
				+ "    <organization id=\"0\" entity-name=\"Organization\" identifier=\"*\"/>  "
				+ "    <active>true</active>  "
				+ "    <searchKey>NUE00555</searchKey>  "
				+ "    <name>Producto nuevo 555555</name>  "
				+ "    <description xsi:nil=\"true\"/>  "
				+ "    <comments xsi:nil=\"true\"/>  "
				+ "    <helpComment xsi:nil=\"true\"/>  "
				+ "    <uPCEAN>7503000098713</uPCEAN>  "
				+ "    <sKU xsi:nil=\"true\"/>  "
				+ "    <uOM id=\"E8968E7C2927435C9FB71E73BD2050DC\" entity-name=\"UOM\" identifier=\"EXB\"/>  "
				+ "    <salesRepresentative xsi:nil=\"true\"/>  "
				+ "    <summaryLevel>false</summaryLevel>  "
				+ "    <stocked>true</stocked>  "
				+ "    <purchase>true</purchase>  "
				+ "    <sale>true</sale>  "
				+ "    <billOfMaterials>false</billOfMaterials>  "
				+ "    <printDetailsOnInvoice>false</printDetailsOnInvoice>  "
				+ "    <printDetailsOnPickList>false</printDetailsOnPickList>  "
				+ "    <bOMVerified>false</bOMVerified>  "
				+ "    <productCategory id=\"BCF6887ED1664F568A2365999C77D19A\" entity-name=\"ProductCategory\" identifier=\"Azúcar\"/>  "
				+ "    <classification xsi:nil=\"true\"/>  "
				+ "    <volume>0</volume>  "
				+ "    <weight>0</weight>  "
				+ "    <shelfWidth xsi:nil=\"true\"/>  "
				+ "    <shelfHeight xsi:nil=\"true\"/>  "
				+ "    <shelfDepth xsi:nil=\"true\"/>  "
				+ "    <unitsPerPallet xsi:nil=\"true\"/>  "
				+ "    <taxCategory id=\"AF96B5EED9D74E49BA08961DF9F027FC\" entity-name=\"FinancialMgmtTaxCategory\" identifier=\"EXENTO\"/>  "
				+ "    <resource xsi:nil=\"true\"/>  "
				+ "    <discontinued>false</discontinued>  "
				+ "    <discontinuedBy xsi:nil=\"true\"/>  "
				+ "    <processNow>false</processNow>  "
				+ "    <expenseType xsi:nil=\"true\"/>  "
				+ "    <productType>I</productType>  "
				+ "    <imageURL xsi:nil=\"true\"/>  "
				+ "    <descriptionURL xsi:nil=\"true\"/>  "
				+ "    <guaranteedDays xsi:nil=\"true\"/>  "
				+ "    <versionNo xsi:nil=\"true\"/>  "
				+ "    <attributeSet xsi:nil=\"true\"/>  "
				+ "    <attributeSetValue xsi:nil=\"true\"/>  "
				+ "    <downloadURL xsi:nil=\"true\"/>  "
				+ "    <freightCategory xsi:nil=\"true\"/>  "
				+ "    <storageBin xsi:nil=\"true\"/>  "
				+ "    <image xsi:nil=\"true\"/>  "
				+ "    <businessPartner xsi:nil=\"true\"/>  "
				+ "    <printPrice>true</printPrice>  "
				+ "  </Product>  "
				+ "</ob:Openbravo>  ";
		xml =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ob:Openbravo xmlns:ob=\"http://www.openbravo.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">  "
				+ "   <Product>  "
				+ "     <client id=\"9703E31F69644A4AA0306AC426279C7D\"/>  "
				+ "     <organization id=\"0\"/>  "
				+ "     <active>true</active>  "
				+ "     <searchKey>PAPE014_4</searchKey>  "
				+ "     <name>O.ROLLO ETIQUETA P/BASCULA C/800 PZS</name>  "
				+ "     <uPCEAN>5399935452341</uPCEAN>  "
				+ "     <uOM id=\"E8968E7C2927435C9FB71E73BD2050DC\"/>  "
				+ " 	<productCategory id=\"C7E8F43F5521447F9992B7A27D5B3F58\" />  "
				+ "     <taxCategory id=\"8E85B371792340A2A93A63CA2E0C4740\"/>  "
				+ " 	<stocked>true</stocked>  "
				+ " 	<purchase>true</purchase>  "
				+ " 	<productType>I</productType>  "
				+ " 	<sale>true</sale>  "
				+ " 	<production>false</production>  "
				+ " 	<costType xsi:nil=\"true\"/>  "
				+ "   </Product>  "
				+ " </ob:Openbravo>  ";
		//clienteImpl.consultarXML("Product", "F8E7E8DDBCAF4D5D885593E7AACF0BEF",null);
	}
	
}
