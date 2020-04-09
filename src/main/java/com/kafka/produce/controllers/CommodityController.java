package com.kafka.produce.controllers;

import com.kafka.produce.domain.Commodity;
import com.kafka.produce.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * vbala created on 3/31/2020
 * Inside the package - com.kafka.produce.controllers
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommodityController {

    private final CommodityService commodityService;

    @GetMapping("/commodities")
    public List<Commodity> getCommodity(){
        return commodityService.createDummyCommodities();

    }

    private final KafkaAdmin kafkaAdmin;

    @PostMapping("/createTopic")
    @ResponseStatus(HttpStatus.OK)
    public void createTopic() throws ExecutionException, InterruptedException {
        AdminClient admin = AdminClient.create(kafkaAdmin.getConfig());
        List<NewTopic> topics = new ArrayList<>();
        topics.add(new NewTopic("t_topic_1", 3, Short.valueOf("1")));
        topics.add(new NewTopic("t_topic_2", 3, Short.valueOf("1")));
        admin.createTopics(topics).all().get();
    }
}
