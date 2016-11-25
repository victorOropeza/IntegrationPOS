package com.taurus.openbravo.integracionPOS.XML.entidades;

public class GT_CLS_Cliente implements GT_CLS_EntidadBase {

	private String id;
	private String idSAP;
	private String nombre;
	private String telefono;
	private String pais;
	private String localidad;
	private String poblacion;
	private String distrito;
	private String cp;
	private String estado;
	private String calle;
	private String numero;
	private String rfc;
	private String formaPago;
	private String categoria;
	private String pricelist;
	private String c_location_id;
	

	public GT_CLS_Cliente() {
	}
	public GT_CLS_Cliente(String idSAP, String nombre) {
		super();
		this.idSAP = idSAP;
		this.nombre = nombre;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdSAP() {
		return idSAP;
	}
	public void setIdSAP(String idSAP) {
		this.idSAP = idSAP;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPricelist() {
		return pricelist;
	}
	public void setPricelist(String pricelist) {
		this.pricelist = pricelist;
	}
	public String getC_location_id() {
		return c_location_id;
	}
	public void setC_location_id(String c_location_id) {
		this.c_location_id = c_location_id;
	} 
	
	
}
