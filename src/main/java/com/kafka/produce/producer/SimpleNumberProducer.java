package com.kafka.produce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.produce.domain.SimpleNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: SimpleNumberProducer
 * Inside the package - com.kafka.produce.producer
 * Created Date: 4/8/2020
 * Created Time: 4:05 PM
 **/
//@Service
@Slf4j
public class SimpleNumberProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public SimpleNumberProducer(KafkaTemplate kafkaTemplate,
                             ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendNumber(SimpleNumber simpleNumber) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(simpleNumber);
        log.info("Posting the number: {}", simpleNumber.getNumber());
        kafkaTemplate.send("t_simple_number", message);

    }
}
