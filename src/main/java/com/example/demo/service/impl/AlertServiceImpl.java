package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Alert;
import com.example.demo.repository.AlertRepository;
import com.example.demo.service.AlertService;
import com.example.demo.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service("alertService")
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final NotificationService notificationService;

    @Override
    public Alert save(Alert alert) {
        var entity = alertRepository.saveAndFlush(alert);
        notificationService.fireNotification(entity);
        return entity;
    }

    @Override
    public Alert update(Alert alert) {
        return alertRepository.saveAndFlush(alert);
    }

    @Override
    public void delete(Long alertId) {
        alertRepository.delete(new Alert(alertId));
    }

    @Override
    public List<Alert> findAll() {
        return alertRepository.findAll();
    }
}
