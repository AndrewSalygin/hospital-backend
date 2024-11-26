package com.andrewsalygin.specializationservice.util;

import com.andrewsalygin.specializationservice.dto.StringWrapper;
import com.andrewsalygin.specializationservice.model.DoctorShortInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import java.io.IOException;
import java.util.List;

public class GenericDeserializer implements Deserializer<Object> {

    private final ObjectMapper objectMapper;

    public GenericDeserializer() {
        this.objectMapper = new ObjectMapper();
        // Регистрация модуля для работы с типами Java 8 времени
        objectMapper.registerModule(new JavaTimeModule());
    }

    @SneakyThrows @Override
    public Object deserialize(String topic, byte[] data){
        JsonNode node = objectMapper.readTree(data);

        if (node.has("string")) {
            return objectMapper.treeToValue(node, StringWrapper.class);
        } else if (node.has("doctorId")) {
            return objectMapper.treeToValue(node, DoctorShortInfo.class);
        } else if (node.isArray() && node.size() > 0 && node.get(0).has("doctorId")) {
            return objectMapper.convertValue(node, objectMapper.getTypeFactory().constructCollectionType(List.class, DoctorShortInfo.class));
        } else if (node.isInt()) {
            return node.intValue();
        } else if (node.isArray()) {
            return List.of();
        }

        throw new IOException("Unrecognized JSON structure");
    }
}
