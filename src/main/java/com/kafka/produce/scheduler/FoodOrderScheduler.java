package com.kafka.produce.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.produce.domain.FoodOrder;
import com.kafka.produce.producer.FoodOrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: FoodOrderScheduler
 * Inside the package - com.kafka.produce.scheduler
 * Created Date: 4/8/2020
 * Created Time: 3:32 PM
 **/
//@Service
public class FoodOrderScheduler {

    @Autowired
    private FoodOrderProducer foodOrderProducer;

    @Scheduled(fixedRate = 10000)
    public void generateFoodOrder() throws JsonProcessingException {
        FoodOrder foodOrder = FoodOrder.builder()
                .amount(new Random().nextInt(11))
                .item(generateRandomString())
                .build();
        foodOrderProducer.sendOrder(foodOrder);

    }

    private String generateRandomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }
}
