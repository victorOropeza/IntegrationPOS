package Inventario;

import java.util.ArrayList;

public class Inventario 
{
    
    private ArrayList<String> F=new ArrayList();  

    public ArrayList<String> getF() {
        return F;
    }

    public void setF(ArrayList<String> F) {
        this.F = F;
    }

    @Override
    public String toString() {
        return "Inventario{" + "F=" + F + '}';
    }
    
    
}
