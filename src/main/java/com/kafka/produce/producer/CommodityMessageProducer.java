package com.kafka.produce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.produce.domain.Commodity;
import com.kafka.produce.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * vbala created on 3/31/2020
 * Inside the package - com.kafka.produce.producer
 **/
//@Service
@Slf4j
public class CommodityMessageProducer {

    private static KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public CommodityMessageProducer(KafkaTemplate kafkaTemplate, ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendCommodityMessage(Commodity commodity) throws JsonProcessingException {
        String commodityAsString = objectMapper.writeValueAsString(commodity);
        kafkaTemplate.send("t_commodity", commodity.getName(),commodityAsString);
        log.debug("Commodity Produced: " + commodity.getName());
    }
}
