package com.kafka.produce.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;


import java.time.LocalDate;
import java.util.UUID;

/**
 * vbala created on 3/30/2020
 * Inside the package - com.kafka.produce.domain
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @JsonProperty("employee_id")
    private UUID employeeId;
    private String employeeName;
    @JsonProperty("birth_Date")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate dateOfBirth;
}
