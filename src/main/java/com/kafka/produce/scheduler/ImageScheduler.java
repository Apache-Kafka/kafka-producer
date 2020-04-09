package com.kafka.produce.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.produce.domain.Image;
import com.kafka.produce.producer.ImageProducer;
import com.kafka.produce.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: ImageScheduler
 * Inside the package - com.kafka.produce.scheduler
 * Created Date: 4/8/2020
 * Created Time: 5:35 PM
 **/
@Service
@Slf4j
public class ImageScheduler {

    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageProducer imageProducer;

    private static final List<String> imageTypes = Arrays.asList("jpg", "bmp", "png", "svg");

    @Scheduled(fixedRate = 10000)
    public void generateImage() throws JsonProcessingException {
        Image image = imageService.generateImage(imageTypes.get(new Random().nextInt(4)));
        imageProducer.sendImage(image);

    }
}
