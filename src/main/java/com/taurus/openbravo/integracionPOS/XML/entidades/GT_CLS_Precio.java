package com.taurus.openbravo.integracionPOS.XML.entidades;

public class GT_CLS_Precio implements GT_CLS_EntidadBase {

	private String id;
	private String organizacion;
	private String canal ;
	private String uom;
	private String claseCondicion;
	private String moneda;
	private String vigenciaInicio;
	private String vigenciaFin;
	private String costoActualizado;
	private String precioBase;
	private String clavePrincipal;
	private String destino;
	private String articulo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getClaseCondicion() {
		return claseCondicion;
	}
	public void setClaseCondicion(String claseCondicion) {
		this.claseCondicion = claseCondicion;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getVigenciaInicio() {
		return vigenciaInicio;
	}
	public void setVigenciaInicio(String vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}
	public String getVigenciaFin() {
		return vigenciaFin;
	}
	public void setVigenciaFin(String vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}
	public String getCostoActualizado() {
		return costoActualizado;
	}
	public void setCostoActualizado(String costoActualizado) {
		this.costoActualizado = costoActualizado;
	}
	public String getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(String precioBase) {
		this.precioBase = precioBase;
	}
	public String getClavePrincipal() {
		return clavePrincipal;
	}
	public void setClavePrincipal(String clavePrincipal) {
		this.clavePrincipal = clavePrincipal;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	
}
