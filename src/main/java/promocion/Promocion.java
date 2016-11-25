package promocion;

import java.util.ArrayList;


public class Promocion 
{
    private String RegistroID;
    private String PromocionID;
    private String Destino;
    private String Tipo;
    private String Condicion;
    private String VigenciaInicio;
    private String VigenciaFin;
    private String CantidadVenta;
    private String Descripcion;
    private ArrayList<String> Articulo;
    private ArrayList<String> Beneficio;

    public ArrayList<String> getBeneficio() {
        return Beneficio;
    }

    public void setBeneficio(ArrayList<String> Beneficio) {
        this.Beneficio = Beneficio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Promocion() {
    }

    public String getRegistroID() {
        return RegistroID;
    }

    public void setRegistroID(String RegistroID) {
        this.RegistroID = RegistroID;
    }

    public String getPromocionID() {
        return PromocionID;
    }

    public void setPromocionID(String PromocionID) {
        this.PromocionID = PromocionID;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getCondicion() {
        return Condicion;
    }

    public void setCondicion(String Condicion) {
        this.Condicion = Condicion;
    }

    public String getVigenciaInicio() {
        return VigenciaInicio;
    }

    public void setVigenciaInicio(String VigenciaInicio) {
        this.VigenciaInicio = VigenciaInicio;
    }

    public String getVigenciaFin() {
        return VigenciaFin;
    }

    public void setVigenciaFin(String VigenciaFin) {
        this.VigenciaFin = VigenciaFin;
    }

    public String getCantidadVenta() {
        return CantidadVenta;
    }

    public void setCantidadVenta(String CantidadVenta) {
        this.CantidadVenta = CantidadVenta;
    }

    public ArrayList<String> getArticulo() {
        return Articulo;
    }

    public void setArticulo(ArrayList<String> Articulo) {
        this.Articulo = Articulo;
    }

    @Override
    public String toString() {
        return "Promocion{" + "RegistroID=" + RegistroID + ", PromocionID=" + PromocionID + ", Destino=" + Destino + ", Tipo=" + Tipo + ", Condicion=" + Condicion + ", VigenciaInicio=" + VigenciaInicio + ", VigenciaFin=" + VigenciaFin + ", CantidadVenta=" + CantidadVenta + ", Descripcion=" + Descripcion + ", Articulo=" + Articulo + ", Beneficio=" + Beneficio + '}';
    }

   

    
    
    
    
}
