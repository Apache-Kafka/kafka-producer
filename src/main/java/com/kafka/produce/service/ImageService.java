package com.kafka.produce.service;

import com.kafka.produce.domain.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: ImageService
 * Inside the package - com.kafka.produce.service
 * Created Date: 4/8/2020
 * Created Time: 5:32 PM
 **/
@Service
@Slf4j
public class ImageService {

    private static int counter = 0;

    public Image generateImage(String type){
        counter++;
        String name = "image - " + counter;
        long size = ThreadLocalRandom.current().nextInt(100, 10_000);
        return Image.builder()
                .name(name)
                .size(size)
                .type(type).build();
    }
}
