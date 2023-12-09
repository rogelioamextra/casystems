/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Lob;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Service;

/**
 *
 * @author mramirez
 */
@Service
public class BotonFinaliza implements Serializable {

    @JsonProperty("url")
    private String url;
    @Lob
    @Type(type = "jsonb")
    @JsonProperty("request")
    private Object request;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

}
