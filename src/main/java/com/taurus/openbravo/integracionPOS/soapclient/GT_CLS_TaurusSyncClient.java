package com.taurus.openbravo.integracionPOS.soapclient;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

import com.taurus.openbravo.soapclient.entities.files.FileContainer;

public interface GT_CLS_TaurusSyncClient {
	
	public FileContainer[] buscarArchivos(String clave) throws RemoteException, NoSuchAlgorithmException, IOException;
	public String bajarArchivos(FileContainer file, String fileKey) throws RemoteException, NoSuchAlgorithmException, IOException;
	public String subirArchivos(String data) throws RemoteException, NoSuchAlgorithmException, IOException;
	public String confirmarProcesoArchivo(FileContainer file, String fileKey) throws NoSuchAlgorithmException, RemoteException, IOException;

}
