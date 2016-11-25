package Controlador;

import com.taurus.openbravo.integracionPOS.main.GT_CLS_Main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;


public class Controller extends Thread 
{
                
    @Override
    public void run() {
        try {      
            principal();
        } catch (InterruptedException | IOException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void principal() throws InterruptedException, FileNotFoundException, IOException, RemoteException, NoSuchAlgorithmException, ParserConfigurationException
    {
        //Instancias
        GT_CLS_Main iniciar=new GT_CLS_Main();
       
      while(!Thread.currentThread().isInterrupted()) 
      {
              iniciar.manejarPeticionesWS();
              Thread.sleep(1000);
              //Thread.sleep(300000);
      }
      
    }
    
     public void kill() {
        interrupt();
    }
}
