package com.taurus.openbravo.integracionPOS.XML.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.taurus.openbravo.integracionPOS.XML.GT_CLS_XMLServiceInterface;
import com.taurus.openbravo.integracionPOS.XML.entidades.GT_CLS_Inventario;
import com.taurus.openbravo.integracionPOS.main.GT_CLS_Base;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_ReadProperties;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_SaveLog;

public class GT_CLS_ManageStockXML extends GT_CLS_Base
implements GT_CLS_XMLServiceInterface<GT_CLS_Inventario>{

	@Override
	public String generarXML(GT_CLS_Inventario inventario, String tipo) {
            System.out.println("Generando XML inventario");
		StringBuilder sb = new StringBuilder();
		sb.append(addTag("active", "true", null, null, false));
		sb.append(addTag("movementType", "I+", null, null, false));
//		sb.append(addTag("storageBin", null, "id", inventario.getAlmacen(), true));
//		sb.append(addTag("product", null, "id", inventario.getArticulo(), true));
//		sb.append(addTag("organization", null, "id", inventario.getSucursal(), true));
//		sb.append(addTag("uOM", null, "id", inventario.getUnidadMedida(), true));
		sb.append(addTag("movementDate", inventario.getMovementDate(), null, null, false));
//		sb.append(addTag("movementQuantity", inventario.getCantidad(), null, null, false));
		sb.append(addTag("costingStatus", "NC", null, null, false));
		sb.append(addTag("checkReservedQuantity", "true", null, null, false));
		sb.append(addTag("isProcessed", "false", null, null, false));
		sb.append(addTag("checkpricedifference", "false", null, null, false));
		sb.append(addTag("manualcostadjustment", "false", null, null, false));
		sb.append(addTag("isCostPermanent", "false", null, null, false));
		String temp = sb.toString();
		sb = new StringBuilder(addHeader());
		sb.append(addTag("MaterialMgmtMaterialTransaction", temp, null, null, false));
		sb.append(addFooter());
                
                System.out.println("XML inventario generado");
		return sb.toString();
	}

	@Override
	public void procesarXML(NodeList nodos) throws IOException {
		for(int i=0; i<nodos.getLength(); i++){
                        System.out.println("1");
			GT_CLS_Inventario inventario = new GT_CLS_Inventario();
			Node nodoMov = nodos.item(i);
			NamedNodeMap map = nodoMov.getAttributes();
			if(map!= null){
                            System.out.println("2");
                            try
                            {
                                inventario.setAlmacen(map.getNamedItem("Almacen").getNodeValue());
				inventario.setCantidad(map.getNamedItem("Cantidad").getNodeValue());
				inventario.setSucursal(map.getNamedItem("Centro").getNodeValue());
				inventario.setUnidadMedida(map.getNamedItem("UnidadMedida").getNodeValue());
				inventario.setArticulo(map.getNamedItem("Articulo").getNodeValue());
                            }
                            catch(Exception ex)
                            {
                                for(int x=0;x<=10;x++)
                                {
                                    System.err.println("Error");
                                }
                            }
				
			}
			if(!inventario.getArticulo().equals("") && !inventario.getUnidadMedida().equals("")){
			System.out.println("3");	
                            String searchKey = inventario.getArticulo() + "_" + inventario.getUnidadMedida();
				inventario.setArticulo(searchKey);
			}
			if(inventario.getCantidad().equals("")){
                            System.out.println("4");
				inventario.setCantidad("0.00");
			}
                        System.out.println("5");
			String qty = formatearCantidad(inventario.getCantidad());
			inventario.setCantidad(qty);
			inventario = validarDatos(inventario);
			if(inventario!=null){
				inventario.setMovementDate(obtenerFechaMovimiento());
				String xml = generarXML(inventario, "");
                                Vista.Vista.eventos.append("Enviando inventario...\n");
				agregar(xml, "MaterialMgmtMaterialTransaction");
			}
			//System.out.println("Parate aquí.");
		}//FOR PRINCIPAL
	}
        @Override
	public GT_CLS_Inventario validarDatos(GT_CLS_Inventario inventario) throws IOException {
		String idProducto = buscar("Product", "searchKey", inventario.getArticulo());
		String idAlmacen = buscar("Warehouse","description",inventario.getAlmacen());
		String idLocator = null;
		if(idAlmacen!=null){
			idLocator = buscar("Locator","warehouse.id",idAlmacen);
		}
		String idSucursal = buscar("Organization", "description", inventario.getSucursal());
		String idUOM = buscar("UOM", "name",inventario.getUnidadMedida());
		if(idProducto!=null && idLocator!=null && idSucursal!=null && idUOM!=null){
			inventario.setArticulo(idProducto);
			inventario.setAlmacen(idLocator);
			inventario.setSucursal(idSucursal);
			inventario.setUnidadMedida(idUOM);
			return inventario;
		}else {
			List<String> mensaje = new ArrayList<>();
			mensaje.add(GT_CLS_ReadProperties.getPropertieValue("mensaje.inventario.noinsertado.producto") + " " + inventario.getArticulo() );
			mensaje.add(GT_CLS_ReadProperties.getPropertieValue("mensaje.inventario.noinsertado.elementosrelacionados"));
			GT_CLS_SaveLog.guardarArchivo(mensaje);
			GT_CLS_SaveLog.guardarLogDb(mensaje);
		}
		return null;
	}
	private String formatearCantidad(String cantidad){
		StringTokenizer st = new StringTokenizer(cantidad, ".");
		int i=0;
		String amount = "";
		while(st.hasMoreTokens()){
			if(i==0){
				amount = st.nextToken();
				i++;
				break;
			}
		}
		if(amount.equals("")){
			return cantidad;
		}else{
			return amount;
		}		
	}
	private String obtenerFechaMovimiento(){
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fecha1 = formato.format(fecha);
		formato = new SimpleDateFormat("HH:mm:ss.SS");
		String fecha2 = formato.format(fecha);
		String fechaFinal = fecha1+"T"+fecha2+"Z";
		return fechaFinal;
	}

}
