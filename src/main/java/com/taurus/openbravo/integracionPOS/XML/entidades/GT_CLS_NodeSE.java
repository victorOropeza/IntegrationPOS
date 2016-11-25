/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taurus.openbravo.integracionPOS.XML.entidades;

/**
 *
 * @author Villa
 */
public class GT_CLS_NodeSE {
    
    private boolean inicio;
    private String prodCve;
    private String destino;
    private String claseC;
    
    public GT_CLS_NodeSE(boolean inicio, String prodCve, String destino, String claseC){
        this.inicio = inicio;
        this.prodCve = prodCve;
        this.destino = destino;
        this.claseC = claseC;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getClaseC() {
        return claseC;
    }

    public void setClaseC(String claseC) {
        this.claseC = claseC;
    }
    
    

    public String getProdCve() {
        return prodCve;
    }

    public void setProdCve(String prodCve) {
        this.prodCve = prodCve;
    }
    
    

    public boolean isInicio() {
        return inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }
    
}
