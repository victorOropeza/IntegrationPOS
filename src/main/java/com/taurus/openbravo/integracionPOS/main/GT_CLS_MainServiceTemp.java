    package com.taurus.openbravo.integracionPOS.main;

import com.taurus.openbravo.soapclient.entities.files.FileContainer;

//Debe conectarse (o usar las clases que se conectan) con los webservices (SAP y OB),  

public class GT_CLS_MainServiceTemp {


	public String obtenerXML(String tipo, FileContainer file)  {
		/* Aqui debo ir a buscar los archivos pendientes a bajar, de acuerdo al
		 tipo que sean (productos, clientes,etc)
		 buscar archivo de clientes;*/
		String xml = "";
		/*
		 * if (tipo.equals("PRODUCTO")) { xml =
		 * getGt_CLS_ServiceObtenerXMLs().obtenerXMLProductos(file); } else {
		 * xml = ""; }
		 */
		// Aquí pongo el String con el XML mientras

/*		
 				xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ " <Productos xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"Q00\" Tipo=\"WBBDLD04\" ArchivoOrigen=\"WBBDLD_00140003\"> "
				+ "   <Producto RegistroID=\"1\"> " + "     <Basico ClavePrincipal=\"NOTE53\"> "
				+ "       <Descripcion>TestFromJava3</Descripcion> " + "       <Grupo>001001001</Grupo> "
				+ "       <UnidadBase>PCE</UnidadBase> " + "       <AgrupoadorPrecios /> "
				+ "       <EstatusBloqueo /> " + "       <Sucursal>CD01</Sucursal> "
				+ "       <IndicadorABC>A</IndicadorABC> " + "       <Marca /> " + "     </Basico> "
				+ "     <Impuestos> " + "       <ImpuestoClave>C0</ImpuestoClave> " + "     </Impuestos> "
				+ "     <UnidadesMedida> " + "       <UnidadMedida Clave=\"BX\" Cantidad=\"12.0000\"> "
				+ "         <CodigosBarras> "
				+ "           <CodigoBarras Tipo=\"IC\" Principal=\"\" Valor=\"212233445541121\" /> "
				+ "           <CodigoBarras Tipo=\"IC\" Principal=\"X\"  Valor=\"2122334455132\" /> "
				+ "           <CodigoBarras Tipo=\"IC\" Principal=\"\" Valor=\"21223344551143\" /> "
				+ "         </CodigosBarras> " + "       </UnidadMedida> "
				+ "       </UnidadesMedida> "
				+ "   </Producto> " + " </Productos> ";
				*/
		
		/*xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+"<Promociones xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"Q00\" Tipo=\"WPDBBY01\" ArchivoOrigen=\"WPDBBY_01545699_GRUPOMATERIALES\">"
				+"  <Promocion RegistroID=\"1\">"
				+"    <Basico>"
				+"      <PromocionID>PROMO#00034</PromocionID>"
				+"      <Destino>SD01</Destino>"
				+"      <Tipo>MGP</Tipo>"
				+"    </Basico>"
				+"    <Mecanica>"
				+"      <Condicion>BB04</Condicion>"
				+"      <VigenciaInicio>20151201</VigenciaInicio>"
				+"      <VigenciaFin>20151212</VigenciaFin>"
				+"      <CantidadVenta>30.000</CantidadVenta>"
				+"      <Descripcion>1 EN 30 SE PAGA CON PASTA RUEDA</Descripcion>"
				+"      <ArticulosVenta>"
				+"        <Articulo ClavePrincipal=\"\" UnidadMedida=\"\" />"
				+"        <Articulo ClavePrincipal=\"SP113\" UnidadMedida=\"BX\" />"
				+"        <Articulo ClavePrincipal=\"SP115\" UnidadMedida=\"BX\" />"
				+"        <Articulo ClavePrincipal=\"SP116\" UnidadMedida=\"BX\" />"
				+"        <Articulo ClavePrincipal=\"SP117\" UnidadMedida=\"BX\" />"
				+"      </ArticulosVenta>"
				+"      <Beneficios>"
				+"        <Articulo ClavePrincipal=\"PR014\" UnidadMedida=\"BX\" Cantidad=\"1.000\" />"
				+"      </Beneficios>"
				+"    </Mecanica>"
				+"  </Promocion>"
				+"</Promociones>";*/
		
	/*	xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<MovimientosInventario xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ClienteID=\"160\" Ambiente=\"Q00\" Destino=\"SD01\" Tipo=\"MOVSINV_009\" Referencia=\"4900216354\" ArchivoOrigen=\"0090153297.txt\">"
				+ "<Movimiento Referencia=\"4900216354\" Consecutivo=\"0002\" Centro=\"SD01\" Almacen=\"A001\" Articulo=\"NOTE53\" Cantidad=\"8.000\" UnidadMedida=\"BX\" Tipo=\"S\" PedidoPOS=\"0000036671\" PedidoSAP=\"0000088456\" Factura=\"0090153297\" />"
				+ "</MovimientosInventario>";*/
		
		/*xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<Clientes xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"Q00\" Tipo=\"DEBMAS04\" ArchivoOrigen=\"DEBMAS_00118026\">"
				+ "  <Cliente RegID=\"1\">"
				+ "    <IDSAP>000012388</IDSAP>"
				+ "    <Tratamiento>Señor</Tratamiento>"
				+ "    <GrupoCuentas>Y001</GrupoCuentas>"
				+ "    <Nombre>Timothy Camarillo</Nombre>"
				+ "    <Telefono>57018950</Telefono>"
				+ "    <Zona>000</Zona>"
				+ "    <RFC>CAMT130305RS9</RFC>"
				+ "    <Direccion>"
				+ "      <Pais>MX</Pais>"
				+ "      <Localidad>FUENTES MARES</Localidad>"
				+ "      <Poblacion>VILLA JUAREZ</Poblacion>"
				+ "      <Distrito>CHIHUAHUA</Distrito>"
				+ "      <CP>31064</CP>"
				+ "      <Estado>CHI</Estado>"
				+ "      <Calle>CALLE 3 607</Calle>"
				+ "    </Direccion>"
				+ "    <CondicionesComerciales>"
				+ "      <Organizacion>GV01</Organizacion>"
				+ "      <CanalDistribucion>DG</CanalDistribucion>"
				+ "      <DiasCredito>Y002</DiasCredito>"
				+ "      <SucursalAsignada>CD01</SucursalAsignada>"
				+ "      <VendedorAsignado>DG4</VendedorAsignado>"
				+ "      <Multitienda>X</Multitienda>"
				+ "    </CondicionesComerciales>"
				+ "    <FormasPago>CEJPTZ</FormasPago>"
				+ "    <RutaVisita>"
				+ "      <Dia>04</Dia>"
				+ "      <Frecuencia>VS</Frecuencia>"
				+ "      <Bloqueo>X</Bloqueo>"
				+ "    </RutaVisita>"
				+ "    <Interlocutores>"
				+ "      <Interlocutor Tipo=\"AG\" TipoConsec=\"000\" IDSAP=\"0000100005\" />"
				+ "      <Interlocutor Tipo=\"RE\" TipoConsec=\"000\" IDSAP=\"0000100005\" />"
				+ "      <Interlocutor Tipo=\"RG\" TipoConsec=\"000\" IDSAP=\"0000100005\" />"
				+ "      <Interlocutor Tipo=\"WE\" TipoConsec=\"000\" IDSAP=\"0000100005\" />"
				+ "      <Interlocutor Tipo=\"WE\" TipoConsec=\"001\" IDSAP=\"0000500000\" />"
				+ "      <Interlocutor Tipo=\"WE\" TipoConsec=\"002\" IDSAP=\"0000500001\" />"
				+ "      <Interlocutor Tipo=\"WE\" TipoConsec=\"003\" IDSAP=\"0000500002\" />"
				+ "      <Interlocutor Tipo=\"WE\" TipoConsec=\"004\" IDSAP=\"0000500003\" />"
				+ "      <Interlocutor Tipo=\"WE\" TipoConsec=\"005\" IDSAP=\"0000500040\" />"
				+ "      <Interlocutor Tipo=\"WE\" TipoConsec=\"006\" IDSAP=\"0000500044\" />"
				+ "    </Interlocutores>"
				+ "  </Cliente>"
				+ "</Clientes>";
*/
		
		/*  xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> " 
		  +" <Precios xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"Q00\" Tipo=\"COND_A02\" ArchivoOrigen=\"COND_A_01617624_OFERTA_FINAL\">"
		 + " <Precio RegistroID=\"1\"> " 
		 + " <Basico ProductoClave=\"NOTE53\"> " 
		 + " <Destino>SD01</Destino> " + " <Organizacion>AV01</Organizacion> " 
		 + " <Canal>R2</Canal> " + " </Basico> " 
		 + " <Condiciones UnidadMedida=\"BX\" ClaseCondicion=\"VKA0\" Moneda=\"MXN\" VigenciaInicio=\"20160120\" VigenciaFin=\"20160131\"> "
		 + " <CostoActualizado>0.00</CostoActualizado> " 
		 + " <PrecioBase>203.40</PrecioBase> " + " </Condiciones> " 
		 + " </Precio> " + " </Precios>";*/
		
		xml= "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<Creditos xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Cliente=\"160\" Ambiente=\"Q00\" Tipo=\"CRESTA01\" ArchivoOrigen=\"CRESTA_01650536_UNITARIO\">"
				+ "  <Credito RegID=\"1\" IDSAP=\"12345\" Limite=\"4000.0000\" Saldo=\"3958.3900\" Bloqueado=\"\" />"
				+ "</Creditos>";
		 
		return xml;
	}

	
}
