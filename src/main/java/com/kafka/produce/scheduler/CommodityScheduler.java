package com.kafka.produce.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.produce.domain.Commodity;
import com.kafka.produce.producer.CommodityMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * vbala created on 3/31/2020
 * Inside the package - com.kafka.produce.scheduler
 **/
@Service
//@RequiredArgsConstructor
@Slf4j
public class CommodityScheduler {

    public final String COMMODITY_PATH_V1="http://localhost:8080/api/v1/commodities/";
    private final RestTemplate restTemplate;
    private final CommodityMessageProducer commodityMessageProducer;

    public CommodityScheduler(RestTemplateBuilder restTemplateBuilder, CommodityMessageProducer commodityMessageProducer) {
        this.restTemplate = restTemplateBuilder.build();
        this.commodityMessageProducer = commodityMessageProducer;
    }

    @Scheduled(fixedRate = 5000)
    public void getCommodities() throws JsonProcessingException {
        ResponseEntity<Commodity[]> response = restTemplate.getForEntity(
               COMMODITY_PATH_V1, Commodity[].class);
        Commodity [] commodities = response.getBody();
//        List<Commodity> commodities = restTemplate.exchange(
//                COMMODITY_PATH_V1,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Commodity>>() {
//        }).getBody();
        //log.info("Commodities:" + commodities);
        for (Commodity commodity: commodities) {
            commodityMessageProducer.sendCommodityMessage(commodity);
        }
    }
}
