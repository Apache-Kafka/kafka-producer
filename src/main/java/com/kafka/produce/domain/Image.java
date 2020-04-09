package com.kafka.produce.domain;

import lombok.*;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: Image
 * Inside the package - com.kafka.produce.domain
 * Created Date: 4/8/2020
 * Created Time: 5:28 PM
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Image {

    private String name;
    private long size;
    private String type;
}
