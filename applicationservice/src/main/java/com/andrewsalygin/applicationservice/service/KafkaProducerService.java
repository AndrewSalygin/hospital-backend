package com.andrewsalygin.applicationservice.service;

import com.andrewsalygin.applicationservice.dto.StringWrapper;
import com.andrewsalygin.applicationservice.model.DoctorShortInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendNotifySpecializations() {
        kafkaTemplate.send("notify.specializations", new StringWrapper(""));
    }

    public void sendDoctorId(Integer doctorId) {
        kafkaTemplate.send("doctors", doctorId);
    }
}
