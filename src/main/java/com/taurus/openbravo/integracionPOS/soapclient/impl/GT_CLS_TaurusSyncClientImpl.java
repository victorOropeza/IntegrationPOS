package com.taurus.openbravo.integracionPOS.soapclient.impl;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_EncryptDecript;
import com.taurus.openbravo.utils.integracionPOSUtils.GT_CLS_ReadProperties;
import com.taurus.openbravo.integracionPOS.soapclient.GT_CLS_TaurusSyncClient;
import com.taurus.openbravo.soapclient.dev.ITAURUSSyncProxy;
import com.taurus.openbravo.soapclient.entities.files.DownloadFileRequest;
import com.taurus.openbravo.soapclient.entities.files.DownloadFileResponse;
import com.taurus.openbravo.soapclient.entities.files.FileContainer;
import com.taurus.openbravo.soapclient.entities.files.FileData;
import com.taurus.openbravo.soapclient.entities.files.ProcessingFileRequest;
import com.taurus.openbravo.soapclient.entities.files.ProcessingFileResponse;
import com.taurus.openbravo.soapclient.entities.files.SearchFileRequest;
import com.taurus.openbravo.soapclient.entities.files.SearchFileResponse;
import com.taurus.openbravo.soapclient.entities.files.UploadFileRequest;
import com.taurus.openbravo.soapclient.entities.security.CredentialData;

public class GT_CLS_TaurusSyncClientImpl implements GT_CLS_TaurusSyncClient {
	
	private ITAURUSSyncProxy itaurusSyncProxy = new ITAURUSSyncProxy();
	
	@Override
	public FileContainer[] buscarArchivos(String fileKey) throws RemoteException, NoSuchAlgorithmException, IOException { 
		SearchFileRequest request = new SearchFileRequest(getCredentials(), fileKey);
		SearchFileResponse response = getITAURUSSyncProxy().searchFile(request);
		FileContainer[] fileContainer = new FileContainer[0];
		if(response.getCodeNumber() == 302){
			//Si hay
			fileContainer = response.getResult();
		}
		return fileContainer;
	}
	@Override
	public String bajarArchivos(FileContainer file, String fileKey) throws RemoteException, NoSuchAlgorithmException, IOException {
		DownloadFileRequest request = new DownloadFileRequest(file.getKey(), fileKey, getCredentials());
		DownloadFileResponse response = getITAURUSSyncProxy().downloadFile(request);
		String data = "";
		if(response.getCodeNumber() == 302){
			FileData fileData = response.getResult();
			data = fileData.getData();
		}
		return new String(GT_CLS_EncryptDecript.decryptBase64(data));
	}
	@Override
	public String subirArchivos(String data) throws RemoteException, NoSuchAlgorithmException, IOException {
		FileData fileData = new FileData();
		fileData.setData(GT_CLS_EncryptDecript.encryptBase64(data));
		UploadFileRequest request = new UploadFileRequest("",getCredentials(), fileData);
		getITAURUSSyncProxy().uploadFile(request);
		return null;
	}
	public String confirmarProcesoArchivo(FileContainer file, String fileKey) throws NoSuchAlgorithmException, RemoteException, IOException{
		ProcessingFileRequest request = new ProcessingFileRequest(file.getKey(), fileKey, getCredentials());
		ProcessingFileResponse response =  getITAURUSSyncProxy().processingFile(request);
		String data = "";
		if(response.getCodeNumber()==302){
			FileData fileData = response.getResult();
			data = fileData.getData();
		}
		return data;
	}

	public CredentialData getCredentials() throws NoSuchAlgorithmException, IOException {
		final String USERNAME = GT_CLS_ReadProperties.getPropertieValue("usuario.taurussync");
		final String PASSWORD = GT_CLS_ReadProperties.getPropertieValue("password.taurussync");
		return new CredentialData(GT_CLS_EncryptDecript.encryptMD5(PASSWORD), USERNAME);
	}

	public ITAURUSSyncProxy getITAURUSSyncProxy() {
		return itaurusSyncProxy;
	}

	public void setITAURUSSyncProxy(ITAURUSSyncProxy itaurusSyncProxy) {
		this.itaurusSyncProxy = itaurusSyncProxy;
	}
}
