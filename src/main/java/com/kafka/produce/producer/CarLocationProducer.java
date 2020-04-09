package com.kafka.produce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.produce.domain.CarLocation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: CarLocationProducer
 * Inside the package - com.kafka.produce.producer
 * Created Date: 4/8/2020
 * Created Time: 2:09 PM
 **/
//@Service
public class CarLocationProducer {

    private static KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public CarLocationProducer(KafkaTemplate kafkaTemplate,
                               ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendCarLocation(CarLocation carLocation) throws JsonProcessingException {
        String carLocationMessage = objectMapper.writeValueAsString(carLocation);
        kafkaTemplate.send("t_location", carLocation.getCarId(), carLocationMessage);
    }
}
