package com.kafka.produce.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: RebalanceProducer
 * Inside the package - com.kafka.produce.producer
 * Created Date: 4/8/2020
 * Created Time: 6:26 AM
 **/
//@Service
@Slf4j
public class RebalanceProducer {

    private static KafkaTemplate<String, String> kafkaTemplate;

    public RebalanceProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private int i = 0;

    @Scheduled(fixedRate = 1000)
    public void sendMessage(){
        i++;
        log.info("Producing message: Counter is " + i);
        kafkaTemplate.send("t_rebalance", "Counter is " + i);
    }
}
