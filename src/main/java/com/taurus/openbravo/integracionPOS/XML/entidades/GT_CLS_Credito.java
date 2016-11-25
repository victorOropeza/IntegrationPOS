package com.taurus.openbravo.integracionPOS.XML.entidades;

public class GT_CLS_Credito implements GT_CLS_EntidadBase{

	private String id;
	private String idSAP;
	private String limite;
	private String saldo;
	private String bloqueado;
	
	
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

	public String getLimite() {
		return limite;
	}

	public void setLimite(String limite) {
		this.limite = limite;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	
}
