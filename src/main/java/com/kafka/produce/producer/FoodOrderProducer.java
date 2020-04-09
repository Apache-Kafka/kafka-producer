package com.kafka.produce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.produce.domain.FoodOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: FoodOrderProducer
 * Inside the package - com.kafka.produce.producer
 * Created Date: 4/8/2020
 * Created Time: 3:23 PM
 **/
@Service
@Slf4j
public class FoodOrderProducer {

    private static KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public FoodOrderProducer(KafkaTemplate kafkaTemplate,
                               ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrder(FoodOrder foodOrder) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(foodOrder);
        log.info("Posting food Order: " + foodOrder);
        kafkaTemplate.send("t_food_order", json);
    }

}
