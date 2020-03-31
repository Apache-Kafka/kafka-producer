package com.kafka.produce.controllers;

import com.kafka.produce.domain.Commodity;
import com.kafka.produce.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
