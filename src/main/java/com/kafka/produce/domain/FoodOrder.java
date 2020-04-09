package com.kafka.produce.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: FoodOrder
 * Inside the package - com.kafka.produce.domain
 * Created Date: 4/8/2020
 * Created Time: 3:21 PM
 **/
@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodOrder {

    private int amount;
    private String item;
}
