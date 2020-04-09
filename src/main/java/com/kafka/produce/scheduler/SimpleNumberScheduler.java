package com.kafka.produce.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.produce.domain.SimpleNumber;
import com.kafka.produce.producer.SimpleNumberProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: SimpleNumberScheduler
 * Inside the package - com.kafka.produce.scheduler
 * Created Date: 4/8/2020
 * Created Time: 4:08 PM
 **/
//@Service
public class SimpleNumberScheduler {

    @Autowired
    private SimpleNumberProducer simpleNumberProducer;

    @Scheduled(fixedRate = 10000)
    public void generateNumber() throws JsonProcessingException {
        SimpleNumber simpleNumber = SimpleNumber.builder()
                .number(new Random().nextInt()).build();
        simpleNumberProducer.sendNumber(simpleNumber);
    }
}
