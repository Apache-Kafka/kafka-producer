package com.kafka.produce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.produce.domain.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

/**
 * vbala created on 3/30/2020
 * Inside the package - com.kafka.produce.producer
 **/
//@Service
@Slf4j
public class EmployeeMessageProducer {

    private static KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public EmployeeMessageProducer(KafkaTemplate kafkaTemplate, ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRate = 1000)
    public void sendEmployeeeMessage() throws JsonProcessingException {
        Employee employee = generateRandomEmployee();
        String employeeAsString = objectMapper.writeValueAsString(employee);
        kafkaTemplate.send("t_employee",employeeAsString);
        log.debug("Employee Produced: " + employee.getEmployeeName());
    }

    private Employee generateRandomEmployee(){
        return Employee.builder()
                .employeeId(UUID.randomUUID())
                .employeeName("Employee:" + new Random().nextInt())
                .dateOfBirth(LocalDate.now()).build();

    }


}
