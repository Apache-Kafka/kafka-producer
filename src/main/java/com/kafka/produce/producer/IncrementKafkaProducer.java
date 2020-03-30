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
        String key = "key" + (++count%4);
        String message = "Message is: The value is " + count;
        kafkaTemplate.send("t_increments_mult_partitions", key, message);
        log.debug("Key: {}", key);
        log.debug("Message: "+ message);
    }
}
