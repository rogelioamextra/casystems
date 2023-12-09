/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.repositorys.CatServiciosValidacionesExternosRepository;
import com.mx.ca.viu.services.FTPService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.io.IOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service(value = "FTPService")
public class FTPServiceImpl implements FTPService {

    private String HOST;
    private String USER;
    private String PASS;

    //private Session session;
   // private Channel channel;
    //  private ChannelSftp sftpChannel;

    @Autowired
    CatServiciosValidacionesExternosRepository servicios;

    Logger logger = LoggerFactory.getLogger(FTPServiceImpl.class);

    FTPClient ftpClient = new FTPClient();

    private void llena() {

        CatServiciosValidacionesExternos credenciales = servicios.buscarConfigFTP();
        if (credenciales != null) {
            if (!credenciales.getCatConfiguracionesServiciosExternosList().isEmpty()) {
                this.HOST = credenciales.getCatConfiguracionesServiciosExternosList().get(0).getUrl();
                this.USER = credenciales.getCatConfiguracionesServiciosExternosList().get(0).getUser();
                this.PASS = credenciales.getCatConfiguracionesServiciosExternosList().get(0).getPass();
            }
        } else {
            logger.error("No se encontraron los parametros de configuracion para el FTP de VIU");
        }

    }

    @Override
    public FTPClient connect() {
        logger.info("Conectando al ftp");
        llena();

        try {

            if (ftpClient == null) {
                ftpClient = new FTPClient();

                ftpClient.connect(HOST);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                boolean logged = ftpClient.login(USER, PASS);
                if (logged) {
                    return ftpClient;

                }
            } else {
                if (ftpClient.isConnected()) {
                    return ftpClient;
                } else {
                    ftpClient.connect(HOST);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    boolean logged = ftpClient.login(USER, PASS);
                    if (logged) {
                        return ftpClient;

                    }
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.info("No se pudo conectar al ftp");
            e.printStackTrace();
        }
        return ftpClient;

    }

    @Override
    public boolean uploadFiles(String empresa, String remoteFile, InputStream inputStream) {
        if (ftpClient == null || !ftpClient.isConnected()) {
            connect();
        }
        try {
            createDirectory(empresa);
            boolean done = ftpClient.storeFile(empresa + "/" + remoteFile, inputStream);
            if (done) {
                logger.info("Archivo guardado");
                return true;
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("No se pudo guardar el archivo");
        } finally {
            try {
                ftpClient.disconnect();
                ftpClient.logout();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public InputStream dowloadFiles(String folder, String nombreArchivo) {

//        if (ftpClient == null || !ftpClient.isConnected()) {
//            connect();
//        }
        ftpClient = connect();
        InputStream inputStream = null;

        try {
            boolean remoteDirectoryExists = ftpClient.changeWorkingDirectory(folder);
            if (remoteDirectoryExists) {
                // LOGGER.info("Archivo:" + file);
                final InputStream inputStreamTemp = ftpClient.retrieveFileStream(nombreArchivo);
                inputStream = new ByteArrayInputStream(IOUtils.toByteArray(inputStreamTemp));
                inputStreamTemp.close();
                boolean completed = ftpClient.completePendingCommand();

            } else {
                logger.error("No existe el directorio: " + folder);
            }

        } catch (Exception e) {

        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return inputStream;

    }

    @Override
    public boolean createDirectory(String folder) {

        boolean respuesta = false;
        try {
            String folderAux = "";
            String dierctorios[] = folder.split("/");
            for (int i = 0; i < dierctorios.length; i++) {

                folderAux = folderAux + dierctorios[i] + "/";
                if (!ftpClient.changeWorkingDirectory(folderAux)) {
                    System.out.println("ene ste nievel estas " + ftpClient.printWorkingDirectory());

                    System.out.println("folder a crear:" + folderAux);
                    System.out.println("directorio se creo: " + ftpClient.makeDirectory(folderAux));
                } else {
                    //  ftpClient.changeWorkingDirectory(folderAux);
                    System.out.println("ene ste nievel estas " + ftpClient.printWorkingDirectory());
                    System.out.println("no se creo el folder :" + folderAux);
                    ftpClient.changeToParentDirectory();
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {

        }

        return respuesta;

    }

    private void createD(String path, ChannelSftp sftpChannel) {
        try {
            boolean continuar = false;

            //  System.out.println("directorio actual: " + sftpChannel.pwd());
            Vector<ChannelSftp.LsEntry> files = sftpChannel.ls(sftpChannel.pwd());
            for (ChannelSftp.LsEntry aux : files) {
                if (aux.getFilename().contentEquals(path)) {
                    continuar = true;
                    break;
                }

            }

            if (continuar) {
                sftpChannel.cd(path);

            } else {
                System.out.println("carpeta a crear: " + path);
                sftpChannel.mkdir(path);
                sftpChannel.cd(path);

            }

        } catch (SftpException ex) {
            java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createDirectorios(String path, ChannelSftp sftpChannel) {
        String carpetas[] = path.split("/");
        if (carpetas.length > 1) {
            for (int i = 0; i < carpetas.length; i++) {
                createD(carpetas[i],sftpChannel);

            }

        } else {
            try {
                System.out.println("carpeta a crear: " + path);
                sftpChannel.mkdir(path);
            } catch (SftpException ex) {
                java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean sendFileSFTP(byte[] content, String filename, Map<String, String> propertiesFTP) {
        System.out.println("Sending file to sftp... File " + filename);

        boolean status = false;
        ChannelSftp sftpChannel = inicializaClientesFtp(propertiesFTP);
        if (sftpChannel != null) {
            // Upload file
            try (InputStream fis = new ByteArrayInputStream(content)) {
                // Change to output directory
                System.out.println("direcctorio: " + propertiesFTP.get("ftp.sender.path"));
                createDirectorios(propertiesFTP.get("ftp.sender.path"),sftpChannel);
                sftpChannel.cd(sftpChannel.getHome());
//                sftpChannel.mkdir(propertiesFTP.get("ftp.sender.path"));
                sftpChannel.cd(propertiesFTP.get("ftp.sender.path"));
                sftpChannel.put(fis, filename);

                fis.close();
                status = true;
                System.out.println("File uploaded successfully - " + filename);
            } catch (Exception e) {
                System.out.println(e);
                java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.WARNING, null, "#Error FTP" + e);
            } finally {
                cerrarClientesFtp(sftpChannel);
            }
        } else {
            System.out.println(" nos e pudo conectar al servidor SFTP");
        }

        return status;
    }
    @Override
    public boolean deleteFileSFTP( String filename, Map<String, String> propertiesFTP) {
        System.out.println("delete file to sftp... File " + filename);

        boolean status = false;
        ChannelSftp sftpChannel = inicializaClientesFtp(propertiesFTP);
        if (sftpChannel != null) {
            // Upload file
            try {
                // Change to output directory
                System.out.println("direcctorio: " + propertiesFTP.get("ftp.sender.path"));
                createDirectorios(propertiesFTP.get("ftp.sender.path"),sftpChannel);
                sftpChannel.cd(sftpChannel.getHome());
//                sftpChannel.mkdir(propertiesFTP.get("ftp.sender.path"));
                sftpChannel.rm(propertiesFTP.get("ftp.sender.path")+"/"+filename);
                

               
                status = true;
                System.out.println("File delete successfully - " + filename);
            } catch (Exception e) {
                System.out.println(e);
                java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.WARNING, null, "#Error FTP" + e);
            } finally {
                cerrarClientesFtp(sftpChannel);
            }
        } else {
            System.out.println(" nos e pudo conectar al servidor SFTP");
        }

        return status;
    }

    @Override
    public byte[] dowloadFileSFTP(String filename, Map<String, String> propertiesFTP) {
        InputStream out = null;
        ChannelSftp sftpChannel = inicializaClientesFtp(propertiesFTP);
        if (sftpChannel != null) {
            try {
                System.out.println("dowload file to sftp... File " + filename);

                // Upload file
                // Change to output directory
                if (!propertiesFTP.get("ftp.sender.path").isEmpty()) {
                    sftpChannel.cd(propertiesFTP.get("ftp.sender.path"));
                }

                out = sftpChannel.get(filename);
                if (out != null) {
                    System.out.println("File uploaded dowload - " + filename);
                    return IOUtils.toByteArray(out);
                } else {
                    return null;
                }

            } catch (SftpException ex) {
                java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                cerrarClientesFtp(sftpChannel);

            }
        } else {
            System.out.println("nos e pudo conectar al servido SFTP");

        }

        return null;
    }

    /**
     * Metodo que se encarga de inicializar las propiedades del servidor FTP y
     * la redireccion al directorio correspondiente.
     *
     * @param propertiesFTP Propiedades para conexion al servidor
     */
    //ChannelSftp sftpChannel;

    public ChannelSftp inicializaClientesFtp(Map<String, String> propertiesFTP) {
        System.out.println("Connecting... " + propertiesFTP.get("ftp.sender.host"));
        try {
            JSch jsch = new JSch();
           Session session = jsch.getSession(propertiesFTP.get("ftp.sender.user"), propertiesFTP.get("ftp.sender.host"), Integer.parseInt(propertiesFTP.get("ftp.sender.port")));
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(propertiesFTP.get("ftp.sender.password"));
            session.connect();
          Channel  channel = session.openChannel("sftp");
            channel.connect();

            System.out.println("conexion:" + channel.isConnected());
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            return sftpChannel;
        } catch (JSchException e) {
            System.out.println(e);
            // No se puedo establecer conexión con el servidor FTP.
            java.util.logging.Logger.getLogger(FTPServiceImpl.class.getName()).log(Level.WARNING, null, "#Error FTP" + e);
        }
        return null;
    }

    /**
     * Metodo que se encarga de cerrar la conexion al servidor de FTP.
     */
    public void cerrarClientesFtp( ChannelSftp sftpChannel) {
        System.out.println("Disconnectinging...");
        sftpChannel.disconnect();
     //   channel.disconnect();
       // session.disconnect();
    }

}
