package com.taurus.openbravo.integracionPOS.XML.entidades;

import java.util.List;

public class GT_CLS_Producto implements GT_CLS_EntidadBase {
	
	private String id;
	private String clavePrincipal;
	private String descripcion;
	private String grupo;
	private String impuestoClave;
	private String unidadMedida;
	private String codigoBarraPrincipal;
	private List<String> codigosBarrasSec;
	private List<String> codigosBarrasSecDesactivos;
	private String sucursal;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClavePrincipal() {
		return clavePrincipal;
	}
	public void setClavePrincipal(String clavePrincipal) {
		this.clavePrincipal = clavePrincipal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getImpuestoClave() {
		return impuestoClave;
	}
	public void setImpuestoClave(String impuestoClave) {
		this.impuestoClave = impuestoClave;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public String getCodigoBarraPrincipal() {
		return codigoBarraPrincipal;
	}
	public void setCodigoBarraPrincipal(String codigoBarraPrincipal) {
		this.codigoBarraPrincipal = codigoBarraPrincipal;
	}
	public List<String> getCodigosBarrasSec() {
		return codigosBarrasSec;
	}
	public void setCodigosBarrasSec(List<String> codigosBarrasSec) {
		this.codigosBarrasSec = codigosBarrasSec;
	}
	public List<String> getCodigosBarrasSecDesactivos() {
		return codigosBarrasSecDesactivos;
	}
	public void setCodigosBarrasSecDesactivos(List<String> codigosBarrasSecDesactivos) {
		this.codigosBarrasSecDesactivos = codigosBarrasSecDesactivos;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	
	
	
}
