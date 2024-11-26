package com.andrewsalygin.applicationservice.util;

import com.andrewsalygin.applicationservice.dto.doctor.DoctorSpecializationDTO;
import com.andrewsalygin.applicationservice.model.Specialization;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SpecializationOrDoctorSpecializationDeserializer implements Deserializer<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows @Override
    public Object deserialize(String topic, byte[] data) {
        JsonNode node = objectMapper.readTree(data);

        if (node.isInt()) {
            return node.asInt();
        }

        if (node.isArray() && !node.isEmpty()) {
            JsonNode firstElement = node.get(0);

            if (firstElement.has("specializationId") && firstElement.has("specializationName") && !firstElement.has("yearsOfExperience")) {
                return objectMapper.readValue(data, TypeFactory.defaultInstance().constructCollectionType(List.class, Specialization.class));
            } else if (firstElement.has("specializationId") && firstElement.has("specializationName") && firstElement.has("yearsOfExperience")) {
                return objectMapper.readValue(data, TypeFactory.defaultInstance().constructCollectionType(List.class, DoctorSpecializationDTO.class));
            }
        }

        return Collections.emptyList();
    }
}
