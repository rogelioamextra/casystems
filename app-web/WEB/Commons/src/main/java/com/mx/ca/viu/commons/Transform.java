/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.io.Serializable;

public class Transform implements Serializable {

    static final long serialVersionUID = 1L;
    static ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

    public Transform() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public JavaType typeCollection(Class<?> defineRef) {
        JavaType type = mapper.getTypeFactory().
                constructCollectionType(List.class, defineRef);
        return type;
    }

    public static String toJSON(Object objectData) throws JsonProcessingException {

        return mapper.writeValueAsString(objectData);
    }

    public <T> List<T> toList(String inputJson, TypeReference<List<T>> typeReference) throws JsonParseException, JsonMappingException, IOException {
        List<T> tmp = mapper.readValue(inputJson, typeReference);

        return tmp;
    }

    public <T> T toObject(String json, Class<T> obj) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(json, obj);
    }

    public static <T> T toObject(Object objectData, Class<T> type) {
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(objectData, type);
    }

    public String validateWithSchema(String jsonData, String jsonSchema) throws Exception {

        final JsonNode objeto = mapper.readValue(jsonData, JsonNode.class);
        final JsonNode esquema = mapper.readValue(jsonSchema, JsonNode.class);
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        final JsonSchema schema = factory.getJsonSchema(esquema);

        ProcessingReport report;

        report = schema.validate(objeto);

        if (!report.toString().contains("success")) {
            ProcessingMessage message;

            Iterator<?> itr = report.iterator();
            String reporte = "";

            while (itr.hasNext()) {
                message = (ProcessingMessage) itr.next();
                reporte += message.asJson().get("message").asText();
            }
            return "" + reporte;
        }
        return null;

    }

}
