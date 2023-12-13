/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mx.ca.viu.modelos.CatConfiguracionesServiciosExternos;
import com.mx.ca.viu.modelos.CatFolio;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.modelos.DtDatosOcrIdentificacion;
import com.mx.ca.viu.modelos.dtos.request.ValidaCurpRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.digest.DigestUtils;
//import org.bouncycastle.asn1.ASN1Sequence;
//import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
//import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
//import org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder;
//import org.bouncycastle.operator.InputDecryptorProvider;
//import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
//import org.apache.commons.ssl.PKCS8Key;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author jbecerril
 */
public class UtilGenerico {

    private static final Logger loggerConnectionClient = Logger.getLogger("ConnectionClient");
    private static String charset = "UTF-8";

    public static String Encriptar(String texto) {
        String encript = DigestUtils.sha512Hex(texto);

        return encript;
    }

    public static String EncriptarLlAVE(String texto) {

        String secretKey = Constantes.KEYENCRIPTION; //llave para encriptar datos  
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "AES/GCM/NoPadding");
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String DesencriptarLlave(String textoEncriptado) throws Exception {

        String secretKey = Constantes.KEYENCRIPTION; //llave para encriptar datos   
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "AES/GCM/NoPadding");

            Cipher decipher = Cipher.getInstance("AES/GCM/NoPadding");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static byte[] encriptarDesencriptarBytes(byte[] datos) {

        byte data[] = new byte[datos.length];

        int i = 0;

        for (byte b : datos) {
            data[i] = (byte) (b ^ Constantes.KEYBYTES);
            i++;
        }

        System.out.println("Encryption Done...");
        return data;
    }

    /**
     * metodo que obtienen la hora actual de mexico
     *
     * @return objeto de tipo Date
     */
    public static Date obtenerHoraMexico() {

        try {
            Date date = new Date(new Date().getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");
            SimpleDateFormat formatterNY = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tzNY = TimeZone.getDefault();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(new Date().getTime());
            cal.setTimeZone(tz);
            formatter.setCalendar(cal);
            String strDate = formatter.format(date);
            formatterNY.setTimeZone(tzNY);

            Date respuesta = formatterNY.parse(strDate);

            return respuesta;
        } catch (ParseException ex) {
            Logger.getLogger(UtilGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();

    }

    public static Date obtenerSoloFechaMexico() {

        try {
            Date date = new Date(new Date().getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");
            SimpleDateFormat formatterNY = new SimpleDateFormat("yyyy-MM-dd");
            TimeZone tzNY = TimeZone.getDefault();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(new Date().getTime());
            cal.setTimeZone(tz);
            formatter.setCalendar(cal);
            String strDate = formatter.format(date);
            formatterNY.setTimeZone(tzNY);

            Date respuesta = formatterNY.parse(strDate);

            return respuesta;
        } catch (ParseException ex) {
            Logger.getLogger(UtilGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();

    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean isNotNull(String object) {
        return object != null && !"null".equals(object) && !object.isEmpty();
    }

    public static boolean isNull(String object) {
        return object == null || "null".equals(object) || object.isEmpty();
    }

    public static String convertDatetoString(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);

    }

    public static String convertDateToStringTimeHHMMSS(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }

    public static Date parseStringToDate(String date, String patter) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(patter);
            return formatter.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(UtilGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String stringToDateString(String date, String patter, String patter2) {

        try {
            SimpleDateFormat in = new SimpleDateFormat(patter);
            SimpleDateFormat out = new SimpleDateFormat(patter2);
            Date aux = in.parse(date);
            return out.format(aux);
        } catch (ParseException ex) {
            Logger.getLogger(UtilGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String stringToDateString(Date date, String patter) {

        SimpleDateFormat in = new SimpleDateFormat(patter);
        return in.format(date);

    }

    public static String convertDateToStringTimeMMSS(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(date);
    }

    public static String generaFolio(Long idUsuario, Long secuencia) {
        String resultado = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(obtenerHoraMexico());

        resultado = "" + cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH) + 1) + "" + cal.get(Calendar.DAY_OF_MONTH) + "-" + idUsuario + "-" + secuencia;
        return resultado;
    }

    public static String generaFolioQR(CatFolio data) {
        Date caducidad = data.getFechaVigencia();
        LocalDate localDate = caducidad.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String resultado;
        String estatus = "";
        if (data.getStatus()) {
            estatus = "T";
        } else {
            estatus = "F";
        }

        resultado = data.getIdProducto().getIdProductos() + "-"
                + localDate.getYear() + "/" + localDate.getMonthValue() + "/" + localDate.getDayOfMonth() + "-" + estatus;
        return resultado;
    }

    public static boolean validaContrasena(String pass) {
        /*
        String regex = "^(?=.*[A-Za-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,12}$";

        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(pass);
        return mat.matches();
         */
        boolean valida = false;
        String clave = pass.trim();
        //Valida si la contraseÃ±a estÃ¡ entre 8 y 12 caracteres
        if (clave.length() >= 8 && clave.length() <= 12) {
            //Valida si posee al menos una mayuscula
            for (int i = 65; i < 91; i++) {
                for (int j = 0; j < clave.length(); j++) {
                    if (clave.charAt(j) == i) {
                        valida = true;
                    }
                }
            }

            //Valida si posee al menos una minuscula
            if (valida == true) {
                valida = false;
                for (int i = 97; i < 123; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
            }

            //Valida si posee un nÃºmero
            if (valida == true) {
                valida = false;
                for (int i = 48; i < 58; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
            }

            //Valida si posee caracteres especiales
            if (valida == true) {
                valida = false;
                for (int i = 33; i < 48; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
                for (int i = 58; i < 65; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
                for (int i = 91; i < 97; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
                for (int i = 123; i < 255; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
            }
        }
        return valida;
    }

    public static String sendRequest(String jsonBody, CatConfiguracionesServiciosExternos servicio) {
        StringBuilder response = new StringBuilder();
        HttpsURLConnection con = null;
        try {
         //   System.out.println("json de envio:"+jsonBody);
            if (servicio != null) {
                URL url = new URL(servicio.getUrl());
                con = (HttpsURLConnection) url.openConnection();

                con.setRequestMethod(servicio.getTypeRequest());
                HashMap<String, Object> cabeceras = new HashMap<>();
                cabeceras = (HashMap<String, Object>) servicio.getCabeceras();
                if (!cabeceras.isEmpty()) {
                    for (Map.Entry<String, Object> aux : cabeceras.entrySet()) {

                        if (aux.getKey().equals("Authorization")) {
                            if (aux.getValue().equals("Basic")) {
                                String userpass = servicio.getUser() + ":" + servicio.getPass();
                                String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
                                con.setRequestProperty(aux.getKey(), basicAuth);

                            }

                        } else {
                            con.setRequestProperty(aux.getKey(), (String) aux.getValue());
                        }

                    }
                    con.setDoOutput(true);
                    //  loggerConnectionClient.log(org.apache.log4j.Level.INFO, "\nSending     : " + con.getURL());
                    //  loggerConnectionClient.log(org.apache.log4j.Level.INFO, "parameters : " + jsonBody);
                    try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                        out.write(jsonBody.getBytes(charset));
                        out.flush();
                    }
                    int responseCode = con.getResponseCode();
                    //   logger.log(org.apache.log4j.Level.INFO, "Response Code   : " + responseCode);

                    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));) {
                        String line;
                        while ((line = in.readLine()) != null) {
                            response.append(line);
                        }
                    }
                    //  logger.log(org.apache.log4j.Level.INFO, "Response    : " + (response.toString().length() > 1000 ? "Respuesta demaciado larga consulte en el postman" : response.toString()));
                    con.disconnect();

                } else {
                    return "Sin cabeceras de configuracion";
                }
            } else {
                return "Sin parametros de configuracion";
            }

        } catch (Exception e) {
            if (con != null) {
                con.disconnect();
            }
            //     logger.error(e);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return response.toString();

    }

    public static String ObjectToJson(Object obj) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UtilGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    //OBTIENE LA EDAD CON LA FECHA DE NACIMIENTO EN FORMATO DD/MM/YYYY
    public static String obtieneEdad(String dato) {
        dato = dato.replaceAll("\\s", "/");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(dato, fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);
        return String.valueOf(periodo.getYears());
    }

    //OBTIENE LA FECHA DE NACIMIENTO DE LA CURP Y LA REGRESA EN FORMATO DD/MM/YYYY
    public static String curp2Date(String curp) {
        SimpleDateFormat formato = new SimpleDateFormat("yyMMdd");
        Date fecha = new Date();
        try {
            String fechaCurp = curp.substring(4, 10);
            fecha = formato.parse(fechaCurp);
            formato.applyPattern("dd/MM/yyyy");
        } catch (ParseException ex) {
            Logger.getLogger(UtilGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formato.format(fecha);
    }

    //OBTIENE EL LUGAR(CLAVE DEL ESTADO) DE NACIEMIENTO CON LA CURP
    public static String curp2Estado(String curp) {
        String claveInegi = "";
        try {
            claveInegi = curp.substring(11, 13);
        } catch (Exception ex) {
            Logger.getLogger(UtilGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claveInegi;
    }

    //VALIDA MRZ
    public static boolean validaMRZ(DtDatosOcrIdentificacion obj) {
        boolean valido = false;
        try {
            if (obj.getOcr() != null && !obj.getOcr().equals("")) {
                if (!obj.getMrz().contains(obj.getOcr())) {
                    return valido;
                } else {
                    valido = true;
                }
            }
            if (obj.getCic() != null && !obj.getCic().equals("")) {
                if (!obj.getMrz().contains(obj.getCic())) {
                    return valido;
                } else {
                    valido = true;
                }
            }
            if (obj.getIdentificadorCiudadano() != null && !obj.getIdentificadorCiudadano().equals("")) {
                if (!obj.getMrz().contains(obj.getIdentificadorCiudadano())) {
                    return valido;
                } else {
                    valido = true;
                }
            }
        } catch (Exception ex) {
            valido = false;
            Logger.getLogger(UtilGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valido;
    }

    public static int getFiveDigitsNumber() {
        double fiveDigits = 10000 + Math.random() * 90000;
        return (int) fiveDigits;
    }

//    public static String redondear2decimales(Double date) {
//        DecimalFormat formato = new DecimalFormat("#.00");
//        String aux = formato.format(date);
//        if (aux.equals(".00")) {
//            return "0";
//        }
//        return aux;
//
//    }

    public static String redondear2decimales(Double date) {
//        DecimalFormat formato = new DecimalFormat("#.00");
//        String aux = formato.format(date);
//        if (aux.equals(".00")) {
//            return "0";
//        }
//        return aux;

        BigDecimal bd = new BigDecimal(date).setScale(2, RoundingMode.HALF_UP);
        //DecimalFormat formato = new DecimalFormat("#.00");
        //return formato.format(aux);
        return String.valueOf(bd.doubleValue());

    }
}
