package com.example.demo.dto;

import static com.example.demo.entity.Alert.AlertTarget;

import lombok.Data;

@Data
public class AlertDto {

    private Long id;
    private String title;
    private AlertTarget target;
}
