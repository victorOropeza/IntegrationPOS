package com.taurus.openbravo.integracionPOS.XML.entidades;

import java.util.List;

public class GT_CLS_Promocion implements GT_CLS_EntidadBase {

	private String id;
	private String promoID;
	private String destino;
	private String vigenciaIni;
	private String vigenciaFin;
	private String cantidadVenta;
	private String descripcion;
	private String tipo;
	private List<String> articulosVenta;
	private List<String> beneficios;
	private String condicion;
	
	public String getPromoID() {
		return promoID;
	}

	public void setPromoID(String promoID) {
		this.promoID = promoID;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getVigenciaIni() {
		return vigenciaIni;
	}

	public void setVigenciaIni(String vigenciaIni) {
		this.vigenciaIni = vigenciaIni;
	}

	public String getVigenciaFin() {
		return vigenciaFin;
	}

	public void setVigenciaFin(String vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}

	public String getCantidadVenta() {
		return cantidadVenta;
	}

	public void setCantidadVenta(String cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<String> getArticulosVenta() {
		return articulosVenta;
	}

	public void setArticulosVenta(List<String> articulosVenta) {
		this.articulosVenta = articulosVenta;
	}

	public List<String> getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(List<String> beneficios) {
		this.beneficios = beneficios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	
	
	
}
