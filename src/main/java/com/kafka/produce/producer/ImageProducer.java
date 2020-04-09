package com.kafka.produce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.produce.domain.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: ImageProducer
 * Inside the package - com.kafka.produce.producer
 * Created Date: 4/8/2020
 * Created Time: 5:29 PM
 **/
@Service
@Slf4j
public class ImageProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public ImageProducer(KafkaTemplate kafkaTemplate,
                             ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendImage(Image image) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(image);
        log.info("Posting the image: {}", image.getName());
        kafkaTemplate.send("t_image", image.getType(), message);
    }
}
