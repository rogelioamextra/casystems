/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import java.io.InputStream;
import java.util.Map;
import org.apache.commons.net.ftp.FTPClient;

public interface FTPService {

    public FTPClient connect();

    public boolean uploadFiles(String empresa, String remoteFile, InputStream inputStream);

    public InputStream dowloadFiles(String folder, String nombreArchivo);

    public boolean createDirectory(String folder);

    public boolean sendFileSFTP(byte[] content, String filename, Map<String, String> propertiesFTP);
    public byte[] dowloadFileSFTP(String filename,  Map<String, String> propertiesFTP);
     public boolean deleteFileSFTP( String filename, Map<String, String> propertiesFTP);

}
