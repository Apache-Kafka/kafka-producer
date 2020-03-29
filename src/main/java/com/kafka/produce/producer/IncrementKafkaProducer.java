package com.kafka.produce.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * vbala created on 3/29/2020
 * Inside the package - com.kafka.produce.producer
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class IncrementKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private int count = 0;

    @Scheduled(fixedRate = 1000)
    public void sendIncrementValue(){
        kafkaTemplate.send("t_increments", "Increment Value is: " + ++count);
        log.debug("Count Value: "+ count);
    }
}
