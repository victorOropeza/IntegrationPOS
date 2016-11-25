/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taurus.openbravo.integracionPOS.XML.entidades;

import java.util.List;

/**
 *
 * @author Villa
 */
public class GT_CLS_VersionNode {

    private String vigencia;
    private List<GT_CLS_NodeSE> prodCve;
    private boolean flag;
    

    public GT_CLS_VersionNode(String vigencia, List<GT_CLS_NodeSE> prodCve) {
        this.vigencia = vigencia;
        this.prodCve = prodCve;
        this.flag = false;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public List<GT_CLS_NodeSE> getProdCve() {
        return prodCve;
    }

    public void setProdCve(List<GT_CLS_NodeSE> prodCve) {
        this.prodCve = prodCve;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
