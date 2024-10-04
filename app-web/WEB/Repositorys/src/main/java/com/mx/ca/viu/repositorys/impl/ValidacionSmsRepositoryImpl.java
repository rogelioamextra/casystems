/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.BtValidacionSms;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.DtValidacionPinAval;
import com.mx.ca.viu.modelos.DtValidacionPinCliente;
import com.mx.ca.viu.repositorys.ValidacionSmsRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jbecerril
 */
@Repository("ValidacionSmsRepository")
public class ValidacionSmsRepositoryImpl extends SimpleRepository implements ValidacionSmsRepository {

    @Override
    @Transactional

    public void desabilitaTodos(String telefono) {
        try {
            getSession().createQuery("Update BtValidacionSms bit set bit.status=true where bit.numeroEnvio=:telefono ").setParameter("telefono", telefono).executeUpdate();

        } catch (Exception e) {

        }
    }

    @Override
    @Transactional

    public void desabilitaTodos5Minutos(Date fecha) {
        try {
            getSession().createQuery("Update BtValidacionSms bit set bit.status=true where bit.fechaEnvio<:fecha ").setParameter("fecha", fecha).executeUpdate();

        } catch (Exception e) {

        }
    }

    @Override
    @Transactional

    public BtValidacionSms validarCodigo(String telefono, String codigo) {
        BtValidacionSms resultado = new BtValidacionSms();
        try {
            resultado = (BtValidacionSms) getSession().createQuery("SELECT bit FROM BtValidacionSms bit where bit.numeroEnvio=:telefono and bit.codigoVerificacion=:codigo and bit.status=false ").setParameter("telefono", telefono).setParameter("codigo", codigo).uniqueResult();

        } catch (Exception e) {

        }
        return resultado;
    }

    @Override
    @Transactional
    public CatClientes buscarClienteTelefono(String telefono) {
        CatClientes respuesta = new CatClientes();
        try {

            respuesta = (CatClientes) getSession().createQuery("select cli from CatClientes cli where cli.idPersona.telefono =:telefono ").setParameter("telefono", telefono).uniqueResult();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional

    public String obtenPin(String curp) {
        String resultado = null;
        try {

            List<String> tmp = (List<String>) getSession().createQuery("select pi.pin from DtValidacionPinCliente pi where pi.curp=:curp").setParameter("curp", curp).list();
            if (tmp != null) {
                if (tmp.size() > 1) {
                    resultado = "ERROR,existe mas de un nip asociado al cliente";
                } else if (tmp.size() == 1) {
                    resultado = tmp.get(0);
                }

            } else {
                resultado = "ERROR,no exite un nip asociado al cliente";
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return resultado;
    }

    @Override
    @Transactional

    public String validarPin(String curp, String nip) {
        String resultado = null;
        try {

            DtValidacionPinCliente tmp = (DtValidacionPinCliente) getSession().createQuery("select pi from DtValidacionPinCliente pi where pi.curp=:curp and pi.pin=:nip").setParameter("curp", curp).setParameter("nip", nip).uniqueResult();
            if (tmp != null) {
                resultado = "OK";

            } else {
                resultado = "ERROR, el pin ingresado no es valido";
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return resultado;
    }

    @Override
    @Transactional
    public String obtenPinAval(String curp) {
        String resultado = null;
        try {

            List<String> tmp = (List<String>) getSession().createQuery("select pi.pin from DtValidacionPinAval pi where pi.curp=:curp and pi.statusPin=false order by pi.fechaValidacion desc").setParameter("curp", curp).list();

            if (tmp != null) {
                resultado = tmp.get(0);

            } else {
                resultado = "";
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return resultado;
    }

    @Override
    @Transactional

    public String validarPinAval(String telefono, String codigo, String curp) {
        String resultado = null;
        try {

            DtValidacionPinAval tmp = (DtValidacionPinAval) getSession().createQuery("select pi from DtValidacionPinAval pi where pi.curp=:curp and pi.pin=:nip  and pi.statusPin = false and pi.telefono=:telefono")
                    .setParameter("curp", curp)
                    .setParameter("nip", codigo)
                    .setParameter("telefono", telefono)
                    .uniqueResult();
            if (tmp != null) {
                resultado = "OK";

            } else {
                resultado = "ERROR, el pin ingresado no es valido";
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return resultado;
    }

    @Override
    @Transactional

    public void actualizaStatusNipAval(String telefono, String curp, String codigo) {
        try {
            getSession().createQuery("Update DtValidacionPinAval bit set bit.statusPin=true where bit.telefono=:telefono and bit.curp=:curp and bit.pin=:codigo")
                    .setParameter("telefono", telefono)
                    .setParameter("curp", curp)
                    .setParameter("codigo", codigo)
                    .executeUpdate();

        } catch (Exception e) {
            logger.error(e);
        }
    }

}
