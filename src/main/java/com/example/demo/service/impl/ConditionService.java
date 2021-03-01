package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Alert;
import com.example.demo.model.Condition;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConditionService implements Condition<Alert> {

    private final List<Condition<Alert>> conditions;

    @Override
    public boolean test(Alert alert) {
        return conditions.stream()
                .allMatch(condition -> condition.test(alert));
    }
}
