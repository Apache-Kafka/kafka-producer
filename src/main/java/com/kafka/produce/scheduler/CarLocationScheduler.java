package com.kafka.produce.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.produce.domain.CarLocation;
import com.kafka.produce.producer.CarLocationProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: CarLocationScheduler
 * Inside the package - com.kafka.produce.scheduler
 * Created Date: 4/8/2020
 * Created Time: 2:13 PM
 **/
//@Service
@Slf4j
public class CarLocationScheduler {
    private CarLocation carOne;
    private CarLocation carTwo;
    private CarLocation carThree;

    @Autowired
    private CarLocationProducer producer;

    public CarLocationScheduler(){
        long now = System.currentTimeMillis();
        carOne = CarLocation.builder()
                .carId("car-one")
                .timestamp(now)
                .distance(0)
                .build();
        carTwo = CarLocation.builder()
                .carId("car-two")
                .timestamp(now)
                .distance(110)
                .build();
        carThree = CarLocation.builder()
                .carId("car-three")
                .timestamp(now)
                .distance(95)
                .build();
    }

    @Scheduled(fixedRate = 10000)
    public void sendCarLocation() throws JsonProcessingException {
        long now = System.currentTimeMillis();
        carOne.setTimestamp(now);
        carOne.setDistance(carOne.getDistance() + 1);
        carTwo.setTimestamp(now);
        carTwo.setDistance(carTwo.getDistance() - 1);
        carThree.setTimestamp(now);
        carThree.setDistance(carThree.getDistance() + 1);
        producer.sendCarLocation(carOne);
        producer.sendCarLocation(carTwo);
        producer.sendCarLocation(carThree);
    }



}
