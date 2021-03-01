package com.example.demo.config;

import static com.example.demo.entity.Alert.AlertTarget.USER;
import static org.springframework.util.StringUtils.hasLength;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.Alert;
import com.example.demo.model.Condition;

@Configuration
public class ConditionalConfiguration {

    @Bean
    public Condition<Alert> alertLengthCondition() {
        return alert -> hasLength(alert.getTitle());
    }

    @Bean
    public Condition<Alert> alertUserTargetCondition() {
        return alert -> USER.equals(alert.getTarget());
    }
}
