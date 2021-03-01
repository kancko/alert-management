package com.example.demo.service.impl;

import static java.util.concurrent.CompletableFuture.runAsync;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Alert;
import com.example.demo.repository.AlertRepository;
import com.example.demo.service.AlertService;
import com.example.demo.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("alertService")
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final NotificationService notificationService;

    @Override
    public Alert save(Alert alert) {
        var entity = alertRepository.saveAndFlush(alert);
        sendNotification(entity);
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

    private void sendNotification(Alert entity) {
        runAsync(() -> notificationService.fireNotification(entity)).exceptionally(throwable -> {
            log.error("Failed to send notification", throwable);
            return null;
        });
    }
}
